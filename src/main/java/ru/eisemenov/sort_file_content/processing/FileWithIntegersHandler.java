package ru.eisemenov.sort_file_content.processing;

import java.io.File;

class FileWithIntegersHandler extends FileHandler<Integer> {
    FileWithIntegersHandler(File file, String outputFilePrefix, boolean ascending) {
        super(file, outputFilePrefix, ascending);
    }

    @Override
    protected Integer parse(String line) throws FileProcessingException {
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new FileProcessingException("WARNING: In file " + filePath + " found line \"" + line + "\" which is not integer. File will not be processed");
        }
    }
}
