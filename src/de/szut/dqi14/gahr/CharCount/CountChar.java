package de.szut.dqi14.gahr.CharCount;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


class CountChar {

    public static void main(String[] args) throws IOException {
        Map<Character, Integer> letters = Read();
        LinkedHashMap<Character, Integer> sortedLetters = SortMap(letters);
        Write(sortedLetters, CountLetters(letters));
        System.out.println("Ende");
    }

    private static Map<Character, Integer> Read() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Rl2.txt"));
        List<Character> nonASCII = new ArrayList<>();
        Map<Character, Integer> letters = new HashMap<>();
        String line;
        char[] carray;
        int temp;
        while ((line = br.readLine()) != null) {
            carray = line.toCharArray();
            for (char letter : carray) {
                if ((int) letter > 127) {
                    if (!nonASCII.contains(letter)) {
                        System.out.println(letter + " ist leider kein ASCII Zeichen und wurde übersprungen");
                        nonASCII.add(letter);
                    }
                } else if (letters.containsKey(letter)) {
                    temp = letters.get(letter);
                    letters.put(letter, temp + 1);
                } else {
                    letters.put(letter, 1);
                }
            }
        }
        br.close();
        return letters;
    }

    private static void Write(LinkedHashMap<Character, Integer> sortedLetters, int total) throws IOException {
        float perc = 0.00f;
        BufferedWriter bw = new BufferedWriter(new FileWriter("res.txt"));
        int letteramount;
        String let;
        String toWrite = "";
        float percent;
        char c;
        for (Map.Entry<Character, Integer> entry : sortedLetters.entrySet()) {
            c = entry.getKey();
            if ((int) c == 9) {
                let = "tabulator";
            } else {
                let = "" + c;
            }
            letteramount = entry.getValue();
            percent = Percents(letteramount, total);
            perc = perc + percent;
            if (let.equals(" ")) {
                let = "whitespace";
            }
            toWrite = String.format("|%10s|%8s|%12s|\n", String.format("%10s", let), letteramount, percent) + toWrite;
        }
        toWrite = "|  letter  | amount | percentage |\n|‾‾‾‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾|‾‾‾‾‾‾‾‾‾‾‾‾‾‾|\n"
                + toWrite
                + "|==========|========|============|\n"
                + String.format("|%10s|%8s|%12s|\n", sortedLetters.size(), total, perc)
                + "‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
        bw.write(toWrite);
        bw.close();
    }

    private static int CountLetters(Map<Character, Integer> letters) {
        int total = 0;
        int letteramount;
        for (Map.Entry<Character, Integer> entry : letters.entrySet()) {
            letteramount = entry.getValue();
            total = total + letteramount;
        }
        return total;
    }

    private static LinkedHashMap<Character, Integer> SortMap(Map<Character, Integer> letters) {
        return letters.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey
                        , Map.Entry::getValue, (e1, e2) -> e1
                        , LinkedHashMap::new));
    }

    private static float Percents(float amount, float total) {
        return Math.round(((amount / total) * 100) * 100) / 100f;
    }
}