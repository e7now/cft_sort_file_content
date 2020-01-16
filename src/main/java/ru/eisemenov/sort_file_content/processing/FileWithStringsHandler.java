package ru.eisemenov.sort_file_content.processing;

import java.io.File;

class FileWithStringsHandler extends FileHandler<String> {

    FileWithStringsHandler(File file, String outputFilePrefix, boolean ascending) {
        super(file, outputFilePrefix, ascending);
    }

    @Override
    protected String parse(String line) {
        return line;
    }
}
