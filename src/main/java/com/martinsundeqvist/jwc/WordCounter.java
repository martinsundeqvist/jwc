package com.martinsundeqvist.jwc;

import com.martinsundeqvist.jwc.exception.InvalidLongOptionException;
import com.martinsundeqvist.jwc.exception.InvalidShortOptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordCounter {

    private HashSet<WordCounterOption> wordCounterOptions;
    private List<String> fileNames;

    public WordCounter(WordCounterArguments wordCounterArguments) {
        this.wordCounterOptions = new HashSet<>();
        wordCounterOptions.addAll(setOptionsBasedOnShortOptions(wordCounterArguments.getShortOptions()));
        wordCounterOptions.addAll(setOptionsBasedOnLongOptions(wordCounterArguments.getLongOptions()));
        this.fileNames = wordCounterArguments.getFilenames();
    }
    private HashSet<WordCounterOption> setOptionsBasedOnShortOptions(HashSet<Character> shortOptions) {
        HashSet<WordCounterOption> wordCounterOptions = new HashSet<>();
        for (Character shortOption : shortOptions) {
            switch (shortOption) {
                case 'c':
                    wordCounterOptions.add(WordCounterOption.PRINT_BYTE_COUNT);
                    break;
                case 'm':
                    wordCounterOptions.add(WordCounterOption.PRINT_CHAR_COUNT);
                    break;
                case 'l':
                    wordCounterOptions.add(WordCounterOption.PRINT_LINE_COUNT);
                    break;
                case 'L':
                    wordCounterOptions.add(WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH);
                    break;
                case 'w':
                    wordCounterOptions.add(WordCounterOption.PRINT_WORD_COUNT);
                    break;
                default:
                    throw new InvalidShortOptionException(String.format("Option %shortOption is not a valid short option", shortOption));
            }
        }
         return wordCounterOptions;
    }

    private HashSet<WordCounterOption> setOptionsBasedOnLongOptions(HashSet<String> longOptions) {
        HashSet<WordCounterOption> wordCounterOptions = new HashSet<>();
        for (String longOption : longOptions) {
            switch (longOption) {
                case "bytes":
                    wordCounterOptions.add(WordCounterOption.PRINT_BYTE_COUNT);
                    break;
                case "chars":
                    wordCounterOptions.add(WordCounterOption.PRINT_CHAR_COUNT);
                    break;
                case "lines":
                    wordCounterOptions.add(WordCounterOption.PRINT_LINE_COUNT);
                    break;
                case "max-line-length":
                    wordCounterOptions.add(WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH);
                    break;
                case "words":
                    wordCounterOptions.add(WordCounterOption.PRINT_WORD_COUNT);
                    break;
                default:
                    throw new InvalidLongOptionException(String.format("Option %s is not a valid long option", longOption));
            }
        }
        return wordCounterOptions;
    }

    public List<String> process() {
        List<String> results = new ArrayList<>();
        for (String filename: this.fileNames) {
            int numberOfBytes = 0;
            int numberOfWords = 0;
            // TODO Count lines not just based on UNIX line endings, but also Windows
            int numberOfLines = 0;
            int maximumLineLength = 0;

            try {
                byte[] bytes = Files.readAllBytes(Paths.get(filename));
                boolean inWord = false;
                int currentLineLength = 0;
                for (byte b: bytes) {
                    char ch = (char) b;

                    if (!Character.isWhitespace(ch)) {
                        // Non-whitespace case
                        currentLineLength++;
                        inWord = true;
                    } else {
                        // Whitespace case
                        if (inWord) {
                            // If we were in a word we add to count and "exit" the word
                            numberOfWords++;
                            inWord = false;
                        }
                        if (b == '\n') {
                            // If we are the end of a line, we increment line count and check
                            // if we've found a greater max line length
                            numberOfLines++;
                            maximumLineLength = Math.max(currentLineLength, maximumLineLength);
                            currentLineLength = 0;
                        } else {
                            // If we've hit some other whitespace we simply increment the current
                            // line length count
                            currentLineLength++;
                        }
                    }
                    // Increment number of bytes regardless
                    numberOfBytes++;
                }
                // If file ends with EOF we need to add one more word
                if (inWord)
                    numberOfWords++;
                maximumLineLength = Math.max(currentLineLength, maximumLineLength);
            } catch (IOException e) {
                ProcessingResult processingResult =
                        new ProcessingResult(filename, "No such file or directory");
                results.add(processingResult.toString());
                continue;
            }
            ProcessingResult processingResult =
                    new ProcessingResult(
                            filename,
                            wordCounterOptions,
                            numberOfBytes,
                            numberOfWords,
                            numberOfLines,
                            maximumLineLength);
            results.add(processingResult.toString());
        }
        return results;
    }
}
