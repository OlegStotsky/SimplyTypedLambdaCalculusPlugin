package com.olegstotsky.simplytypedlambdacalculus.evaluator;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.CapturingProcessHandler;
import com.intellij.execution.process.ProcessOutput;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

public class StitchDockerRunner implements StitchRunner {
    private static Logger LOG = Logger.getInstance(StitchDockerRunner.class);
    public static StitchDockerRunner INSTANCE = new StitchDockerRunner();
    boolean isRunning = false;
    String id;

    public StitchDockerRunner() {
        GeneralCommandLine cmd = new GeneralCommandLine();
        cmd.setExePath("docker");
        cmd.addParameters("container", "run", "-dt", "olegstotsky/stitch");
        ProgressManager.getInstance().run(new Task.Backgroundable(null,
                "Running stitch docker container...",
                false) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                try {
                    CapturingProcessHandler processHandler = new CapturingProcessHandler(cmd);
                    ProcessOutput out = processHandler.runProcess();
                    id = out.getStdoutLines().get(0);
                    isRunning = true;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public Optional<List<String>> evaluate(String text) {
        if (!isRunning) {
            return Optional.empty();
        }
        GeneralCommandLine cmd = new GeneralCommandLine();
        cmd.setExePath("docker");
        cmd.addParameters("container", "exec", "-i", id, "/scripts/my_stitch_bin");
        CapturingProcessHandler processHandler;
        try {
            processHandler = new CapturingProcessHandler(cmd);
        } catch (ExecutionException e) {
            LOG.warn(e);
            return Optional.empty();
        }
        Application app = ApplicationManager.getApplication();
        PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
        stitchStdIn.println(text);
        stitchStdIn.flush();
        stitchStdIn.println(":quit");
        stitchStdIn.flush();
        ProcessOutput out = processHandler.runProcess();
        List<String> lines = out.getStdoutLines();
        return Optional.of(lines);
    }
}
