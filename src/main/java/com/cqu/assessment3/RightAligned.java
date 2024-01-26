/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cqu.assessment3;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * 
 */
public class RightAligned extends AlignmentStrategy {

    public RightAligned() {

    }

    /**
     * Right align the text
     *
     * @param line
     */
    @Override
    public void align(LinkedList<String> line) {
        if (line.getLast().matches("\\s+")) {
            line.addFirst(line.getLast());
            line.removeLast();
        }

    }

}
