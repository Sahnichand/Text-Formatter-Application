/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.cqu.assessment3;

import java.util.LinkedList;

/**
 *
 * 
 */
public class Centered extends AlignmentStrategy {

    @Override
        public void align(LinkedList<String> line) {
        if (line.getLast().matches("\\s+")) {
            String spaceBefore = "";
            String spaceAfter = "";
            

            for (int i = 0; i < line.getLast().length(); i++) {
                if (i <= line.getLast().length() / 2) {
                    spaceBefore +=" ";
                } else {
                    spaceAfter+=" ";
                }
            }
            line.addFirst(spaceBefore);
            line.removeLast();
            line.addLast(spaceAfter);

        }
    }
    
}
