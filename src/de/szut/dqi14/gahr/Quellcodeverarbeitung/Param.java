package de.szut.dqi14.gahr.Quellcodeverarbeitung;

import org.apache.commons.cli.*;

class Param {
    //declaration of static variables for the commandline arguments
    static String file;
    static String csv;

    static void param(String args[]) throws ParseException{
        //declaration of needed options
        Options cliOptions = new Options();

        Option optionFile = new Option("f", "file", true, "select path of the infile");
        Option optionCSV = new Option("c", "csv_file", true, "csv-file contains the counted keywords");

        //declare optionFile as required Option
        optionFile.setRequired(true);

        //adding all options to cliOption
        cliOptions.addOption(optionCSV);
        cliOptions.addOption(optionFile);

        // read the commandline arguments and save them into the static variables
        CommandLine lvCmd;
        CommandLineParser lvParser = new DefaultParser();
        lvCmd = lvParser.parse(cliOptions, args);

        file = lvCmd.getOptionValue("file");
        csv = lvCmd.getOptionValue("csv_file");
    }
}