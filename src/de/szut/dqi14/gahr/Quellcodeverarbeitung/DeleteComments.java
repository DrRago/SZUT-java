package de.szut.dqi14.gahr.Quellcodeverarbeitung;

import org.apache.commons.cli.ParseException;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class DeleteComments extends JFrame {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";

    private static String comments = "↓- comments & JavaDoc -↓\n\n";
    private static int commentsCounter = 0;
    private static int lineCounter = 0;

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        // initialize commons cli
        Param.param(args);

        final String dividingLineColor = ANSI_PURPLE;

        final String highlightedColor = ANSI_CYAN;

        // initialize gui
        System.out.println("Starting GUI...");
        DeleteCommentsGui gui = new DeleteCommentsGui();
        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // set in/out name of the files
        System.out.println("Getting inputfile path...");
        String inFilePath = getInFilePath();
        System.out.println("Inputfile path is: " + highlightedColor + inFilePath + ANSI_RESET);

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        System.out.println("Generating outputfile path...");
        String outFilePath = getOutFilePath(inFilePath);
        System.out.println("Outputfile path is: " + highlightedColor + outFilePath + ANSI_RESET);

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // call method read to get the content of the file.txt
        System.out.println("Reading " + inFilePath + "...");
        String file = read(inFilePath);
        System.out.println("Reading completed (" + highlightedColor + file.length() + " bytes" + ANSI_RESET + ")");

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // remove comments from the file.txt
        System.out.println("Removing comments & JavaDoc...");
        file = removeComments(file);
        System.out.println(highlightedColor + commentsCounter + ANSI_RESET + " comments & JavaDoc removed");

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // remove empty lines from the file.txt
        System.out.println("Removing empty lines...");
        file = removeLines(file);
        System.out.println(highlightedColor + lineCounter + ANSI_RESET + " empty lines removed");

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // change className
        System.out.println("Changing classname...");
        file = changeClassName(file);
        System.out.println("Classname changed");

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // TODO edit GUI display type
        System.out.println("Printing comments, JavaDoc and the new file in GUI...");
        comments += "↑- comments & JavaDoc -↑\n\n\n↓- new code -↓\n\n" + file + "\n↑- new code -↑\n\nNew file is: " + outFilePath;
        gui.write(comments);
        while (gui.tArea.getDocument().getLength() < comments.length()) {
            Thread.sleep(1);  // random bug appears, so we wait 1 millisecond
        }
        System.out.println("Printing completed");

        System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

        // write the new file.txt
        System.out.println("Writing new file: " + outFilePath + "...");
        write((file), outFilePath);
        System.out.println("File " + highlightedColor + outFilePath + ANSI_RESET + " created successfully");

        // write a csv-file.txt containing every occurrence of given keywords if commandline option "csv" is given
        if (Param.csv != null) {
            // count keyWords in a the file.txt
            System.out.println("Counting keywords in file...");
            Map<String, Integer> keywordOccurrences = countKeywords(file);
            System.out.println("Keywords counted");

            System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

            // sort HashMap
            System.out.println("Sorting counted keywords descending...");
            ValueComparator bvc = new ValueComparator(keywordOccurrences);
            TreeMap<String, Integer> sortedKeywordOccurrences = new TreeMap<>(bvc);
            System.out.println("Keywords sorted");

            sortedKeywordOccurrences.putAll(keywordOccurrences);

            System.out.println(dividingLineColor + "------------------------------------------------------------" + ANSI_RESET);

            // write csv-file.txt
            System.out.println("Writing csv-file " + Param.csv);
            writeCSV(sortedKeywordOccurrences, Param.csv);
            System.out.println("csv-file " + highlightedColor + Param.csv + ANSI_RESET + "created");
        }

        System.out.println("\n" + ANSI_GREEN + "Program finished" + ANSI_RESET);
    }

    private static String changeClassName(String file) {
        /* replaces all classname occurrences in the sourcecode */

        String[] splitFileName = Param.file.split(Pattern.quote("."));
        String className = splitFileName[splitFileName.length - 2];

        file = file.replace(className, className + "NoComments");

        return file;
    }

    private static String removeLines(String file) {
        /* removes all empty lines in a String */

        String result = "";

        // use scanner to iterate through a string
        Scanner s = new Scanner(file);

        int lines = file.split("\r\n|\r|\n").length;

        // loop to iterate through the string each line
        while (s.hasNextLine()) {
            String line = s.nextLine();

            // use second scanner to iterate through the line
            Scanner s2 = new Scanner(line);
            s2.useDelimiter("");

            // loop to iterate through the line
            while (s2.hasNext()) {
                String c = s2.next();

                // if the line contains a character that is printable, add the whole line to a resultString
                if ((byte) c.charAt(0) > 0x20) {
                    result += line + "\n";
                    break;
                }
            }
        }
        lineCounter = lines - result.split("\r\n|\r|\n").length;
        return result;
    }

    private static void writeCSV(TreeMap<String, Integer> treeMap, String outFilePath) throws IOException {
        /* writes a csv-file.txt containing the content of a TreeMap */

        BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));

        // write header
        bw.write("keyword;occurrences");

        // write everything in the TreeMap
        for(Map.Entry<String,Integer> entry : treeMap.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            bw.write("\n" + key + ";" + value);
        }
        // finish writing

        bw.close();
    }

    private static void write(String result, String outFilePath) throws IOException {
        /* writes a given string into a given file.txt */

        BufferedWriter bw = new BufferedWriter(new FileWriter(outFilePath));
        bw.write(result);
        bw.close();
    }

    private static String removeComments(String code){
        /* removes every comment and JavaDoc in a given String */

        // declare final variables as states
        final int noComment = 0;
        final int inLineComment = 1;
        final int inBlockComment = 2;
        final int inString = 3;

        int currentState = noComment;

        String result = "";
        String comment = "";

        // use scanner to iterate through a string
        Scanner s = new Scanner(code);

        // set delimiter to "" to get every letter
        s.useDelimiter("");

        // begin filter
        while (s.hasNext()) {
            String c = s.next();
            switch (currentState) {
                case noComment:
                    // exception if the next letter is a character (necessary if the character contains a quotation mark)
                    if (c.equals("'")) {
                        String c2 = s.next();
                        if (c2.equals("\\")) {
                            result += c + c2 + s.next() + s.next();
                        } else {
                            result += c + c2 + s.next();
                        }
                    } else if (c.equals("/") && s.hasNext()) {
                        String c2 = s.next();
                        switch (c2) {
                            case "/":
                                comment = c + c2;
                                currentState = inLineComment;
                                break;
                            case "*":
                                comment = c + c2;
                                currentState = inBlockComment;
                                break;
                            default:
                                result += c + c2;
                                break;
                        }
                    } else if (c.equals("\"")) {
                        currentState = inString;
                        result += c;
                    } else {
                        result += c;
                    }
                    break;
                case inLineComment:
                    // save the comment letter in a string
                    comment += c;
                    if (c.equals("\n")) {
                        currentState = noComment;
                        // print the comment and clear the string
                        comments += comment + "\n";
                        commentsCounter ++;
                        comment = "";
                        result += c;
                    }
                    break;
                case inString:
                    switch (c) {
                        case "\\":
                            result += c + s.next();
                            break;
                        case "\"":
                            currentState = noComment;
                            result += c;
                            break;
                        default:
                            result += c;
                            break;
                    }
                    break;
                case inBlockComment:
                    // save the comment letter in a string
                    comment += c;
                    while (c.equals("*") && s.hasNext()) {
                        String c2 = s.next();
                        if (c2.equals("/")) {
                            // print the comment and clear the string
                            comment += c2;
                            comments += comment + "\n\n";
                            commentsCounter ++;
                            comment = "";
                            currentState = noComment;
                            break;
                        } else {
                            comment += c2;
                        }
                    }
            }
        }


        // finished filtering

        s.close();

        return result;
    }

    private static Map<String, Integer> countKeywords(String str) {
        /* this method counts keywords in a given String */

        // replace disturbing characters with spaces
        str = str.replace("\t", " ");
        str = str.replaceAll(Pattern.quote("{"), " ");
        str = str.replace("(", " ");

        // set StringArray of keywords to search for
        String[] keywords = {
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
                "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if",
                "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private",
                "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized",
                "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};

        // HashMap saves the Keyword and the number of occurrences
        Map<String, Integer> keyOccurrences = new HashMap<>();

        int i = 0;

        // search for every entry of the stringArray in a given string
        for (String keyword: keywords) {
            Pattern p = Pattern.compile(" " + keyword + " ");
            Matcher m = p.matcher(str);
            while (m.find()) {
                i++;
            }
            // add the results to the HashMap
            keyOccurrences.put(keyword, i);
            i = 0;
        }
        // count finished
        return keyOccurrences;
    }

    private static String getInFilePath() {
        /* gets the filename from Param */
        return Param.file;
    }

    private static String getOutFilePath(String inFilePath) {
    /* sets the name of the outfile*/
        String outFilePath = "";

        // split the filePath at every "." in a list
        List<String> tokens = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(inFilePath, ".");
        String token;
        while (st.hasMoreTokens()){
            token = st.nextToken();
            tokens.add(token);
        }
        // end of splitting filePath

        // fetching the new name except of the last two
        for (int i = 0; i < tokens.size()-2; i++){
            outFilePath += tokens.get(i) + ".";
        }

        // adding the penultimate token, NoComments and the last token to the outputfile
        outFilePath += tokens.get(tokens.size()-2) + "NoComments." + tokens.get(tokens.size()-1);
        return outFilePath;
    }

    private static String read(String inFilePath) throws IOException {
        /* reads a given file.txt and returns a string containing the File */

        // read the file.txt into an byteArray, convert the byteArray to a String and return it
        return new String(Files.readAllBytes(Paths.get(inFilePath)), "UTF-8");
    }
}