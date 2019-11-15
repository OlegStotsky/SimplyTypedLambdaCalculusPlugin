package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


public class StitchExternalEvaluatorAnnotator extends ExternalAnnotator<List<StatementInfo>, List<ParseResult>> {
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
        List<ParseResult> result = collectedInfo.stream().map(statement -> {
            String text = statement.getText();
            Integer offset = statement.getOffsetFromStart();
            Optional<List<String>> lines = new StitchConsoleRunner().evaluate(text);
            if (!lines.isPresent()) {
                return null;
            }
            Optional<Pair<String, Boolean>> executionResult = parseLines(lines.get());
            if (executionResult.isPresent()) {
                TextRange range = new TextRange(offset, offset + text.length() - 1);
                String parseResult = executionResult.get().first;
                if (executionResult.get().second) {
                    return new SuccessResult(parseResult, range);
                } else {
                    return new ErrorResult(parseResult, range);
                }
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());

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

}