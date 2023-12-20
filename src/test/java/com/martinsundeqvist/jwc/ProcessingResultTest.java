package com.martinsundeqvist.jwc;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Set;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ProcessingResultTest {

    @Test
    public void allOptionsSelected_returnsFormatedStringWithAllData()
    {
        String filename = "testfile.txt";
        Set<WordCounterOption> renderOptions = Set.of(
            WordCounterOption.PRINT_BYTE_COUNT,
            WordCounterOption.PRINT_CHAR_COUNT,
            WordCounterOption.PRINT_LINE_COUNT,
            WordCounterOption.PRINT_WORD_COUNT,
            WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH);
        int numberOfBytes = 10;
        int numberOfWords = 12;
        int numberOfLines = 13;
        int maxLineWidth = 14;
        ProcessingResult processingResult = new ProcessingResult(
            filename,
            renderOptions,
            numberOfBytes,
            numberOfWords,
            numberOfLines,
            maxLineWidth);

        String expected = String.join(" ", List.of(
            Integer.toString(numberOfLines + 1),
            Integer.toString(numberOfWords),
            Integer.toString(numberOfBytes),
            Integer.toString(numberOfBytes),
            Integer.toString(maxLineWidth),
            filename));
        String outputString = processingResult.toString();

        assertEquals(expected, outputString);
    }
    
    @Test
    public void hasErrorMessage_returnFormattedErrorMessage()
    {
        String filename = "testfile.txt";
        Set<WordCounterOption> renderOptions = Set.of(
            WordCounterOption.PRINT_BYTE_COUNT,
            WordCounterOption.PRINT_CHAR_COUNT,
            WordCounterOption.PRINT_LINE_COUNT,
            WordCounterOption.PRINT_WORD_COUNT,
            WordCounterOption.PRINT_MAXIMUM_LINE_WIDTH);
        int numberOfBytes = 10;
        int numberOfWords = 12;
        int numberOfLines = 13;
        int maxLineWidth = 14;
        ProcessingResult processingResult = new ProcessingResult(
            filename,
            renderOptions,
            numberOfBytes,
            numberOfWords,
            numberOfLines,
            maxLineWidth);

        String expected = String.join(" ", List.of(
            Integer.toString(numberOfLines + 1),
            Integer.toString(numberOfWords),
            Integer.toString(numberOfBytes),
            Integer.toString(numberOfBytes),
            Integer.toString(maxLineWidth),
            filename));
        String outputString = processingResult.toString();

        assertEquals(expected, outputString);
    }

}
