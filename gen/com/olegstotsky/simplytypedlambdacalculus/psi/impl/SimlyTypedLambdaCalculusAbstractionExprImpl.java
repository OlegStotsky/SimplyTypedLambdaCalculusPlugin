// This is a generated file. Not intended for manual editing.
package com.olegstotsky.simplytypedlambdacalculus.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.olegstotsky.simplytypedlambdacalculus.psi.*;

public class SimlyTypedLambdaCalculusAbstractionExprImpl extends ASTWrapperPsiElement implements SimlyTypedLambdaCalculusAbstractionExpr {

  public SimlyTypedLambdaCalculusAbstractionExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimlyTypedLambdaCalculusVisitor visitor) {
    visitor.visitAbstractionExpr(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimlyTypedLambdaCalculusVisitor) accept((SimlyTypedLambdaCalculusVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public SimlyTypedLambdaCalculusLambdaExpr getLambdaExpr() {
    return findNotNullChildByClass(SimlyTypedLambdaCalculusLambdaExpr.class);
  }

  @Override
  @NotNull
  public SimlyTypedLambdaCalculusTypingExpr getTypingExpr() {
    return findNotNullChildByClass(SimlyTypedLambdaCalculusTypingExpr.class);
  }

  @Override
  @NotNull
  public PsiElement getId() {
    return findNotNullChildByType(ID);
  }

}
