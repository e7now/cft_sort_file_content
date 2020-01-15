package ru.eisemenov.sort_file_content.processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWithStringsHandler implements Runnable {
    private final File filePath;
    private List<String> content = new ArrayList<>();
    private String outputFilePrefix;
    private boolean sortAsc;

    public FileWithStringsHandler(File file, String outputFilePrefix, boolean ascending) {
        this.filePath = file;
        this.outputFilePrefix = outputFilePrefix;
        this.sortAsc = ascending;
    }

    private void readContent() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.add(line);
            }
        }
    }

    private void saveToFile() throws IOException {
        File directory = new File(filePath.getParentFile(), "out");
        String file = filePath.getName();
        File outputFile = new File(directory, outputFilePrefix + file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (String element : content) {
                writer.write(element);
                writer.newLine();
            }
        }
    }

    @Override
    public void run() {
        try {
            readContent();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        InsertionSort.sort(content, sortAsc);

        try {
            saveToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
