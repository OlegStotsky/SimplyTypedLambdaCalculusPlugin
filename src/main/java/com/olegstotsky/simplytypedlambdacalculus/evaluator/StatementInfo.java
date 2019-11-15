package com.olegstotsky.simplytypedlambdacalculus.evaluator;

class StatementInfo {
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
