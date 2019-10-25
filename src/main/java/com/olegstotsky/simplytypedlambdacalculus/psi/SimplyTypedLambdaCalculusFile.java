package com.olegstotsky.simplytypedlambdacalculus.psi;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusFileType;
import com.olegstotsky.simplytypedlambdacalculus.SimplyTypedLambdaCalculusLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SimplyTypedLambdaCalculusFile extends PsiFileBase {
    public SimplyTypedLambdaCalculusFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SimplyTypedLambdaCalculusLanguage.INSTANCE);
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return super.getIcon(flags);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return SimplyTypedLambdaCalculusFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Simply Typed Lambda Calculus File";
    }
}
