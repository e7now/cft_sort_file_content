package ru.eisemenov.sort_file_content.processing;

import ru.eisemenov.sort_file_content.config.ContentType;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FilesSorter {
    private File inputDirectory;
    private File[] files;

    public FilesSorter(File inputDirectory) {
        this.inputDirectory = inputDirectory;
        this.files = inputDirectory.listFiles(File::isFile);
    }

    public void sortAndSaveAllFiles(boolean ascendingSort, ContentType contentType, String prefix) {
        assert this.files != null;

        if (this.files.length > 0) {
            File outputDirectory = new File(inputDirectory, "out");
            File[] filesToDelete = outputDirectory.listFiles(File::isFile);
            if (outputDirectory.exists()) {
                assert filesToDelete != null;
                for (File file : filesToDelete) file.delete();
            } else {
                outputDirectory.mkdir();
            }
        }
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (File file : files) {
            System.out.println(file.toString());
            Runnable fileHandler = null;
            if (contentType == ContentType.INTEGER) {
                fileHandler = new FileWithIntegersHandler(file, prefix, ascendingSort);

            } else if (contentType == ContentType.STRING) {
                fileHandler = new FileWithStringsHandler(file, prefix, ascendingSort);
            }

            assert fileHandler != null;
            threadPool.execute(fileHandler);
        }

        try {
            threadPool.shutdown();
            threadPool.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
