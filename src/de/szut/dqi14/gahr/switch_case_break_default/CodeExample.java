package de.szut.dqi14.gahr.switch_case_break_default;

import java.util.Random;

class CodeExample {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        //noinspection InfiniteLoopStatement
        while (true) {
            int number = random.nextInt(7) + 1;
            switch (number) {
                case 1:
                    System.out.println("     ");
                    System.out.println("  *  ");
                    System.out.println("     ");
                    break;
                case 2:
                    System.out.println("*    ");
                    System.out.println("     ");
                    System.out.println("    *");
                    break;
                case 3:
                    System.out.println("*    ");
                    System.out.println("  *  ");
                    System.out.println("    *");
                    break;
                case 4:
                    System.out.println("*   *");
                    System.out.println("     ");
                    System.out.println("*   *");
                    break;
                case 5:
                    System.out.println("*   *");
                    System.out.println("  *  ");
                    System.out.println("*   *");
                    break;
                case 6:
                    System.out.println("*   *");
                    System.out.println("*   *");
                    System.out.println("*   *");
                    break;
                default:
                    System.out.println("Lern würfeln");
                    break;
            }
            Thread.sleep(100);
            System.out.println("-----");
        }
    }
}