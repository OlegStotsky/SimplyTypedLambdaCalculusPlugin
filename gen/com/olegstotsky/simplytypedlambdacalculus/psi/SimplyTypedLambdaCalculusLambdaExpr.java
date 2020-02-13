// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface SimplyTypedLambdaCalculusLambdaExpr extends PsiElement {

  @Nullable
  SimplyTypedLambdaCalculusAbstractionExpr getAbstractionExpr();

  @Nullable
  SimplyTypedLambdaCalculusApplicationExpr getApplicationExpr();

  @Nullable
  SimplyTypedLambdaCalculusBinOpExpr getBinOpExpr();

  @Nullable
  SimplyTypedLambdaCalculusBoolExpr getBoolExpr();

  @Nullable
  SimplyTypedLambdaCalculusCondExpr getCondExpr();

  @Nullable
  SimplyTypedLambdaCalculusNumExpr getNumExpr();

  @Nullable
  SimplyTypedLambdaCalculusParExpr getParExpr();

  @Nullable
  SimplyTypedLambdaCalculusVariableExpr getVariableExpr();

}
