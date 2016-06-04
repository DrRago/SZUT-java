package de.szut.dqi14.gahr.Runlength;

import org.apache.commons.cli.*;

class Param {
    //declaration of static variables for the commandline arguments
    static boolean compressed;
    static boolean uncompressed;
    static String file;
    static boolean quiet;
    static String extension;
    static boolean rl2;
    static boolean rl3;

    public static void param(String args[]) throws ParseException{
        //declaration of needed options
        Options cliOptions = new Options();

        Option optionExtension = new Option("e", "extension", true, "fileextension without a dot");
        Option optionQuiet = new Option("q", "quiet", false, "Launch the (de)compression without outputs");
        Option optionCompress = new Option("c", "compress", false, "select, if the file shall be compressed");
        Option optionUncompress = new Option("u", "uncompress", false, "select, if the file shall ve decompressed");
        Option optionFile = new Option("f", "file", true, "select path of the inputfile");
        Option optionRl3 = new Option("Rl3" , "Runlenght3", false, "(de)compress with Runlenght 3");
        Option optionRl2 = new Option("Rl2", "Runlenght2", false, "(de)compress with Runlenght 2");

        //declare optionFile as required Option
        optionFile.isRequired();

        //combine optionRl2 and optionRl3 to a optionGroup
        OptionGroup rlGroup = new OptionGroup();
        rlGroup.addOption(optionRl2);
        rlGroup.addOption(optionRl3);
        rlGroup.isRequired();

        //combine optionCompress and optionUncompress to a optionGroup
        OptionGroup cuGroup = new OptionGroup();
        cuGroup.addOption(optionCompress);
        cuGroup.addOption(optionUncompress);
        cuGroup.isRequired();

        //adding all options and optionGroups as an Option to cliOption
        cliOptions.addOption(optionExtension);
        cliOptions.addOption(optionQuiet);
        cliOptions.addOption(optionFile);
        cliOptions.addOptionGroup(cuGroup);
        cliOptions.addOptionGroup(rlGroup);

        // read the commandline arguments and save them into the static variables
        CommandLine lvCmd;
        CommandLineParser lvParser = new DefaultParser();
        lvCmd = lvParser.parse(cliOptions, args);

        uncompressed = lvCmd.hasOption("u");
        compressed = lvCmd.hasOption("c");
        file = lvCmd.getOptionValue("file");
        quiet = lvCmd.hasOption("q");
        extension = lvCmd.getOptionValue("extension");
        rl2 = lvCmd.hasOption("Rl2");
        rl3 = lvCmd.hasOption("Rl3");
    }
}
