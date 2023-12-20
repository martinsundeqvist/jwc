package com.martinsundeqvist.jwc;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;


public class WordCounterTest {

    @BeforeClass
    public static void setUp() {
        // Set the current working directory to the project directory
        System.setProperty("user.dir", Paths.get("").toAbsolutePath().toString());
    }
    
    @Test
    public void emptyFile_returnsExpectedResult() {
        String filename = "src/test/resources/empty-file.txt";
        String[] args = new String[] {"-cmlLw", filename};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);
        WordCounter wordCounter = new WordCounter(wordCounterArguments);

        List<String> results = wordCounter.process();
        assertEquals(1, results.size());

        String emptyFileResult = results.get(0);
        assertEquals(String.format("0 0 0 0 0 %s", filename), emptyFileResult);
    }

    @Test
    public void validFile_allShortOptions_returnsExpectedResult() {
        String filename = "src/test/resources/quick-brown-fox.txt";
        String[] args = new String[] {"-cmlLw", filename};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);
        WordCounter wordCounter = new WordCounter(wordCounterArguments);

        List<String> results = wordCounter.process();
        assertEquals(1, results.size());

        String result = results.get(0);
        assertEquals(String.format("2 9 43 43 23 %s", filename), result);

    }
    
    @Test
    public void validFile_allLongOptions_returnsExpectedResult() {
        String filename = "src/test/resources/quick-brown-fox.txt";
        String[] args = new String[] {"--bytes", "--chars", "--lines", "--max-line-length", "--words", filename};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);
        WordCounter wordCounter = new WordCounter(wordCounterArguments);

        List<String> results = wordCounter.process();
        assertEquals(1, results.size());

        String result = results.get(0);
        assertEquals(String.format("2 9 43 43 23 %s", filename), result);
    }

    @Test
    public void cantOpenFile_returnsProcessingResultWithErrorMessage() {
        String filename = "src/test/resources/non-existent-file.txt";
        String[] args = new String[] {"--bytes", "--chars", "--lines", "--max-line-length", "--words", filename};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);
        WordCounter wordCounter = new WordCounter(wordCounterArguments);

        List<String> results = wordCounter.process();
        assertEquals(1, results.size());

        String nonFileResult = results.get(0);
        assertEquals(String.format("wc: %s: No such file or directory", filename), nonFileResult);
    }
    
}
