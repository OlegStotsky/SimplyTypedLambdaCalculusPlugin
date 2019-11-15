package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import java.util.List;
import java.util.Optional;

interface StitchRunner {
    Optional<List<String>> evaluate(String text);
}
