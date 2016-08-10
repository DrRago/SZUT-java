package de.szut.dqi14.gahr.E2.HashCode;

class Hash {

    @SuppressWarnings("unused")
    public int hash (String input) {
        int hash = input.length();
        for (int iterator = 1; input.length() > iterator - 1; iterator ++){
            hash += input.charAt(iterator - 1) % iterator;
        }
        return hash % 0x10;
    }

    public int[] hash(String[] input){
        int[] hash = new int[input.length];
        for(int i = 0; i < input.length; i ++) {
            hash[i] = input[i].length();
            for(int e = 1; e - 1 < input[i].length(); e ++) {
                hash[i] += input[i].charAt(e - 1) % e;
            }
            hash[i] = hash[i] % 0x10;
        }
        return hash;
    }
}
