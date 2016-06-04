package de.szut.dqi14.gahr.FloatList;

public class FloatListElement {
	final float mValue;
	private FloatListElement mNext;
	
	@SuppressWarnings("unused")
	public FloatListElement(float value) {
		mValue = value;
	}
	
	public float getValue() {
		return mValue;
	}
	
	public void setNext(FloatListElement next) {
		mNext = next;
	}
	
	public FloatListElement getNext() {
		return mNext;
	}
}
