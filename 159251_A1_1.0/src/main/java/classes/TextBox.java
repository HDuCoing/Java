package classes;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;
import org.yaml.snakeyaml.Yaml;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TextBox extends JFrame implements ActionListener{
    JFrame frame;
    RSyntaxTextArea textArea;
    RTextScrollPane sp;
    //Integers for location of window
    int x = 50;
    int y = 50;
    public static void main(String[] args){
        TextBox tb = new TextBox();
        tb.newWindow(50,50);
    }
    public void newWindow(int x, int y) {

        // Import config file parameters
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.yml");
        Yaml yaml = new Yaml();
        Map<String,Object> data = yaml.load(is);

        // Set background and foreground colours
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        // Retrieve background colour from config file
        String backColStr = data.get("background_color").toString();
        String[] backColArray = backColStr.split(",");
        int[] backIntArray = new int[3];
        int count1 = 0;
        for (String num1 : backColArray) {
            int final1Num = Integer.parseInt(num1);
            backIntArray[count1] = final1Num;
            count1 += 1;
        }
        // Retrieve foreground colour from config file
        String foreColStr = data.get("foreground_color").toString();
        String[] foreColArray = foreColStr.split(",");
        int[] foreIntArray = new int[3];
        int count2 = 0;
        for (String num2 : foreColArray) {
            int final2Num = Integer.parseInt(num2);
            foreIntArray[count2] = final2Num;
            count2 += 1;
        }
        // Set colours for menus
        Color background = new Color(backIntArray[0],backIntArray[1],backIntArray[2]);
        Color foreground = new Color(foreIntArray[0],foreIntArray[1],foreIntArray[2]);
        UIManager.put("MenuBar.foreground",foreground);
        UIManager.put("Menu.foreground",foreground);
        UIManager.put("MenuItem.foreground",foreground);
        // Basic frame
        frame = new JFrame(data.get("title").toString());
        frame.setResizable(true);
        // Basic text writing zone
        textArea = new RSyntaxTextArea();
        sp = new RTextScrollPane(textArea);
        // Set font type,style and size
        Font font = new Font(data.get("font").toString(),Font.PLAIN,(int) data.get("size"));
        // Set color scheme for text editor window
        textArea.setFont(font);
        textArea.setBackground(background);
        textArea.setForeground(foreground);
        textArea.setCurrentLineHighlightColor(background);
        textArea.setCaretColor(foreground);
        //Menubar
        JMenuBar menuBar = new JMenuBar();
        //Menu
        JMenu file = new JMenu("File");
        //Menu items
        JMenu filesubmenu = new JMenu("Save");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem savetoPDF = new JMenuItem("Save as PDF");
        JMenuItem printFile = new JMenuItem("Print");

        //Action listeners
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        printFile.addActionListener(this);
        savetoPDF.addActionListener(this);

        // Adding menu items to menu
        file.add(newFile);
        file.add(openFile);
        filesubmenu.add(saveFile);
        filesubmenu.add(savetoPDF);
        file.add(filesubmenu);
        file.add(printFile);

        //Edit option
        JMenu edit = new JMenu("Edit");

        //Creating edit menu items
        JMenuItem select = new JMenuItem("Select All");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste");
        // submenu to edit
        JMenu submenu = new JMenu("Edit Font");
        JMenuItem normal = new JMenuItem("Normal");
        JMenuItem bold = new JMenuItem("Bold");
        JMenuItem italic = new JMenuItem("Italic");
        //JMenuItem underline = new JMenuItem("Underline");
        //JMenuItem superscript = new JMenuItem("Superscript");
        //JMenuItem color = new JMenuItem("Color");
        JMenuItem leftalign = new JMenuItem("Align left");
        JMenuItem rightalign = new JMenuItem("Align right");
        //Adding action listeners
        select.addActionListener(this);
        copy.addActionListener(this);
        cut.addActionListener(this);
        paste.addActionListener(this);
        submenu.addActionListener(this);
        normal.addActionListener(this);
        bold.addActionListener(this);
        italic.addActionListener(this);
        //underline.addActionListener(this);
        //superscript.addActionListener(this);
        //color.addActionListener(this);
        leftalign.addActionListener(this);
        rightalign.addActionListener(this);
        // Add to edit dropdown
        edit.add(select);
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        submenu.add(normal);
        submenu.add(bold);
        submenu.add(italic);
        //submenu.add(superscript);
        //submenu.add(color);
        submenu.add(leftalign);
        submenu.add(rightalign);
        edit.add(submenu);

        //Search option
        JMenuItem search = new JMenuItem("Search");

        search.addActionListener(this);

        //Time/Date option
        JMenuItem timeDate = new JMenuItem();
        //Refresh rate in milliseconds
        int refresh = 1000;
        ActionListener showTime = evt -> {
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(System.currentTimeMillis()));
            timeDate.setText(date);
        };
        new Timer(refresh, showTime).start();

        //About option
        JMenuItem about = new JMenuItem("About");

        about.addActionListener(this);

        // Exit option
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        // Add everything to the menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(search);
        menuBar.add(timeDate);
        menuBar.add(about);
        menuBar.add(exit);
        // Display window
        frame.setJMenuBar(menuBar);
        frame.add(sp);
        int width = (int) data.get("width");
        int height = (int) data.get("height");
        frame.setSize(width,height);
        frame.setLocation(x,y);
        frame.show();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String event = e.getActionCommand();
        // Select all text in textArea
        if (event.equals("Select All")) {
            textArea.selectAll();
        }
        // Paste clipboard
        else if (event.equals("Paste")) {
            textArea.paste();
        }
        // Copy to clipboard
        else if (event.equals("Copy")) {
            textArea.copy();
        }
        // Cuts text
        else if (event.equals("Cut")) {
            textArea.cut();
        }
        // Bolds ALL text
        else if (event.equals("Bold")){
            textArea.setFont(textArea.getFont().deriveFont(Font.BOLD, textArea.getFont().getSize()));
        }
        else if (event.equals("Italic")){
            textArea.setFont(textArea.getFont().deriveFont(Font.ITALIC, textArea.getFont().getSize()));
        }
        else if (event.equals("Normal")){
            textArea.setFont(textArea.getFont().deriveFont(Font.PLAIN, textArea.getFont().getSize()));
        }
        else if (event.equals("Open")) {

            JFileChooser j = new JFileChooser("f:");
            // simple OpenDialog function
            int r = j.showOpenDialog(null);
            // If a file is selected
            if (r == JFileChooser.APPROVE_OPTION) {
                //Reset syntax style to none
                textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
                // Set the label to the path of the selected directory
                File fi = new File(j.getSelectedFile().getAbsolutePath());
                Open.openFile(fi, textArea, frame);
            }
            // If open is cancelled
            else
                JOptionPane.showMessageDialog(frame, "Open cancelled.");

        } else if (event.equals("Save")) {
            JFileChooser j = new JFileChooser("f:");
            // Simple SaveDialog function
            int r = j.showSaveDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) {
                // Set directory
                File fi = new File(j.getSelectedFile().getAbsolutePath()+".txt");
                Save.saveFile(fi,textArea,frame);
            }
            // If save is cancelled
            else {
                JOptionPane.showMessageDialog(frame, "Save cancelled.");
            }
            // PDFbox save
        } else if (event.equals("Save as PDF")) {
            SaveAsPDF.saveToPDF(textArea,frame);
        }

        else if (event.equals("Print")) {
            try {
                // print the file
                textArea.print();
            }
            catch (Exception evt) {
                JOptionPane.showMessageDialog(frame, evt.getMessage());
            }
        }
        else if (event.equals("New")){
            //display new window at new coordinates
            x += 15;
            y += 15;
            newWindow(x,y);
        }

        else if (event.equals("Search")) {

//
            //Removes existing highlights
            Highlighter h = textArea.getHighlighter();
            h.removeAllHighlights();
            //Show popup window asking user to input a word
            String word = JOptionPane.showInputDialog("Enter search word: \nSingle word searches only");
            int cycles = Search.searchWindow(textArea,word);
            if (cycles != 0) {
                JOptionPane.showMessageDialog(null,cycles+" matches found.");
            }
        }

        else if (event.equals("About")) {
            JOptionPane.showMessageDialog(null, "Developed by Chris McDonald & Holly Ducoing\nThank you for using our Text Editor!", "About", JOptionPane.INFORMATION_MESSAGE);
        }

        else if (event.equals("Exit")) {
            System.exit(0);
        }

    }
}
