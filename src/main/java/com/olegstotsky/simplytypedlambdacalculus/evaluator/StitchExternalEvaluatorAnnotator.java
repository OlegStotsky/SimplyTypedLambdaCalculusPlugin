package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.ExternalAnnotator;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusStatement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class CollectedInfo {
    List<StatementInfo> statementInfos;
    String rawText;

    public CollectedInfo(List<StatementInfo> statementInfos, String rawText) {
        this.statementInfos = statementInfos;
        this.rawText = rawText;
    }
}


public class StitchExternalEvaluatorAnnotator extends ExternalAnnotator<CollectedInfo, List<ParseResult>> {
    @Nullable
    @Override
    public CollectedInfo collectInformation(@NotNull PsiFile file) {
        List<StatementInfo> statementInfos = PsiTreeUtil.getChildrenOfTypeAsList(file, SimplyTypedLambdaCalculusStatement.class)
                .stream()
                .map(stmt -> new StatementInfo(stmt.getText(), stmt.getTextOffset()))
                .collect(Collectors.toList());
        String rawText = file.getText();

        return new CollectedInfo(statementInfos, rawText);
    }

    @Nullable
    @Override
    public List<ParseResult> doAnnotate(CollectedInfo collectedInfo) {
        Optional<List<String>> lines = new StitchDockerRunner().evaluate(collectedInfo.rawText);
        if (!lines.isPresent()) {
            return null;
        }

        return parse(lines.get(), collectedInfo);
    }

    private List<ParseResult> parse(List<String> strings, CollectedInfo info) {
        ArrayList<ParseResult> ans = new ArrayList<>();
        int curStmt = 0;
        int i = -1;
        while (i < strings.size()) {
            if (i < 2) {
                i++;
                continue;
            }

            String curString = strings.get(i);

            if (curString.contains("Global variable not in scope")) {
                String text = curString.substring(2);
                StatementInfo stmt = info.statementInfos.get(curStmt++);
                Integer offset = stmt.getOffsetFromStart();
                TextRange range = new TextRange(offset, offset + stmt.getText().length() - 1);
                ans.add(new ErrorResult(text, range));
                i++;
            }
            if (curString.contains("Bad function application")) {
                String text = parseErrors(strings, i, 3);
                StatementInfo stmt = info.statementInfos.get(curStmt++);
                Integer offset = stmt.getOffsetFromStart();
                TextRange range = new TextRange(offset, offset + stmt.getText().length() - 1);
                ans.add(new ErrorResult(text, range));
                i += 4;
            }
            if (curString.contains("Bad arith operand")) {
                String text = parseErrors(strings, i, 3);
                StatementInfo stmt = info.statementInfos.get(curStmt++);
                Integer offset = stmt.getOffsetFromStart();
                TextRange range = new TextRange(offset, offset + stmt.getText().length() - 1);
                ans.add(new ErrorResult(text, range));
                i += 4;
            }
            if (curString.contains("Bad conditional")) {
                String text = parseErrors(strings, i, 4);
                StatementInfo stmt = info.statementInfos.get(curStmt++);
                Integer offset = stmt.getOffsetFromStart();
                TextRange range = new TextRange(offset, offset + stmt.getText().length() - 1);
                ans.add(new ErrorResult(text, range));
                i += 5;
            } else {
                String text = curString.substring(2);
                StatementInfo stmt = info.statementInfos.get(curStmt++);
                Integer offset = stmt.getOffsetFromStart();
                TextRange range = new TextRange(offset, offset + stmt.getText().length() - 1);
                ans.add(new SuccessResult(text, range));
                i++;
            }
        }

        return ans;
    }

//    private Optional<Pair<String, Boolean>> parseLines(List<String> lines) {
//        if (lines.size() < 4) {
//            return Optional.empty();
//        }
//
//        if (lines.get(2).contains("Global variable not in scope")) {
//            return Optional.of(new Pair<>(lines.get(2).substring(2), false));
//        }
//        if (lines.get(2).contains("Bad function application")) {
//            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
//        }
//        if (lines.get(2).contains("Bad arith operand")) {
//            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
//        }
//        if (lines.get(2).contains("Bad conditional")) {
//            return Optional.of(new Pair<>(parseErrors(lines, 4), false));
//        } else {
//            return Optional.of(new Pair<>(lines.get(2).substring(2), true));
//        }
//    }

    private String parseErrors(List<String> lines, int from, int size) {
        List<String> errors = lines.subList(from, from + size + 1);
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
