package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.openapi.util.Pair;

import java.util.List;
import java.util.Optional;

public class StitchConsoleRunnerOutputParser implements StitchOutputParser {
    public Optional<Pair<String, Boolean>> parseLines(List<String> lines) {
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

    public String parseErrors(List<String> lines, int numElements) {
        List<String> errors = lines.subList(2, 2 + numElements);
        errors.set(0, errors.get(0).substring(2));
        return "<html>" + String.join("<br>", errors) + "</html>";
    }
}
