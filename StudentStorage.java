package assignment1;
import javax.swing.*;
import java.io.*;
import java.io.IOException;
import java.util.Collection;

public class StudentStorage {

    static void save(Collection<Student> students, String fileName) throws IOException {
//Creates a file output stream to write to the file with the specified name.
        try {
            // Creates file and outputs confirmation
            ObjectOutputStream objectStream = new ObjectOutputStream( new FileOutputStream( fileName ) );
            objectStream.writeObject( students );
            System.out.println( "Successful writing of file2" );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static char load(File file) throws IOException {
        // create a reader
        FileInputStream fis = new FileInputStream( new File( "file.bin" ) );
        BufferedInputStream reader = new BufferedInputStream( fis );

        // read one byte at a time
        int ch;
        while ((ch = reader.read()) != -1) {
            return ((char) ch);
        }
        // close the reader
        reader.close();

        return 0;
    }
}




