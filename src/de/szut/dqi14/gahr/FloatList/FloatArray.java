package de.szut.dqi14.gahr.FloatList;

class FloatArray implements FloatInterface {
    private float[] myArray = new float[0];

    @Override
    public float getFloat(int index) throws IndexOutOfBoundsException {
        if (myArray.length < index || index < 0){
            throw new IndexOutOfBoundsException();
        }
        return myArray[index];
    }

    @Override
    public void appendFloat(float value) throws IndexOutOfBoundsException {
        float newArray[] = new float[myArray.length + 1];
        int count = 0;
        for (float i: myArray){
            newArray[count] = i;
            count ++;
        }
        newArray[myArray.length] = value;
        myArray = newArray;
    }

    @Override
    public void insertFloat(float value, int index) throws IndexOutOfBoundsException {
        if (myArray.length < index || index < 0){
            throw new IndexOutOfBoundsException();
        }
        float newArray[] = new float[myArray.length + 1];
        for (int i = 0; myArray.length > i; i++){
            if (i < index){
                newArray[i] = myArray[i];
            } else if (i == index){
                newArray[i] = value;
            } else{
                newArray[i] = myArray[i - 1];
            }
        }
        myArray = newArray;
    }

    @Override
    public void removeFloat(int index) throws IndexOutOfBoundsException {
        if (myArray.length < index || index < 0){
            throw new IndexOutOfBoundsException();
        }
        float newArray[] = new float[myArray.length - 1];
        for (int i = 0; myArray.length > i; i++){
            if (i < index){
                newArray[i] = myArray[i];
            } else {
                newArray[i] = myArray[i + 1];
            }
        }
        myArray = newArray;
    }

    @Override
    public int getLength() {
        return myArray.length;
    }

    @Override
    public void print() {
        if (myArray != null) {
            for (float i : myArray) {
                System.out.println(i);
            }
        }
    }
}
