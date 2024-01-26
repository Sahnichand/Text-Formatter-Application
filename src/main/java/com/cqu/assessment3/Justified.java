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
public class Justified extends AlignmentStrategy {
    
    public Justified(){
        
    }
    /**
     * default is left align
     * @param line 
     */
    @Override
    public void align(LinkedList<String> line) {
        
      if (line.getLast().matches("\\s+")) {
            String spaceBefore = "";
            String spaceAfter = "";
            

            for (int i = 0; i < line.getLast().length(); i++) {
               
                    spaceBefore +=" ";
                    spaceAfter+=" ";
                }
            }
            
          

        }
    }
    
    

