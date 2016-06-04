package de.szut.dqi14.gahr.Buchstabieren;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Buchstabieren {
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Eingabe:");
        String text = input.readLine();
        for (int i = 0; i < text.length(); i++){
            System.out.println(text.charAt(i));
        }
    }
}
