package de.szut.dqi14.gahr.E2.FloatList;

interface FloatInterface {
	@SuppressWarnings("unused")
	float getFloat(int index) throws IndexOutOfBoundsException;
	void appendFloat(float value) throws IndexOutOfBoundsException;
	void insertFloat(@SuppressWarnings("SameParameterValue") float value, int index) throws IndexOutOfBoundsException;
	void removeFloat(int index) throws IndexOutOfBoundsException;
	@SuppressWarnings("unused")
	int getLength();
	void print();
}
