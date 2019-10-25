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

public class SimlyTypedLambdaCalculusTypingExprImpl extends ASTWrapperPsiElement implements SimlyTypedLambdaCalculusTypingExpr {

  public SimlyTypedLambdaCalculusTypingExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull SimlyTypedLambdaCalculusVisitor visitor) {
    visitor.visitTypingExpr(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof SimlyTypedLambdaCalculusVisitor) accept((SimlyTypedLambdaCalculusVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public SimlyTypedLambdaCalculusTypingExprOther getTypingExprOther() {
    return findChildByClass(SimlyTypedLambdaCalculusTypingExprOther.class);
  }

}
