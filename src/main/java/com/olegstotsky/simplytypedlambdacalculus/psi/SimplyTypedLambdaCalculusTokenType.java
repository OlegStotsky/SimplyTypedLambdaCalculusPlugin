package com.olegstotsky.simplytypedlambdacalculus.psi;

import com.intellij.psi.tree.IElementType;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusLanguage;

public class SimplyTypedLambdaCalculusTokenType extends IElementType {
    public SimplyTypedLambdaCalculusTokenType(String debugName) {
        super(debugName, SimplyTypedLambdaCalculusLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SimplyTypedLambdaCalculusTokenType." + super.toString();
    }
}
