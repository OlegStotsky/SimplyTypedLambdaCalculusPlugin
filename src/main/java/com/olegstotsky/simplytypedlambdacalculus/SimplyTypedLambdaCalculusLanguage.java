package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.lang.Language;

public class SimplyTypedLambdaCalculusLanguage extends Language {
    public static final SimplyTypedLambdaCalculusLanguage INSTANCE = new SimplyTypedLambdaCalculusLanguage();

    public SimplyTypedLambdaCalculusLanguage() {
        super("SimplyTypedLambdaCalculus");
    }
}
