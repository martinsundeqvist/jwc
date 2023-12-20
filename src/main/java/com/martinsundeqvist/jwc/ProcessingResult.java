package com.martinsundeqvist.jwc;

import java.util.Set;

public class ProcessingResult {
    private String fileName;
    private Set<WordCounterOption> renderOptions;
    private int numberOfBytes = -1;
    private int numberOfWords = -1;
    private int numberOfLines = -1;
    private int maximumLineWidth = -1;

    private String errorMessage;

    public ProcessingResult(String fileName,
                            Set<WordCounterOption> renderOptions,
                            int numberOfBytes,
                            int numberOfWords,
                            int numberOfLines,
                            int maximumLineLength) {
        this.fileName = fileName;
        this.renderOptions = renderOptions;
        this.numberOfBytes = numberOfBytes;
        this.numberOfWords = numberOfWords;
        this.numberOfLines = numberOfLines;
        this.maximumLineWidth = maximumLineLength;
    }

    public ProcessingResult(String fileName,
                            String errorMessage) {
        this.fileName = fileName;
        this.errorMessage = errorMessage;
    }

    public String toString() {
        if (this.errorMessage != null)
            return String.format("wc: %s: %s", fileName, errorMessage);

        this.numberOfLines = this.numberOfLines == 0 ? 0 : this.numberOfLines + 1;
        String linesOutput =
                this.renderOptions.contains(WordCounterOption.PRINT_LINE_COUNT) ?
                        this.numberOfLines + " " : "";
        String wordOutput =
                this.renderOptions.contains(WordCounterOption.PRINT_WORD_COUNT) ?
                        this.numberOfWords + " " : "";

        // TODO: Account for multi-character encodings
        // Currently we are going to assume that we only get input with single character encoding,
        // i.e. one byte -> one character such as in ASCII.
        String charOutput =
                this.renderOptions.contains(WordCounterOption.PRINT_CHAR_COUNT) ?
                        this.numberOfBytes + " " : "";
        String byteOutput =
                this.renderOptions.contains(WordCounterOption.PRINT_BYTE_COUNT) ?
                        this.numberOfBytes + " " : "";
        String maxLineOutput =
                this.renderOptions.contains(WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH) ?
                        this.maximumLineWidth + " " : "";

        return String.format("%s %s %s %s %s %s",
                linesOutput,
                wordOutput,
                charOutput,
                byteOutput,
                maxLineOutput,
                fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getNumberOfBytes() {
        return numberOfBytes;
    }

    public void setNumberOfBytes(int numberOfBytes) {
        this.numberOfBytes = numberOfBytes;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getMaximumLineWidth() {
        return maximumLineWidth;
    }

    public void setMaximumLineWidth(int maximumLineWidth) {
        this.maximumLineWidth = maximumLineWidth;
    }
}
