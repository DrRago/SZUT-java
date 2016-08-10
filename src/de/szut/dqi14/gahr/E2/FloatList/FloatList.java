package de.szut.dqi14.gahr.E2.FloatList;

@SuppressWarnings("unused")
class FloatList implements FloatInterface {

	private FloatListElement mFirst;

    @Override
    public float getFloat(int index) throws IndexOutOfBoundsException {
        if (mFirst != null) {
            int count = 0;
            FloatListElement iterator = mFirst;
            while (count < index - 1 && iterator.getNext() != null) {
                iterator = iterator.getNext();
                count++;
            }
            if (iterator.getNext() == null || index < 0) {
                throw new IndexOutOfBoundsException();
            }
            return iterator.getValue();
        } else {
            return 0;
        }
    }

    @Override
    public void appendFloat(float value) throws IndexOutOfBoundsException {
        FloatListElement elementToAdd = new FloatListElement(value);
        if(null == mFirst){
            mFirst = elementToAdd;
        }
        else{
            FloatListElement element = mFirst;
            while (null!= element.getNext()){
                element = element.getNext();
            }
            element.setNext(elementToAdd);
        }
    }

    @Override
    public void insertFloat(float value, int index) throws IndexOutOfBoundsException {
        if (mFirst != null) {
            int count = 0;
            FloatListElement iterator = mFirst;
            FloatListElement elementToInsert = new FloatListElement(value);
            while (count < index - 1 && iterator.getNext() != null){
                iterator = iterator.getNext();
                count ++;
            }
            if (iterator.getNext() == null || index < 0) {
                throw new IndexOutOfBoundsException();
            }
            elementToInsert.setNext(iterator.getNext());
            iterator.setNext(elementToInsert);
        }
    }

    @Override
    public void removeFloat(int index) throws IndexOutOfBoundsException {
        if (mFirst != null) {
            int count = 0;
            FloatListElement iterator = mFirst;
            while (count < index - 1 && iterator.getNext() != null){
                iterator = iterator.getNext();
                count ++;
            }
            if (iterator.getNext() == null || index < 0) {
                throw new IndexOutOfBoundsException();
            }
            iterator.setNext(iterator.getNext().getNext());
        }
    }

    public int getLength() {
        if (null == mFirst) {
            return 0;
        } else {
            int count = 0;
            FloatListElement iterator = mFirst;
            while (iterator != null){
                iterator = iterator.getNext();
                count ++;
            }
            return count;
        }
    }

    @Override
    public void print() {
        if (mFirst != null) {
            FloatListElement iterator = mFirst;
            while (iterator != null){
                System.out.println(iterator.mValue);
                iterator = iterator.getNext();
            }
        }
    }
}
