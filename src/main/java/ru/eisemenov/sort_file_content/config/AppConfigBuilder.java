package ru.eisemenov.sort_file_content.config;

import org.apache.commons.cli.*;

import java.io.File;

public class AppConfigBuilder {
    public static AppConfig getConfig(String[] args) throws AppConfigException, ParseException {
        Options cliOptions = getCliOptions();

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        try {
            cmd = parser.parse(cliOptions, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("gnu", cliOptions);
            throw e;
        }

        String path = cmd.getOptionValue("path");
        File filePath = new File(path);
        if (!filePath.exists()) {
            throw new AppConfigException("ERROR: " + path + " doesn't exists");
        }
        if (!filePath.isDirectory()) {
            throw new AppConfigException("ERROR: " + path + " isn't directory");
        }

        String outPrefix = cmd.getOptionValue("out-prefix");
        String contentType = cmd.getOptionValue("content-type");
        ContentType eContentType;
        if (contentType.equals("i")) {
            eContentType = ContentType.INTEGER;
        } else if (contentType.equals("s")) {
            eContentType = ContentType.STRING;
        } else {
            throw new AppConfigException("ERROR: Content type is unknown (can be i - integer or s - string");
        }
        String sortMode = cmd.getOptionValue("sort-mode");
        SortMode eSortMode;
        if (sortMode.equals("a")) {
            eSortMode = SortMode.ASCENDING;
        } else if (sortMode.equals("d")) {
            eSortMode = SortMode.DESCENDING;
        } else {
            throw new AppConfigException("ERROR: Sort mode must be a (ascending) or d (descending)");
        }

        return new AppConfig(new File(path), outPrefix, eContentType, eSortMode);
    }

    private static Options getCliOptions() {
        Option path = Option.builder()
                .longOpt("path")
                .desc("Absolute path to directory")
                .required()
                .hasArg()
                .type(String.class)
                .build();

        Option outPrefix = Option.builder()
                .longOpt("out-prefix")
                .desc("Prefix for output files")
                .required()
                .hasArg()
                .type(String.class)
                .build();

        Option contentType = Option.builder()
                .longOpt("content-type")
                .desc("Type of content (i or s)")
                .required()
                .hasArg()
                .type(String.class)
                .build();

        Option sortMode = Option.builder()
                .longOpt("sort-mode")
                .desc("Type of content (i or s)")
                .required()
                .hasArg()
                .type(String.class)
                .build();

        Options options = new Options();
        options.addOption(path);
        options.addOption(outPrefix);
        options.addOption(contentType);
        options.addOption(sortMode);

        return options;
    }
}
