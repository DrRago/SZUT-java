package de.szut.dqi14.gahr.Runlength;

class RLedException extends Exception {
	/* A universal exception for Runlenght which gives out a customized errorcode*/
	RLedException(int a){
		super("Errorcode: " + a );
	}
}
