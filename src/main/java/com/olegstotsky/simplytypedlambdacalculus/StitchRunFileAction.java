package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusFile;
import org.jetbrains.annotations.NotNull;

public class StitchRunFileAction extends DumbAwareAction {
    static final String ID = "runStitchFileAction";

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiFile file = e.getData(CommonDataKeys.PSI_FILE);
        VirtualFile virtualFile = file.getVirtualFile();
        if (virtualFile == null) return;
    }

    @Override
    public boolean isDumbAware() {
        return false;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        e.getPresentation().setEnabledAndVisible(isEnabled(e));
    }

    private static boolean isEnabled(@NotNull AnActionEvent e) {
        return e.getProject() != null && e.getData(CommonDataKeys.PSI_FILE) instanceof SimplyTypedLambdaCalculusFile;
    }
}
