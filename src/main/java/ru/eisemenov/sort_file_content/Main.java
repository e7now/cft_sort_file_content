package ru.eisemenov.sort_file_content;

import org.apache.commons.cli.ParseException;
import ru.eisemenov.sort_file_content.config.AppConfig;
import ru.eisemenov.sort_file_content.config.AppConfigBuilder;
import ru.eisemenov.sort_file_content.config.AppConfigException;
import ru.eisemenov.sort_file_content.config.SortMode;
import ru.eisemenov.sort_file_content.processing.FilesSorter;

class Main {
    public static void main(String[] args) {
        AppConfig config = getAppConfig(args);
        FilesSorter sorter = new FilesSorter(config.getDirectory());

        boolean isAscendingSort = config.getSortMode() == SortMode.ASCENDING;
        sorter.sortAndSaveAllFiles(isAscendingSort, config.getContentType(), config.getPrefix());
    }

    private static AppConfig getAppConfig(String[] args) {
        AppConfig config = null;
        try {
            config = AppConfigBuilder.getConfig(args);
            System.out.println(config.toString());
        } catch (AppConfigException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        } catch (ParseException e) {
            // exit from app
            System.exit(1);
        }

        return config;
    }
}