package de.szut.dqi14.gahr.Runlength;
/**
 * short description
 * 
 */

/**
 * short description
 *
 * @author wolke
 *
 * @version 1.0, 13.10.2011
 *
 */

interface IRLed {
	/* compressed file extension*/
	@SuppressWarnings("unused")
	String EXT = "rld";

	/* set input file name*/
	@SuppressWarnings({"EmptyMethod", "unused"})
	void setInfile(@SuppressWarnings("UnusedParameters") String filename);
	/* get input file name*/
	@SuppressWarnings("unused")
	String getInfile();
	/* set output file name*/
	@SuppressWarnings("unused")
	void setOutfile(@SuppressWarnings({"SameParameterValue", "UnusedParameters"}) String filename);
	/* get output file name*/
	@SuppressWarnings("unused")
	String getOutfile();
	
	/* encode text file and generate compressed file*/
	@SuppressWarnings({"SameReturnValue", "unused"})
	int encodeFile() throws RLedException;
	/* restore compressed file into text file*/
	@SuppressWarnings({"SameReturnValue", "unused"})
	int decodeFile() throws RLedException;
}
