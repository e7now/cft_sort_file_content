package ru.eisemenov.sort_file_content.processing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWithIntegersHandler implements Runnable {
    private final File filePath;
    private List<Integer> content = new ArrayList<>();
    private String outputFilePrefix;
    private boolean sortAsc;

    public FileWithIntegersHandler(File file, String outputFilePrefix, boolean ascending) {
        this.filePath = file;
        this.outputFilePrefix = outputFilePrefix;
        this.sortAsc = ascending;
    }

    private void readContent() throws IOException, FileProcessingException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    int num = Integer.parseInt(line.trim());
                    System.out.println("num = " + num);
                    content.add(num);
                } catch (NumberFormatException e) {
                    throw new FileProcessingException("WARNING: In file " + filePath + " found line \"" + line + "\" which is not integer. File will not be processed");
                }
            }
        }
    }

    private void saveToFile() throws IOException {
        File directory = new File(filePath.getParentFile(), "out");
        String file = filePath.getName();
        File outputFile = new File(directory, outputFilePrefix + file);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (int element : content) {
                writer.write(Integer.toString(element));
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
