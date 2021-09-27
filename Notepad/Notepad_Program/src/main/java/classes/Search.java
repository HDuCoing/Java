package classes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    public static int searchWindow(RSyntaxTextArea textArea, String word) {
        Highlighter h = textArea.getHighlighter();
        //If the 'okay' button is selected
        if (word != null) {
            //Display message if input left blank
            if (word.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter a search word");
            }
            //Allow only single-word searches
            else if (word.contains(" ")) {
                JOptionPane.showMessageDialog(null, "Single word searches only");
            } else {
                //Create search criteria
                Pattern p = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(textArea.getText());
                //Tracks the number of times the search term appears
                int cycles = 0;
                //while there are undiscovered instances of the search word
                while (m.find()) {
                    cycles += 1;
                    //search terms gets highlighted
                    try {
                        h.addHighlight(m.start(), m.end(), DefaultHighlighter.DefaultPainter);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }
                //if no instances of the word are found
                if (cycles == 0) {
                    JOptionPane.showMessageDialog(null, "Search term not found");
                } else {
                    return cycles;
                }
            }
            //If the window is closed or 'cancel' is clicked
        } else {
            JOptionPane.showMessageDialog(null, "Search cancelled");
        }
        return 0;
    }
}
