package de.szut.dqi14.gahr.E2.Operator;

public class Add implements Operator {
    private final float f1;
    private final float f2;

    public Add(){
        this.f1 = 3.0f;
        this.f2 = 7.5f;
    }
    public float result() {
        return f1 + f2;
    }
}
