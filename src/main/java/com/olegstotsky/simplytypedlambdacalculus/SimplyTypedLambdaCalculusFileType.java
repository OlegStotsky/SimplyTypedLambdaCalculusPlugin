package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SimplyTypedLambdaCalculusFileType extends LanguageFileType {
    public static final SimplyTypedLambdaCalculusFileType INSTANCE = new SimplyTypedLambdaCalculusFileType();

    public SimplyTypedLambdaCalculusFileType() {
        super(SimplyTypedLambdaCalculusLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Simply Typed Lambda Calculus File Type";
    }

    @NotNull
    @Override
    public String getDescription() {
        return getName();
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "stlc";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return SimplyTypedLambdaCalculusIcons.ICON;
    }
}
