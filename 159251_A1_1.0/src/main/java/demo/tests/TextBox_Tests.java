package demo.tests;
import classes.Open;
import classes.Save;
import classes.Search;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TextBox_Tests{
    RSyntaxTextArea textArea;
    JFrame frame;
    File testFile;
    File saveFile;

    @Before
    public void setUp() {
        textArea = new RSyntaxTextArea();
    }
    @After
    public void tearDown() {
        try
        {
            Files.deleteIfExists(Paths.get("testData/testSave.txt"));
        } catch(IOException e)
        {
            System.out.println();
        }
    }

    // Test that text area matches expected result.
    @Test
    public void testTextArea(){
        textArea.setText("Test");
        String expResult = "Test";
        assertEquals(expResult, textArea.getText());
    }

    // Tests the 'open' function
    @Test
    public void testOpen() throws IOException {
        testFile = new File("testData/test.txt");
        Open.openFile(testFile,textArea,frame);
        assertEquals("Hello! This is a test file.",textArea.getText());
    }

    // Tests the 'save' function
    @Test
    public void testSave() {
        textArea.setText("Testing save function");
        saveFile = new File("testData/testSave.txt");
        Save.saveFile(saveFile,textArea,frame);
        textArea.setText(null);
        Open.openFile(saveFile,textArea,frame);
        assertEquals("Testing save function",textArea.getText());
    }

    // Tests the 'search' function
    @Test
    public void testSearch() {
        testFile = new File("testData/test.txt");
        Open.openFile(testFile,textArea,frame);
        String word = "hello";
        int instances = Search.searchWindow(textArea,word);
        String word2 = "test";
        int instances2 = Search.searchWindow(textArea,word2);
        String word3 = "elephant";
        int instances3 = Search.searchWindow(textArea,word3);
        assertEquals(1,instances);
        assertEquals(1,instances2);
        assertEquals(0,instances3);
    }
}
