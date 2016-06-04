package de.szut.dqi14.gahr.Operator;


class Squareroot implements Operator{
    private float x;
    public Squareroot(float f1){
        this.x = f1;
    }

    public float result(){
        if(x < 0){
            x = x * -1;
        }
        return (float) Math.sqrt(x);
    }
}
