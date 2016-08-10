package de.szut.dqi14.gahr.E2.HashCode;

class MainHash {
    public static void main(String[] args){
        String[] names = {"Maximilian", "Gianluca", "Marcel", "Marius", "Nicolas", "Alexander", "Swantje", "Leonhard",
        "Sönke", "Pascal", "Robin", "Maik", "Lennard", "Joscha", "Trung"};
        Hash myHash = new Hash();
        int[] hashes = myHash.hash(names);
        for (int i = 0; i < hashes.length; i ++) {
            System.out.println(names[i] + ": " + hashes[i]);
        }
    }
}
