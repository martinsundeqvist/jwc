package com.martinsundeqvist.jwc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WordCounterArgumentTest {
    
    @Test
    public void canParseShortOptions() {
        String[] args = new String[] {"-cmlLw", "filename"};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);

        assertEquals(5, wordCounterArguments.getShortOptions().size());
        assertEquals(0, wordCounterArguments.getLongOptions().size());
        assertEquals(1, wordCounterArguments.getFilenames().size());
    }
    
    @Test
    public void canParseMultipleFiles() {
        String[] args = new String[] {"-cmlLw", "filename1", "filename2"};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);

        assertEquals(5, wordCounterArguments.getShortOptions().size());
        assertEquals(0, wordCounterArguments.getLongOptions().size());
        assertEquals(2, wordCounterArguments.getFilenames().size());
    }
    
    @Test
    public void canParseLongOptions() {
        String[] args = new String[] {"--bytes", "filename"};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);

        assertEquals(0, wordCounterArguments.getShortOptions().size());
        assertEquals(1, wordCounterArguments.getLongOptions().size());
        assertEquals(1, wordCounterArguments.getFilenames().size());
    }
    
    @Test
    public void canParseBothShortAndLong() {
        String[] args = new String[] {"-l", "--bytes", "filename"};
        WordCounterArguments wordCounterArguments = new WordCounterArguments(args);

        assertEquals(1, wordCounterArguments.getShortOptions().size());
        assertEquals(1, wordCounterArguments.getLongOptions().size());
        assertEquals(1, wordCounterArguments.getFilenames().size());
    }
}
