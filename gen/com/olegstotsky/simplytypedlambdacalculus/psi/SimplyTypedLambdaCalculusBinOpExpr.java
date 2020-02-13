// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimplyTypedLambdaCalculusBinOpExpr extends PsiElement {

  @NotNull
  SimplyTypedLambdaCalculusLambdaExpr getLambdaExpr();

  @Nullable
  SimplyTypedLambdaCalculusNumExpr getNumExpr();

  @Nullable
  SimplyTypedLambdaCalculusVariableExpr getVariableExpr();

}
