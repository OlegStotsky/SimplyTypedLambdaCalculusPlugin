package com.olegstotsky.simplytypedlambdacalculus;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.io.*;

public class TypeCheckAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        try {
            Process process = Runtime.getRuntime().exec("echo ':load /Users/olegstotsky/desktop/University/LambdaPlugin/stitch/foo' | /Users/olegstotsky/desktop/University/LambdaPlugin/stitch/stitch");
            BufferedReader output = new BufferedReader(new InputStreamReader(process.getInputStream()));
            process.waitFor();
            while (output.ready()) {
                System.out.println(output.readLine());
            }
            System.out.println("HW");
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }

    }
}
