package com.martinsundeqvist.jwc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WordCounterArguments {
    HashSet<Character> shortOptions;
    HashSet<String> longOptions;
    List<String> fileNames;

    public WordCounterArguments(String args[]) {
        shortOptions = new HashSet<>();
        longOptions = new HashSet<>();
        fileNames = new ArrayList<>();
        for (String arg: args) {
            if (arg.charAt(0) == '-') {
                if (arg.charAt(1) == '-') {
                    longOptions.add(arg.substring(2));
                } else {
                    for (char c: arg.substring(1).toCharArray()) {
                        shortOptions.add(c);
                    }
                }
            } else {
                fileNames.add(arg);
            }
        }
    }
}
