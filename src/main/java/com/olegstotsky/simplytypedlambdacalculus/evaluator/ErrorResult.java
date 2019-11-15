package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;

class ErrorResult extends ParseResult {
    ErrorResult(String text, TextRange textRange) {
        super(text, textRange);
    }

    @Override
    HighlightSeverity getHighlightSeverity() {
        return HighlightSeverity.ERROR;
    }
}
