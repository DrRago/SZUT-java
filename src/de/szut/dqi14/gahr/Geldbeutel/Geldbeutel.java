package de.szut.dqi14.gahr.Geldbeutel;

class Geldbeutel {
    public static void main(String[] args) {
        int euro = 300;
        int fuenfziger = 150;
        int zehner = 50;
        int fuenfer = 10;
        int zweier = 6;
        int einer = 3;
        double summe;
        summe = euro + fuenfziger + zehner + fuenfer + zweier + einer;
        System.out.println("Sie haben in Ihrem Geldbeutel: " + summe + " Cent");
    }
}
