package assignment1;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;



public class StudentListEditor{
    private static JFrame frame;
    private static JPanel studentListPanel;
    private static JPanel toolbar;
    private static JPanel textBoxPanel;
    private static JTextField firstname;
    private static JTextField lastname;
    private static JTextField dob;
    private static JTextField address;
    private static JTextField course;
    private static JComboBox course2;

    public static void main(String[] args) {
        // Invokes the window which extends to the gui program
        createWindow();
    }
    private static void createWindow() {
        // Creates swing window
        frame = new JFrame();
        frame.setTitle("Assignment 1");
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 500);
        //Invokes the GUI
        createUI(frame);
        frame.setVisible(true);
        // Closes the program
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


        private static void createUI(final JFrame frame){
        DefaultListModel model = new DefaultListModel();
        List<List> list=new ArrayList<>();
        List<Student> students= new ArrayList<>();
        List<Address> addresses= new ArrayList<>();
        List<Course> courses= new ArrayList<>();
        String[] courseNList = {"01", "02", "03"};


        //Toolbar along the top
        toolbar = new JPanel();
        toolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        Listener action = new Listener();
        JButton exit = new JButton("Exit");
        JButton load = new JButton("Load File");
        JButton save = new JButton("Save File");
        JButton View = new JButton("View");
        JButton add = new JButton("Add To List");
        JButton clone = new JButton("Clone");
        JButton delete = new JButton("Delete");
        exit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        } );

//Shows entire list of students available
        View.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog( frame, "All Listed Students: " + "\n" + model);
            }
        } );
//Adds entered text to list
        add.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fname2 = firstname.getText();
                String lastnameText = lastname.getText();
                String dobText = dob.getText();
                String addressText = address.getText();
                String courseNText = (String) course2.getSelectedItem();
                String courseText = (String) course.getText();
                Student addStudent = new Student();
                Address addAddress = new Address();
                Course addCourse = new Course();
                addStudent.setFirstname( fname2 );
                addStudent.setSurname( lastnameText );
                addStudent.setDob( dobText );;
                addAddress.setStreet(addressText);
                addCourse.setNumber( courseNText );
                addCourse.setCoursename( courseText );
                students.add( addStudent );
                courses.add( addCourse );
                addresses.add( addAddress );
                model.addElement(students.toString() + addresses.toString() + courses);
                students.clear();
                courses.clear();
                addresses.clear();
                firstname.setText( "" );
                lastname.setText( "" );
                dob.setText( "" );
                address.setText( "" );
                course.setText( "" );

            }
        } );
        toolbar.add(exit);
        toolbar.add(load);
        toolbar.add(save);
        toolbar.add(new JLabel(" | "));
        toolbar.add(View);
        toolbar.add(add);
        toolbar.add(clone);
        toolbar.add(delete);
        frame.getContentPane().add(toolbar,BorderLayout.NORTH);
        //
        // Left hand list
        studentListPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JList JList = new JList( model);
        studentListPanel.add(JList);
        JScrollPane scrollPane = new JScrollPane(JList);
        studentListPanel.add( scrollPane );
        JList.setLayoutOrientation( javax.swing.JList.VERTICAL );
        scrollPane.setPreferredSize( new Dimension(500,400) );
//Clones a selected list item
            clone.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> selectedValuesList = JList.getSelectedValuesList();
                        Student addStudent = new Student();
                        addStudent.equals( selectedValuesList );
                        model.addElement( selectedValuesList );
                        students.clear();
                        courses.clear();
                        addresses.clear();
                }

            } );
            delete.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    List<String> selectedValuesList = JList.getSelectedValuesList();
                    int index = JList.getSelectedIndex();
                    model.remove(index);

                }} );
//TODO load a file and add data
            load.addActionListener( new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        char data = StudentStorage.load( new File( "file.bin" ) ) ;
                        List<Character> array= new ArrayList<>();
                        array.add(data);
                        JOptionPane.showMessageDialog( frame, "Your file data: " + array);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            } );
//Saves list to a bin file
        save.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collection collect = new ArrayList();
                collect.add( model );
                try {
                    StudentStorage.save(collect, "file.bin");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } );

        studentListPanel.setSize(200,500);
        Border leftborder = new LineBorder(Color.GRAY,2,true);
        studentListPanel.setBorder(leftborder);
        studentListPanel.setBackground(Color.WHITE);
        //
        //Right hand text boxes
        textBoxPanel = new JPanel();
        textBoxPanel.setLayout(new BoxLayout(textBoxPanel,BoxLayout.PAGE_AXIS));

        firstname = new JTextField();
        lastname = new JTextField();
        dob = new JTextField();
        address = new JTextField();
        course = new JTextField();
        course2 = new JComboBox(courseNList);
        JPanel coursePanel = new JPanel();
        coursePanel.add(course2);
        coursePanel.add(course);
        coursePanel.setLayout(new BoxLayout(coursePanel,BoxLayout.PAGE_AXIS));
        JPanel p =new JPanel();
        p.setBorder(new EmptyBorder(40, 10, 40, 10));
        textBoxPanel.setMaximumSize(textBoxPanel.getPreferredSize());
        textBoxPanel.add(new JLabel("First Name:"));
        textBoxPanel.add(firstname);
        textBoxPanel.add( p );
        textBoxPanel.add(new JLabel("Surname:"));
        textBoxPanel.add(lastname);
        textBoxPanel.add( p );
        textBoxPanel.add(new JLabel("Date Of Birth:"));
        textBoxPanel.add(dob);
        textBoxPanel.add( p );
        textBoxPanel.add(new JLabel("Address:"));
        textBoxPanel.add(address);
        textBoxPanel.add( p );
        textBoxPanel.add(new JLabel("Course Name and ID:"));
        textBoxPanel.add( coursePanel );
        textBoxPanel.add( p );



        textBoxPanel.setBorder(leftborder);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, studentListPanel, textBoxPanel);
        splitPane.setDividerLocation(150);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);


        //Footer bar
        frame.getContentPane().add(new JLabel("Holly DuCoing"),BorderLayout.SOUTH);
        //
    }

    public static JFrame getFrame() {
        return frame;
    }

    public static void setFrame(JFrame frame) {
        StudentListEditor.frame = frame;
    }

    public static JPanel getStudentListPanel() {
        return studentListPanel;
    }

    public static void setStudentListPanel(JPanel studentListPanel) {
        StudentListEditor.studentListPanel = studentListPanel;
    }

    public static JPanel getToolbar() {
        return toolbar;
    }

    public static void setToolbar(JPanel toolbar) {
        StudentListEditor.toolbar = toolbar;
    }

    public static JPanel getTextBoxPanel() {
        return textBoxPanel;
    }

    public static void setTextBoxPanel(JPanel textBoxPanel) {
        StudentListEditor.textBoxPanel = textBoxPanel;
    }

    public static JTextField getFirstname() {
        return firstname;
    }

    public static void setFirstname(JTextField firstname) {
        StudentListEditor.firstname = firstname;
    }

    public static JTextField getLastname() {
        return lastname;
    }

    public static void setLastname(JTextField lastname) {
        StudentListEditor.lastname = lastname;
    }

    public static JTextField getDob() {
        return dob;
    }

    public static void setDob(JTextField dob) {
        StudentListEditor.dob = dob;
    }

    public static JTextField getAddress() {
        return address;
    }

    public static void setAddress(JTextField address) {
        StudentListEditor.address = address;
    }

    public static JTextField getCourse() {
        return course;
    }

    public static void setCourse(JTextField course) {
        StudentListEditor.course = course;
    }




}


