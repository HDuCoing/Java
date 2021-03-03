package com.company;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

public class sum {
//this loads the file and puts it into strings
    private static ArrayList<String> loadFile(File f) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(f));
//creates strings and puts them into an Array
        ArrayList<String> lines = new ArrayList<>();
        String temp;
        while ((temp = in.readLine()) != null) {
            lines.add(temp);
        }
        return lines;
}
    public static void main(String[] args) throws IOException {
        ArrayList<String> lines = loadFile(new File("Numbers.txt"));
//Prints out the numbers contained in the file
        System.out.println(lines);
//Calls cast2ints and cast2doubles to sum up the array
        int[] integers = cast2Ints(lines.get(0));
        double[] doubles = cast2Doubles(lines.get(2));
        double sum = 0.0D;
        for(int i: integers)
            sum+= i;
        for (double d: doubles)
            sum += d;
        System.out.println(sum);




    }

    private static int[] cast2Ints(String line) {
        String[] temp = line.split(" "); //splits on whitespace
        int[] results = new int[temp.length]; //get new array ready

        for (int i = 0; i < temp.length; i++) {
            results[i] = Integer.parseInt(temp[i]);

        }
        return results;
    }

    private static double[] cast2Doubles(String line) {
        String[] temp = line.split(" "); //splits on whitespace
        double[] results = new double[temp.length]; //get new array ready

        for (int i = 0; i < temp.length; i++) {
            results[i] = Double.parseDouble(temp[i]);
        }
        return results;
    }

}
