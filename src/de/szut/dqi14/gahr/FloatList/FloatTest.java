package de.szut.dqi14.gahr.FloatList;

class FloatTest {

	public static void main(String[] args) {
		
		FloatInterface list = new FloatArray();
//		FloatInterface list = new FloatList();
		list.print();
        System.out.println("------------");

        for (int i = 0; i < 3; i++) {
            list.appendFloat((float) i);
		}
		list.print();
        System.out.println("------------");

        int insertIndex[] = { 4, -1, 0, 2, 4, 6};
		for (int anInsertIndex : insertIndex) {
			try {
				list.insertFloat(42.0f, anInsertIndex);
			} catch (IndexOutOfBoundsException exception) {
				System.out.println("inserting float at index " + anInsertIndex + " failed!");
			}
		}
		list.print();
        System.out.println("------------");

		int removeIndex[] = { 7, -1, 6, 4, 2, 0};
		for (int aRemoveIndex : removeIndex) {
			try {
				list.removeFloat(aRemoveIndex);
			} catch (IndexOutOfBoundsException exception) {
				System.out.println("removing float at index " + aRemoveIndex + " failed!");
			}
		}
		list.print();
	}
}
