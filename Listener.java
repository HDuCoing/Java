package assignment1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


public class Listener implements ActionListener{
    private Component frame;
    private Component textBoxPanel;

    @Override
    public void actionPerformed(ActionEvent e) {

        StudentListEditor studentlisteditor = new StudentListEditor();
        StudentListEditor guiItems = new StudentListEditor();
        StudentStorage storage = new StudentStorage();
        Student st1 = new Student();
        Address a1 = new Address();
        Course c1 = new Course();
        // Student items
        String first = st1.getFirstname();
        String last = st1.getSurname();
        String dob1 = st1.getDob();
        //
        //Address items
        String hnumber = a1.getHousenumber();
        String town = a1.getTown();
        String street = a1.getStreet();
        String postcode = a1.getPostcode();
        //
//TODO deal with course class last
        String coursename = c1.getName();
        String coursenumber = c1.getNumber();
        //
        //Gui items

        String buttonName = e.getActionCommand();
        switch (buttonName) {
            case "Load File":
//TODO Open a file
                JOptionPane.showMessageDialog(frame, "Load File Clicked");
                break;
            case "Save File":
//TODO Save all students in the list to a file
                JOptionPane.showMessageDialog(frame, "Save File Clicked");
                break;
            default:
                JOptionPane.showMessageDialog(frame, "Unknown event");
                break;
        }

    }

}
