package de.szut.dqi14.gahr.Runlength;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Rl3 implements IRLed{

    // declaring global variables from the content of the commandline arguments
    private final String inName = Param.file;
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

    private static int[] read(String name) throws IOException {
        /* reads the inputFile and saves every byte into an intArray and returns it */
        BufferedReader input = new BufferedReader(new FileReader(name));
        int value;
        int[] inputArray = new int[1];
        inputArray[0] = input.read();
        while ((value = input.read()) != -1) {
            int n = inputArray.length;
            inputArray = Arrays.copyOf(inputArray, n + 1);
            inputArray[n] = value;
            System.out.println(n);
        }
        input.close();
        return inputArray;
    }

    private void writeEncode(int[] toWrite) throws IOException {
        /* writes an intArray in the outputFile without clearing it
         * this method is only called in case of a compression */
        BufferedWriter output = new BufferedWriter(new FileWriter(this.outName,true));
        if(toWrite[1] == 0 && toWrite[0] !=  144){
            output.write(toWrite[0]);
        }

        else if(toWrite[1] == 0){
            output.write(toWrite[0]);
            output.write(toWrite[1]);
        }

        else if(toWrite[2] == 0 ){
            output.write(toWrite[0]);
            output.write(toWrite[1]);
        }

        else{
            for(int e:toWrite) {
                output.write(e);
            }
        }
        output.close();
    }

    private void writeDecode(int[] toWrite) throws IOException {
        /* writes an intArray in the outputFile without clearing it
         * this method is only called in case of a uncompression */
        BufferedWriter output = new BufferedWriter(new FileWriter(this.outName,true));
        for(int count = 0; count < toWrite[1]; count++){
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
        return this.inName;
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
         * then calls this method the write-method and hands over an intArray with all the information about the compression */
        int inputArray[];
        try {
            inputArray = read(this.inName);

            // deletes the destinationfile if it exists
            Path path = FileSystems.getDefault().getPath(this.outName);
            Files.deleteIfExists(path);
            // finnish deletion

            int toWrite[] = new int[3];
            int count = 1;
            int temp = inputArray[0];

            // checks every character if it's multiple and calls the write-method to write the character and, if necessary, the counter
            for (int i = 1; inputArray.length > i; i++) {
                if (inputArray[i] != temp){
                    if (count > 2) {
                        toWrite[0] = 144;
                        toWrite[1] = count;
                        toWrite[2] = temp;
                    }
                    else if (count == 2) {
                        if (temp == 144) {
                            toWrite[0] = temp;
                            toWrite[1] = count;
                            toWrite[2] = temp;
                        } else {
                            toWrite[0] = temp;
                            toWrite[1] = temp;
                            toWrite[2] = 0;
                        }
                    }
                    else {
                        toWrite[0] = temp;
                        toWrite[1] = 0;
                        toWrite[2] = 0;
                    }
                    writeEncode(toWrite);
                    count = 1;
                }
                else if(inputArray[i] == temp){
                    count++;
                }
                temp = inputArray[i];

                if (i == inputArray.length-1) {
                    if (count > 2) {
                        toWrite[0] =  144;
                        toWrite[1] = count;
                        toWrite[2] = temp;
                    } else if (count == 2) {
                        if (temp == 144) {
                            toWrite[0] = temp;
                            toWrite[1] = count;
                            toWrite[2] = temp;
                        } else {
                            toWrite[0] = temp;
                            toWrite[1] = temp;
                            toWrite[2] = 0;
                        }
                    }
                    else {
                        toWrite[0] = temp;
                        toWrite[1] = 0;
                        toWrite[2] = 0;
                    }
                    writeEncode(toWrite);
                }
            }
            // finished writing

            // throws the RLedException if anny error occurs
        } catch (IOException e) {
            if (!quiet) {
                throw new RLedException(2);
            } else {
                System.out.println("Fehler");
                System.exit(0);
            }
        }
        return 0;
    }

    @Override
    public int decodeFile() throws RLedException {
        /* this method calls the read-method and saves the returned array to check that array with the Runlenght3 - algorithm
         * and it calls the write-method and hands over a intArray with all the information about the uncompression */
        try {
            int[] inputArray = read(this.inName);

            // deletes the destinationfile if it exists
            Path path = FileSystems.getDefault().getPath(outName);
            Files.deleteIfExists(path);
            // finished deletion

            int[] toWrite= new int[2];

            // goes through every character and hands it over to the write method as well as it hands over the counter
            for (int i = 0; inputArray.length > i;) {
                if (inputArray[i] == 144 && inputArray[i + 1] != 0 && inputArray[i + 2] != 144){
                    toWrite[0] = inputArray[i + 2];
                    toWrite[1] = inputArray[i + 1];
                    i+=3;
                }
                else if(inputArray[i] == 144 && inputArray[i+1] == 0){
                    toWrite[0] = inputArray[i];
                    toWrite[1] = 1;
                    i+=2;
                }

                else if (inputArray[i] == 144 && inputArray[i+1] != 0 && inputArray[i+2] == 144){
                    toWrite[0] = inputArray[i];
                    toWrite[1] = inputArray[i+1];
                    i+=3;
                }

                else {
                    toWrite[0] = inputArray[i];
                    toWrite[1] = 1;
                    i ++;
                }
                writeDecode(toWrite);
            }

            // throws the RLedException if anny error occurs
        } catch (IOException e) {
            if (!quiet) {
                throw new RLedException(2);
            } else {
                System.out.println("Fehler");
                System.exit(0);
            }
        }
        return 0;
    }
}
