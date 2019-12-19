package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.openapi.util.Pair;

import java.util.List;
import java.util.Optional;

public class StitchDockerRunnerOutputParser {
    public Optional<Pair<String, Boolean>> parseLines(List<String> lines) {
        if (lines.size() < 6) {
            return Optional.empty();
        }
        lines = lines.subList(0, lines.size() - 2);

        if (lines.get(3).contains("Global variable not in scope")) {
            return Optional.of(new Pair<>(lines.get(4).substring(7), false));
        }
        if (lines.get(3).contains("Bad function application")) {
            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
        }
        if (lines.get(3).contains("Bad arith operand")) {
            return Optional.of(new Pair<>(parseErrors(lines, 3), false));
        }
        if (lines.get(3).contains("Bad conditional")) {
            return Optional.of(new Pair<>(parseErrors(lines, 4), false));
        } else {
            return Optional.of(new Pair<>(lines.get(4).substring(7), true));
        }
    }

    public String parseErrors(List<String> lines, int numElements) {
        List<String> errors = lines.subList(4, 2 + numElements);
        errors.set(0, errors.get(0).substring(7));
        return "<html>" + String.join("<br>", errors) + "</html>";
    }
}
