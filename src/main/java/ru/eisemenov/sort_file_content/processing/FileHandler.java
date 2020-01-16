package ru.eisemenov.sort_file_content.processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

abstract class FileHandler<T extends Comparable<T>> implements Runnable {
    protected File filePath;
    private List<T> content = new ArrayList<>();
    private String outputFilePrefix;
    private boolean sortAsc;

    FileHandler(File file, String outputFilePrefix, boolean ascending) {
        this.filePath = file;
        this.outputFilePrefix = outputFilePrefix;
        this.sortAsc = ascending;
    }

    private void readContent() throws IOException, FileProcessingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T num = parse(line);
                content.add(num);
            }
        }
    }

    protected abstract T parse(String line) throws FileProcessingException;

    private void saveToFile() throws IOException {
        File directory = new File(filePath.getParentFile(), "out");
        String file = filePath.getName();
        File outputFile = new File(directory, outputFilePrefix + file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int i = 0; i < content.size(); i++) {
                writer.write(content.get(i).toString());
                if (i < content.size() - 1) {
                    writer.newLine();
                }
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
        } catch (FileProcessingException e) {
            System.out.println(e.getMessage());
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
