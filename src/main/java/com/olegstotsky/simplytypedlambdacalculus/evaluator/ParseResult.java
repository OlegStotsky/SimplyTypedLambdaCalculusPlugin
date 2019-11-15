package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.util.TextRange;

abstract class ParseResult {
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
