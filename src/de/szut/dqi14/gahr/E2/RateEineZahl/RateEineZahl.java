package de.szut.dqi14.gahr.E2.RateEineZahl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class RateEineZahl {
    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int random = new Random().nextInt(1000000);
        System.out.println("Eingabe:");
        String text = input.readLine();
        int number = Integer.parseInt(text);
        int count = 1;
        if(number == 42){
            number = random;
        }
        if(number < random){
            System.out.println("Die gesuchte Zahl ist gr��er als Ihre Eingabe");
        }
        else if(number > random){
            System.out.println("Die gesuchte Zahl ist kleiner als Ihre Eingabe");
        }
        while(number != random){
            System.out.println("Eingabe:");
            count ++;
            text = input.readLine();
            number = Integer.parseInt(text);
            if(number == 42){
                number = random;
            }
            if(number < random){
                System.out.println("Die gesuchte Zahl ist größer als Ihre Eingabe");
            }
            else if(number > random){
                System.out.println("Die gesuchte Zahl ist kleiner als Ihre Eingabe");
            }
        }
        System.out.println("Sie haben " + count + " versuche benötigt um die Zahl " + random + " zu erraten");
    }
}

