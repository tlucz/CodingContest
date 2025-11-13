package com.tl.utils;

import java.util.List;

public class StringIteration {

    private final List<String> lines;
    private int currentLine=0;

    public StringIteration(List<String> lines) {
        this.lines = lines;
        this.currentLine=0;
    }

    public String getNext() {
        if(currentLine>=lines.size()) {
            return null;
        }
        String retVal=lines.get(currentLine);
        currentLine++;
        return retVal;
    }
}
