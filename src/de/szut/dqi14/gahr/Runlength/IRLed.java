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
	/* compressed file.txt extension*/
	@SuppressWarnings("unused")
	String EXT = "rld";

	/* set input file.txt name*/
	@SuppressWarnings({"EmptyMethod", "unused"})
	void setInfile(@SuppressWarnings("UnusedParameters") String filename);
	/* get input file.txt name*/
	@SuppressWarnings("unused")
	String getInfile();
	/* set output file.txt name*/
	@SuppressWarnings("unused")
	void setOutfile(@SuppressWarnings({"SameParameterValue", "UnusedParameters"}) String filename);
	/* get output file.txt name*/
	@SuppressWarnings("unused")
	String getOutfile();
	
	/* encode text file.txt and generate compressed file.txt*/
	@SuppressWarnings({"SameReturnValue", "unused"})
	int encodeFile() throws RLedException;
	/* restore compressed file.txt into text file.txt*/
	@SuppressWarnings({"SameReturnValue", "unused"})
	int decodeFile() throws RLedException;
}
