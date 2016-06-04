package de.szut.dqi14.gahr.Addition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Addition {
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Zahl 1:");
        String text = input.readLine();
        int num1 = Integer.parseInt(text);
        System.out.print("Zahl 2:");
        text = input.readLine();
        int num2 = Integer.parseInt(text);
        System.out.println("Summe: " + (num1 + num2));
    }
}
