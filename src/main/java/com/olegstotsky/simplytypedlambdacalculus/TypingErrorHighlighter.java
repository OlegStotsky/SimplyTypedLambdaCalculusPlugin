package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.execution.configurations.GeneralCommandLine;
import com.intellij.execution.process.*;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.olegstotsky.simplytypedlambdacalculus.psi.SimplyTypedLambdaCalculusStatement;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;

public class TypingErrorHighlighter implements Annotator {
    @Override
    public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
        if (element instanceof SimplyTypedLambdaCalculusStatement) {
            try {
                String text = element.getText();
                GeneralCommandLine cmd = new GeneralCommandLine();
                cmd.setExePath("/Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch");
                OSProcessHandler processHandler = new KillableProcessHandler(cmd);
                processHandler.addProcessListener(new StitchProcessListener(holder, element, processHandler));
                processHandler.startNotify();
                PrintWriter stitchStdIn = new PrintWriter(processHandler.getProcessInput());
                Application app = ApplicationManager.getApplication();
                app.executeOnPooledThread(() -> {
                    stitchStdIn.println(":type " + text.substring(0, text.length() - 1));
                    stitchStdIn.flush();
                });
            } catch (Exception e) {
            }
        }
    }

    private static class StitchProcessListener implements ProcessListener {
        private final ProcessHandler processHandler;
        boolean isReadingResult = false;
        int howMuchLeftToRead = Integer.MAX_VALUE;
        boolean isError = false;
        String result = "";
        boolean done = false;
        boolean hasFinishedReadingPrelude = false;
        boolean hasFinishedReadingLine = false;
        private int responseCount = 0;
        private AnnotationHolder holder;
        private PsiElement element;

        StitchProcessListener(AnnotationHolder holder, PsiElement element, ProcessHandler processHandler) {
            this.holder = holder;
            this.element = element;
            this.processHandler = processHandler;
        }

        @Override
        public void startNotified(@NotNull ProcessEvent event) {
            System.out.println("start notified");
        }

        @Override
        public void processTerminated(@NotNull ProcessEvent event) {
            System.out.println("terminated");
        }

        @Override
        public void processWillTerminate(@NotNull ProcessEvent event, boolean willBeDestroyed) {
            System.out.println("will terminate");
        }

        @Override
        public void onTextAvailable(@NotNull ProcessEvent event, @NotNull Key outputType) {
            String text = event.getText();
            if (done) {
                return;
            }
            if (isReadingResult) {
                result += text;
                howMuchLeftToRead--;
            }
            if (howMuchLeftToRead == 0) {
                TextRange range = new TextRange(element.getTextRange().getStartOffset(),
                        element.getTextRange().getEndOffset() - 1);
                if (isError) {
                    Annotation annotation = holder.createErrorAnnotation(range, result);
                } else {
                    Annotation annotation = holder.createInfoAnnotation(range, result);
                }
                done = true;
                processHandler.destroyProcess();
            }
            if (text.startsWith("λ>") || hasFinishedReadingPrelude) {
                hasFinishedReadingPrelude = true;
                if (text.equals("λ> ")) {
                    return;
                }
                if (text.contains("Global variable not in scope")) {
                    result += text;
                    howMuchLeftToRead = 0;
                    isError = true;
                }
            }
        }
    }
}
