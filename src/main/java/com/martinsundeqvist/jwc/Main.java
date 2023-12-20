package com.martinsundeqvist.jwc;

import java.util.List;

public class Main {

    public static final int BUFFER_SIZE = 256;

    public static void main(String[] args) {
        try {
            process(args);
        } catch (Exception e) {
            System.out.println("Error occured while reading arguments");
            System.exit(1);
        }
    }

    private static void process(String args[]) {
        if (args.length == 0)
            System.exit(2);
        else
        {
            WordCounterArguments wordCounterArguments = new WordCounterArguments(args);
            WordCounter wc = new WordCounter(wordCounterArguments);
            List<String> results = wc.process();
            for(String resultLine: results) {
                System.out.println(resultLine);
            }
        }
    }
}