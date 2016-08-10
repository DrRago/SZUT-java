package de.szut.dqi14.gahr.E2.Operator;

import java.util.ArrayList;
import java.util.List;

class Calculations {
    public static void main(String[] args){
        List<Operator> res = new ArrayList<>();
        res.add(new Add());
        res.add(new Substract());
        res.add(new Squareroot(-3.0f));
        System.out.println(new Add());

        for (Operator re : res) {
            System.out.println(re.result());
        }
    }
}
