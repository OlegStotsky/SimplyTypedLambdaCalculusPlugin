package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.openapi.util.Pair;

import java.util.List;
import java.util.Optional;

public interface StitchOutputParser {
    Optional<Pair<String, Boolean>> parseLines(List<String> lines);

    String parseErrors(List<String> lines, int numElements);
}
