package com.martinsundeqvist.jwc;

import java.util.ArrayList;
import java.util.List;
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

        List<String> outputList = new ArrayList<>();

        this.numberOfLines = this.numberOfLines == 0 ? 0 : this.numberOfLines + 1;
        if (this.renderOptions.contains(WordCounterOption.PRINT_LINE_COUNT))
            outputList.add(Integer.toString(this.numberOfLines));
        
        if (this.renderOptions.contains(WordCounterOption.PRINT_WORD_COUNT))
            outputList.add(Integer.toString(this.numberOfWords));

        // TODO: Account for multi-character encodings
        // Currently we are going to assume that we only get input with single character encoding,
        // i.e. one byte -> one character such as in ASCII.
        if (this.renderOptions.contains(WordCounterOption.PRINT_CHAR_COUNT))
            outputList.add(Integer.toString(this.numberOfBytes));

        if (this.renderOptions.contains(WordCounterOption.PRINT_BYTE_COUNT))
            outputList.add(Integer.toString(this.numberOfBytes));

        if (this.renderOptions.contains(WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH))
            outputList.add(Integer.toString(maximumLineWidth));

        outputList.add(this.fileName);
        
        return String.join(" ", outputList);
    }

}
