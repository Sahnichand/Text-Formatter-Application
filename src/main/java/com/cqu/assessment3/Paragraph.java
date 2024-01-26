/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cqu.assessment3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *
 *
 */
public class Paragraph {

    private AlignmentStrategy strategy;
    private int lineLength = 20;
    private String space = " ";
    private String visibleSpace = "^";
    private String text = "";

    public Paragraph(AlignmentStrategy strategy) {
        this.strategy = strategy;
    }

    public String alignText() {
        //convert the text to list of lines. Each line is a linked list of words and spacing.
        List<LinkedList<String>> lines = textToLines();

        //align each line according to the alignment strategy
        for (LinkedList<String> line : lines) {
            strategy.align(line);
        }

        //convert the list of lines back to a string to be returned (so that it can be displayed)
        return linesToText(lines);

    }

    public String getText() {
        return text;
    }

    public String setText(String txt) {
        this.text = txt;
        return text;
    }

    public String alignAndAugmentText() {
        //convert the text to list of lines. Each line is a linked list of words and spacing.
        List<LinkedList<String>> lines = textToLines();
        //align each line according to the alignment strategy
        for (LinkedList<String> line : lines) {
            strategy.align(line);
        }
        augment(lines);

        //convert the list of lines back to a string to be returned (so that it can be displayed)
        return linesToText(lines);
    }

    private void augment(List<LinkedList<String>> lines) {
        for (LinkedList<String> line : lines) {
            ListIterator<String> iterator = line.listIterator();
            while (iterator.hasNext()) {
                String s = iterator.next();
                if (s.contains(" ")) // replace the current entry
                {
                    iterator.set(visibleSpace.repeat(s.length()));
                }
            }
        }
    }

    private String linesToText(List<LinkedList<String>> lines) {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<String> line : lines) {
            for (String s : line) {
                sb.append(s);
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private List<LinkedList<String>> textToLines() {
        String[] words = text.split("\\s+"); // text is the original text – this splits it into words
        List<LinkedList<String>> lines = new ArrayList<>(); // lines will be a list of linked lists 
        int n = 0; // count to track the number of characters in the linked list of words and spaces so far
        boolean firstWord = true; // recognise if it is the first word on a line
        LinkedList<String> line = new LinkedList<>(); //create the first linked list
        //iterate through the array of words building the lines
        for (String w : words) {
            if (firstWord) {
                // check length compared to lineLength without counting a preceding space
                if (n + w.length() >= lineLength) { // it fits exactly or it is too long – just have one word on line 
//                             add w to the line (use line.addFirst() as it is the first word in the linked list)
                    line.addFirst(w);
//                             add line to the linked list of lines (as it is a complete line)
                    lines.add(line);
//                             create a new linked list // for the next word (which will be a first word in this next line)
                    line = new LinkedList<>();
//                             set n to 0 // length of the new line
                    n = 0;
                } else { // adding firstword to new line 
//                    add w to the line(use line.addFirst() as it is the first word in the linked list
                    line.addFirst(w);

//                             set n to the length of the first word 
                    n = w.length();
//set firstWord to false // the next word will be the second word in this line
                    firstWord = false;
                }
            } else {// not the first word, so check if there is room for the word and the preceding space
                if (n + space.length() + w.length() > lineLength) { // no room for this word on current line
                    // complete the current line
                    // add the string to pad it with spaces but only if there is room for spaces
                    // if the previous word added was one that was the same as the lineLength or longer 
                    // and had just been added to a new line then there will be no room for spaces at the 
                    // end of the line
                    if (n < lineLength) {
                        line.addLast(space.repeat(lineLength - n));
                    }

                    // add the completed line to the list of lines
                    lines.add(line);

//                    make a new linked list for the next line add {w} to the start of this next line
                    line = new LinkedList<String>();
                    line.addFirst(w);
//                    set n to the length of w
                    n = w.length();

                } else if (n + space.length() + w.length() == lineLength) { // it fits exactly –  so the next word 
                    // must be first on a new line
                    line.add(space); // add preceding space to the linked list (i.e. to the line we are building) 
//                    add the word w to the line add the line to the list of lines 
                    line.add(w);
                    lines.add(line);
//                  create a new linked list for the next line set {firstWord} to true 
                    line = new LinkedList<>();
                    firstWord = true;

// as the next word (next iteration of loop)  will the first word in the new line
//                    set n to 0 // length of the new line at the start                 
                    n = 0;

                } else { // add the current word to the line (and still space in the line to try to add next word)
//                          Add the preceding space to the linked list (the current line
                    line.add(space);

//                          Add the word w to the linked list 
                    line.add(w);

//                    Increase the value in n(add the length of the new word 
                    n += w.length() + space.length();
                    //                    and the preceding space

                }

            }
        }

        // complete the last line when we leave the loop and add it to the lines array 
        line.addLast(space.repeat(lineLength - n));
        lines.add(line);
        return lines;
    }

}
