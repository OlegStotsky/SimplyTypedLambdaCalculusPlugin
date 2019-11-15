package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class StitchExternalEvaluatorAnnotator extends ExternalAnnotator<List<StitchExternalEvaluatorAnnotator.StatementInfo>, List<StitchExternalEvaluatorAnnotator.ParseResult>> {
    @Nullable
    @Override
    public List<StatementInfo> collectInformation(@NotNull PsiFile file) {
        return PsiTreeUtil.getChildrenOfTypeAsList(file, SimplyTypedLambdaCalculusStatement.class)
                .stream()
                .map(stmt -> new StatementInfo(stmt.getText(), stmt.getTextOffset()))
                .collect(Collectors.toList());
    }

    @Nullable
    @Override
    public List<ParseResult> doAnnotate(List<StatementInfo> collectedInfo) {
        List<ParseResult> result = new ArrayList<>();
        collectedInfo.forEach(statement -> {
            String text = statement.getText();
            Integer offset = statement.getOffsetFromStart();
            GeneralCommandLine cmd = new GeneralCommandLine();
            String stitchBinaryPath = "/Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch";
            cmd.setExePath(stitchBinaryPath);
            try {
                Application app = ApplicationManager.getApplication();
                CapturingProcessHandler processHandler = new CapturingProcessHandler(cmd);
                PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
                stitchStdIn.println(text.substring(0, text.length() - 1));
                stitchStdIn.flush();
                stitchStdIn.println(":quit");
                stitchStdIn.flush();
                ProcessOutput out = processHandler.runProcess();
                List<String> lines = out.getStdoutLines();
                Optional<Pair<String, Boolean>> executionResult = parseLines(lines);
                if (executionResult.isPresent()) {
                    TextRange range = new TextRange(offset, offset + text.length() - 1);
                    String parseResult = executionResult.get().first;
                    if (executionResult.get().second) {
                        result.add(new SuccessResult(parseResult, range));
                    } else {
                        result.add(new ErrorResult(parseResult, range));
                    }
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        return result;
    }

    private Optional<Pair<String, Boolean>> parseLines(List<String> lines) {
        if (lines.size() < 4) {
            return Optional.empty();
        }

        if (lines.get(2).contains("Global variable not in scope")) {
            return Optional.of(new Pair<>(lines.get(2).substring(2), false));
        }
        if (lines.get(2).contains("Bad function application")) {
            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
        }
        if (lines.get(2).contains("Bad arith operand")) {
            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
        }
        if (lines.get(2).contains("Bad conditional")) {
            return Optional.of(new Pair<>(parseErrors(lines, 4), false));
        } else {
            return Optional.of(new Pair<>(lines.get(2).substring(2), true));
        }
    }

    private String parseErrors(List<String> lines, int numElements) {
        List<String> errors = lines.subList(2, 2 + numElements);
        errors.set(0, errors.get(0).substring(2));
        return "<html>" + String.join("<br>", errors) + "</html>";
    }

    @Override
    public void apply(@NotNull PsiFile file, List<ParseResult> annotationResult, @NotNull AnnotationHolder holder) {
        annotationResult.forEach(result -> {
            TextRange range = result.getTextRange();
            String errorMessage = result.getText();
            Annotation annotation = holder.createAnnotation(result.getHighlightSeverity(), range, "", errorMessage);
        });
    }

    static class StatementInfo {
        private String text;

        private Integer offsetFromStart;

        public StatementInfo(String text, Integer offsetFromStart) {
            this.text = text;
            this.offsetFromStart = offsetFromStart;
        }

        public String getText() {
            return text;
        }

        public Integer getOffsetFromStart() {
            return offsetFromStart;
        }
    }

    static abstract class ParseResult {
        private String text;

        private TextRange textRange;

        ParseResult(String text, TextRange textRange) {
            this.text = text;
            this.textRange = textRange;
        }

        public TextRange getTextRange() {
            return textRange;
        }

        String getText() {
            return text;
        }

        abstract HighlightSeverity getHighlightSeverity();
    }

    static class ErrorResult extends ParseResult {
        ErrorResult(String text, TextRange textRange) {
            super(text, textRange);
        }

        @Override
        HighlightSeverity getHighlightSeverity() {
            return HighlightSeverity.ERROR;
        }
    }

    static class SuccessResult extends ParseResult {
        SuccessResult(String text, TextRange textRange) {
            super(text, textRange);
        }

        @Override
        HighlightSeverity getHighlightSeverity() {
            return HighlightSeverity.INFORMATION;
        }
    }
}
