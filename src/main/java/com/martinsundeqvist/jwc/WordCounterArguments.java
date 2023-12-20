package com.martinsundeqvist.jwc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordCounterArguments {
    private HashSet<Character> shortOptions;
    private HashSet<String> longOptions;
    private List<String> filenames;

    public WordCounterArguments(String args[]) {
        this.shortOptions = new HashSet<>();
        this.longOptions = new HashSet<>();
        this.filenames = new ArrayList<>();
        for (String arg: args) {
            if (arg.charAt(0) == '-') {
                if (arg.charAt(1) == '-') {
                    this.longOptions.add(arg.substring(2));
                } else {
                    for (char c: arg.substring(1).toCharArray()) {
                        this.shortOptions.add(c);
                    }
                }
            } else {
                this.filenames.add(arg);
            }
        }
    }

    public HashSet<Character> getShortOptions() {
        return this.shortOptions;
    }
    
    public HashSet<String> getLongOptions() {
        return this.longOptions;
    }

    public List<String> getFilenames() {
        return this.filenames;
    }
}
