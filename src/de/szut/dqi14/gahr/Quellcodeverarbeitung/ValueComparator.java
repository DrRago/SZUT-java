package de.szut.dqi14.gahr.Quellcodeverarbeitung;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<String> {
    private final Map<String, Integer> base;

    ValueComparator(Map<String, Integer> base) {
        this.base = base;
    }

    public int compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}