package ru.eisemenov.sort_file_content.config;

import java.io.File;

public class AppConfig {
    private File directory;
    private String prefix;
    private ContentType contentType;
    private SortMode sortMode;

    AppConfig(File directory, String prefix, ContentType contentType, SortMode sortMode) throws AppConfigException {
        if (!directory.isDirectory()) {
            throw new AppConfigException("ERROR: " + directory.getAbsolutePath() + " is not directory");
        }
        if (prefix.isEmpty()) {
            throw new AppConfigException("ERROR: Prefix \"" + prefix + "\" can't be empty");
        }

        this.directory = directory;
        this.prefix = prefix;
        this.contentType = contentType;
        this.sortMode = sortMode;
    }

    @Override
    public String toString() {
        return "AppConfig{" +
                "directory=" + directory +
                ", prefix='" + prefix + '\'' +
                ", contentType=" + contentType +
                ", sortMode=" + sortMode +
                '}';
    }

    public File getDirectory() {
        return directory;
    }

    public String getPrefix() {
        return prefix;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public SortMode getSortMode() {
        return sortMode;
    }
}
