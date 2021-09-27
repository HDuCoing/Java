package classes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Save {

    public static void saveFile(File fi, RSyntaxTextArea textArea, JFrame frame) {
        // Writes the file
        try {
            // Create a file writer
            FileWriter wr = new FileWriter(fi, false);
            // Create buffered writer
            BufferedWriter w = new BufferedWriter(wr);
            // Write
            w.write(textArea.getText());
            w.flush();
            w.close();
            wr.close();
            JOptionPane.showMessageDialog(frame, "Save successful.");
        } catch (Exception evt) {
            JOptionPane.showMessageDialog(frame, evt.getMessage());
        }
    }
    }

