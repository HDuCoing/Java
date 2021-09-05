package classes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.EditableTextExtractor;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Open {

    public static void openFile(File fi, RSyntaxTextArea textArea, JFrame frame) {
        //Get file type
        String fileName = fi.toString();
        int dotIndex = fileName.lastIndexOf('.');
        String end = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
        //Set correct syntax style for type of file
        if (end.equals("java")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        } else if (end.equals("py")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_PYTHON);
        } else if (end.equals("cpp")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_CPLUSPLUS);
        } else if (end.equals("html")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_HTML);
        } else if (end.equals("xml")) {
            textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_XML);
        }
        if (end.equals("rtf")) {
            try {
                //Get file path
                Path filePath = Paths.get(String.valueOf(fi));
                byte[] content = Files.readAllBytes(filePath);
                //Set up RTF reader
                String rtf = new String(content, StandardCharsets.ISO_8859_1);
                StringReader in = new StringReader(rtf);
                RTFEditorKit kit = new RTFEditorKit();
                Document doc = kit.createDefaultDocument();
                //Read RTF file
                kit.read(in, doc, 0);
                String text = doc.getText(0, doc.getLength());
                //Display
                textArea.setText(text);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        }
        if (end.equals("odt")) {
            TextDocument textD;
            try {
                // Extract text content from file
                textD = TextDocument.loadDocument(fileName);
                EditableTextExtractor ete = EditableTextExtractor.newOdfEditableTextExtractor(textD);
                ete.getText();
                String output = ete.getText();
                // Remove metadata from string output
                String cutoff = "MicrosoftOffice";
                String finalOutput = output.substring(0, output.indexOf(cutoff));
                // Add text to textArea
                textArea.setText(finalOutput);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            try {
                String s1 = "",
                        sl = "";
                // File reader
                FileReader fr = new FileReader(fi);
                // Buffered reader
                BufferedReader br = new BufferedReader(fr);
                // Initialize sl
                sl = br.readLine();
                // Take the input from the file
                while ((s1 = br.readLine()) != null) {
                    sl = sl + "\n" + s1;
                }
                // Set the text
                textArea.setText(sl);
            } catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
        }
    }
}



