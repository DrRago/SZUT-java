package de.szut.dqi14.gahr.E2.Training_Reader_Writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class task_1 {
    public static void main(String[] args) throws IOException {
        FileInputStream input = new FileInputStream("Rl2.txt");
        FileOutputStream output = new FileOutputStream("res.txt");
        for (int letter = 0; letter != -1; letter = input.read()) {
            System.out.println((char) letter + ":" + Integer.parseInt("" + letter, 16) + " " + Integer.toBinaryString(letter));
            output.write(letter);
        }
        input.close();
        output.close();
    }
}
