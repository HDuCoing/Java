package classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveAsPDF {

    public static void saveToPDF(RSyntaxTextArea textArea, JFrame frame) {
        PDDocument document = null;
        try {
            document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDFont pdfFont = PDType1Font.COURIER;
            float fontSize = 15;
            float leading = 1.5f * fontSize;

            PDRectangle mb = page.getMediaBox();
            float margin = 70;
            float width = mb.getWidth() - 2 * margin;
            float x = mb.getLowerLeftX() + margin;
            float y = mb.getUpperRightY() - margin;

            String textAll = textArea.getText();
            List<String> lines = new ArrayList<String>();
            for (String text : textAll.split("\n")) {
                int lastSpace = -1;
                while (text.length() > 0) {
                    int spaceIndex = text.indexOf(' ', lastSpace + 1);
                    if (spaceIndex < 0)
                        spaceIndex = text.length();
                    String subString = text.substring(0, spaceIndex);
                    float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
                    if (size > width) {
                        if (lastSpace < 0)
                            lastSpace = spaceIndex;
                        subString = text.substring(0, lastSpace);
                        lines.add(subString);
                        text = text.substring(lastSpace).trim();
                        lastSpace = -1;
                    } else if (spaceIndex == text.length()) {
                        lines.add(text);
                        text = "";
                    } else {
                        lastSpace = spaceIndex;
                    }
                }
            }
            contentStream.beginText();
            contentStream.setFont(pdfFont, fontSize);
            contentStream.newLineAtOffset(x, y);
            for (String line : lines) {
                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
            }
            contentStream.endText();
            contentStream.close();

            document.save(new File("My_PDF.pdf"));
            JOptionPane.showMessageDialog(frame, "Saved.");
        } catch (IOException evt) {
            JOptionPane.showMessageDialog(frame, evt.getMessage());
        } finally {
            if (document != null) {
                try {
                    document.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage());
                }
            }
        }
    }
    }

