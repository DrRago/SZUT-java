package de.szut.dqi14.gahr.E2.Runlength;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Rl2 implements IRLed{

    //declaring global variables from the content of the commandline arguments
    private static final String inName = Param.file;
    private String outName = "";
    private final String nameExtension = Param.extension;
    private final boolean quiet = Param.quiet;

    void callCompression() throws RLedException {
		/* checks what mode is selected and launches that mode */
        setOutfile("");
        if(Param.compressed){
            encodeFile();
        }
        else if (Param.uncompressed) {
            decodeFile();
        }
    }


    private static byte[] read() throws IOException, RLedException {
        /* reads the inputFile and saves every byte into a byteArray and returns it */
        FileInputStream input = new FileInputStream(inName);
        int fileLength = input.available();
        byte[] inputArray = new byte[fileLength];
        for (int i = 0; i<fileLength;i++){
            byte letter = (byte) input.read();
            if ((int) letter > 127){
                throw new RLedException(4);
            }
            inputArray[i] = letter;
        }
        input.close();
        return inputArray;
    }

    private void write(byte[] toWrite) throws IOException {
        /* writes a byteArray in the outputFile without clearing it */
        FileOutputStream output = new FileOutputStream(outName, true);
        if (toWrite[1] == 0) {
            output.write(toWrite[0]);
        } else {
            output.write(toWrite[1]);
            output.write(toWrite[0]);
        }
        output.close();
    }

    @Override
    public void setInfile(String filename) {
        // unnecessary
    }

    @Override
    public String getInfile() {
        // returns the inputName of the file.txt
        return inName;
    }

    @Override
    public void setOutfile(String filename) {
        /* sets the name of the outputfile with the customized fileextension or with the default "rld" or "txt" */

        // split the inputName at every "." in a list
        List<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(inName, ".");
        String token;
        while (st.hasMoreTokens()){
            token = st.nextToken();
            tokens.add(token);
        }
        // end of splitting inputName

        // extends the outputName by every element in tokens except of the last one
        for (int i = 0; i < tokens.size()-1; i++){
            this.outName += tokens.get(i) + ".";
        }
        // end of extending outputName

        // extends the outputName by the fileextension or by the default "tld" or, in case of an uncompression, by "txt"
        if (Param.compressed){
            if(nameExtension != null){
                this.outName += nameExtension;
            }
            else{
                this.outName += "rld";
            }
        }
        else {
            if (nameExtension != null) {
                this.outName += nameExtension;
            } else {
                this.outName += "txt";
            }
        }
    }

    @Override
    public String getOutfile() {
        // returns the outputName of the file.txt
        return this.outName;
    }

    @Override
	public int encodeFile() throws RLedException {
        /* this method calls the read-method and saves the returned array to check that array for multiple characters
         * then calls this method the write-method and hands over a bytearray with all the information about the compression */
        try {
            byte[] inputArray = read();

            // deletes the destinationfile if it exists
            Path path = FileSystems.getDefault().getPath(outName);
            Files.deleteIfExists(path);
            // finnish deletion

            byte[] toWrite = new byte[2];
            int count = 1;

            byte temp = inputArray[0];

            // checks every character if it's multiple and calls the write-method to write the character and, if necessary, the counter.
            for(int i = 1; inputArray.length > i; i++){
                if (inputArray[i] != temp){
                    if(count > 1){
                        toWrite[0] = temp;
                        toWrite[1] = (byte) (0x80 + count);
                        write(toWrite);
                    }
                    else{
                        toWrite[0] = temp;
                        toWrite[1] = 0;
                        write(toWrite);
                    }
                    temp = inputArray[i];
                    count = 1;
                }
                else{
                    count++;
                }
                if (i == inputArray.length-1){
                    if(count > 1){
                        toWrite[0] = temp;
                        toWrite[1] = (byte) (0x80 + count);
                        write(toWrite);
                    }
                    else{
                        toWrite[0] = temp;
                        toWrite[1] = 0;
                        write(toWrite);
                    }
                }
            }
            // finished writing

            // throws the RLedException if anny error occurs
        } catch (IOException e) {
            if (!quiet) {
                throw new RLedException(2);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        } catch (Exception e) {
            if (!quiet) {
                throw new RLedException(5);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        }
        if (!quiet) {
            System.out.println("Process Finished");
        }
        return 0;
    }

    @Override
    public int decodeFile() throws RLedException {
        /* this method calls the read-method and saves the returned array to check that array with the Runlenght2 - algorithm
         * and it calls the write-method and hands over a bytearray with all the information about the uncompression */
        try {
            byte[] inputArray = read();

            // deletes the destinationfile if it exists
            Path path = FileSystems.getDefault().getPath(outName);
            Files.deleteIfExists(path);
            // finished deletion

            int count = 1;
            byte toWrite[] = new byte[2];

            // goes through every character and hands it over to the write method as often as the read counter says
            for (byte current:inputArray){

                if(current < 0 ){
                    count = current - (byte) 128;
                } else {

                    for(int i = 0; i < count; i++){
                        toWrite[1] = 0;
                        toWrite[0] = current;
                        write(toWrite);
                    }

                    count = 1;
                }
            }
            // throws the RLedException if anny error occurs
        } catch (IOException e) {
            if (!quiet) {
                throw new RLedException(2);
            } else {
                System.out.println("Error");
                System.exit(0);
            }

        } catch (Exception e) {
            if (!quiet) {
                throw new RLedException(5);
            } else {
                System.out.println("Error");
                System.exit(0);
            }
        }

        if (!quiet) {
            System.out.println("Process Finished");
        }
        return 0;
    }
}