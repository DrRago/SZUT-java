package de.szut.dqi14.gahr.RateEineZahl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ComputerRätEineZahl {
    public static void main(String[] args) throws IOException{
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int min = 0;
        int max = 200;
        int number = (min + max) / 2;
        int count = 1;
        String op = " ";
        while (!op.equals("=")){
            System.out.println(number);
            op = input.readLine();
            switch (op){
                case("<"):
                    max = number;
                    break;
                case(">"):
                    min = number;
                    break;
                case("="):
                    System.out.println("Die Zahl ist: " + number + " und ich habe " + count + " Versuche benötigt");
                    break;
            }            number = (max + min) / 2;
            count ++;
        }
    }
}
