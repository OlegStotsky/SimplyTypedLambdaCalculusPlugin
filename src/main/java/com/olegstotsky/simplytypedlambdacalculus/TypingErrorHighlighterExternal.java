package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.*;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.util.ProgressIndicatorBase;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.RecursiveTreeElementWalkingVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class TypingErrorHighlighterExternal extends ExternalAnnotator<List<Pair<String, Integer>>, List<Pair<TextRange, String>>> {
    @Nullable
    @Override
    public List<Pair<String, Integer>> collectInformation(@NotNull PsiFile file) {
        return PsiTreeUtil.getChildrenOfTypeAsList(file, SimplyTypedLambdaCalculusStatement.class)
                .stream()
                .map(stmt -> new Pair<>(stmt.getText(), stmt.getTextOffset()))
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public List<Pair<TextRange, String>> doAnnotate(List<Pair<String, Integer>> collectedInfo) {
        List<Pair<TextRange, String>> result = new ArrayList<>();
        collectedInfo.forEach(statement -> {
            String text = statement.getFirst();
            Integer offset = statement.getSecond();
            GeneralCommandLine cmd = new GeneralCommandLine();
            String stitchBinaryPath = "/Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch";
            cmd.setExePath(stitchBinaryPath);
            try {
                Application app = ApplicationManager.getApplication();
                CapturingProcessHandler processHandler = new CapturingProcessHandler(cmd);
                PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
                stitchStdIn.println(":type " + text.substring(0, text.length() - 1));
                stitchStdIn.flush();
                stitchStdIn.println(":quit");
                stitchStdIn.flush();
                ProcessOutput out = processHandler.runProcess();
                List<String> lines = out.getStdoutLines();
                Optional<String> executionResult = parseLines(lines);
                if (executionResult.isPresent()) {
                    result.add(new Pair<>(new TextRange(offset, offset + text.length() - 1), executionResult.get()));
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        return result;
    }

    private Optional<String> parseLines(List<String> lines) {
        if (lines.size() < 4) {
            return Optional.empty();
        }

        if (lines.get(2).contains("Global variable not in scope")) {
            return Optional.of(lines.get(2).substring(2));
        }
        if (lines.get(2).contains("Bad function application")) {
            return Optional.of(parseBadFunctionApplication(lines));
        }
        if (lines.get(2).contains("Bad arith operand")) {
            return Optional.of(parseBadArithOperands(lines));
        }
        if (lines.get(2).contains("Bad conditional")) {
            return Optional.of(parseBadConditional(lines));
        }

        return Optional.empty();
    }

    private String parseBadConditional(List<String> lines) {
        return String.join("\n", lines.subList(2, 7));
    }

    private String parseBadArithOperands(List<String> lines) {
        return String.join("\n", lines.subList(2, 6));
    }

    private String parseBadFunctionApplication(List<String> lines) {
        return String.join("\n", lines.subList(2, 6));
    }

    @Override
    public void apply(@NotNull PsiFile file, List<Pair<TextRange, String>> annotationResult, @NotNull AnnotationHolder holder) {
        annotationResult.forEach(result -> {
            TextRange range = result.getFirst();
            String errorMessage = result.getSecond();
            holder.createErrorAnnotation(range, errorMessage);
        });
    }
}
