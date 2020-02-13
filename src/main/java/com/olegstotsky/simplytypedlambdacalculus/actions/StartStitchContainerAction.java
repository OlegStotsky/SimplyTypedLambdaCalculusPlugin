package com.olegstotsky.simplytypedlambdacalculus.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.olegstotsky.simplytypedlambdacalculus.evaluator.StitchDockerRunner;
import org.jetbrains.annotations.NotNull;

public class StartStitchContainerAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        StitchDockerRunner.INSTANCE.startStitchDockerContainer();
    }
}
