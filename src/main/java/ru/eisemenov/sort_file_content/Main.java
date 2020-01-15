package ru.eisemenov.sort_file_content;

import org.apache.commons.cli.ParseException;
import ru.eisemenov.sort_file_content.config.*;
import ru.eisemenov.sort_file_content.processing.FileWithIntegersHandler;
import ru.eisemenov.sort_file_content.processing.FileWithStringsHandler;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Main {
    public static void main(String[] args) {
        AppConfig config = getAppConfig(args);

        File[] files = config.getDirectory().listFiles(File::isFile);
        assert files != null;

        if (files.length > 0) {
            File outputDirectory = new File(config.getDirectory(), "out");
            File[] filesToDelete = outputDirectory.listFiles(File::isFile);
            if (outputDirectory.exists()) {
                for (File file : filesToDelete) file.delete();
            } else {
                outputDirectory.mkdir();
            }
        }

        ExecutorService threadPool = Executors.newWorkStealingPool();
        for (File file : files) {
            System.out.println(file.toString());
            if (config.getContentType() == ContentType.INTEGER) {
                boolean isAscending = config.getSortMode() == SortMode.ASCENDING;
                FileWithIntegersHandler fileWithIntegersHandler = new FileWithIntegersHandler(file, config.getPrefix(), isAscending);
                threadPool.execute(fileWithIntegersHandler);
            } else if (config.getContentType() == ContentType.STRING) {
                boolean isAscending = config.getSortMode() == SortMode.ASCENDING;
                FileWithStringsHandler fileWithIntegersHandler = new FileWithStringsHandler(file, config.getPrefix(), isAscending);
                threadPool.execute(fileWithIntegersHandler);
            }
        }

        try {
            threadPool.shutdown();
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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