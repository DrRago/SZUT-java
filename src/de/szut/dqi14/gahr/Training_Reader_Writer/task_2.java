package de.szut.dqi14.gahr.Training_Reader_Writer;

import java.io.*;

class task_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("Rl2.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("res.txt"));
        File myFile = new File("Rl2.txt");
        int durch = (int) myFile.length()/5;
        System.out.println(durch);
        char[] letters = new char[5];
        input.read(letters, 0, 5);
        int count = 1;
        while (count <= durch) {
            for (int i: letters) {
                System.out.println(i);
            }
            output.write(letters);
            letters = new char[5];
            input.read(letters, 0, 5);
            count ++;
        }
        char[] lastletters = new char[3];
        input.read(lastletters, 0, 3);
        for (int i: lastletters) {
            System.out.println(i);
        }
        output.write(lastletters);
        input.close();
        output.close();
    }
}
