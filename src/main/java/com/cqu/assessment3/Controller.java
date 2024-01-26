/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.cqu.assessment3;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javax.print.Doc;

/**
 * FXML Controller class
 *
 * 
 */
public class Controller implements Initializable {
    
    @FXML
    private Button btnExit;
    @FXML
    private TextArea oriText;
    @FXML
    private TextArea alignedText;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnLoad;
    @FXML
    private Button btnDefault;
    @FXML
    private Button btnClear;
    @FXML
    private RadioButton rdoLeft;
    @FXML
    private RadioButton rdoRight;
    @FXML
    private RadioButton rdoCentered;
    @FXML
    private RadioButton rdoJustified;
    @FXML
    private RadioButton rdoAugment;
    
    Document document;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        rdoLeft.setToggleGroup(tg);
        rdoRight.setToggleGroup(tg);
        rdoCentered.setToggleGroup(tg);
        rdoJustified.setToggleGroup(tg);
        
    }
    
    @FXML
    private void handleExit(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void handleSave(ActionEvent event) {
        document.setFormattedText(alignedText.getText());
        try {
            document.save(getResponse("Enter filename"));
        } catch (IOException ex) {
            displayError("Can't save the file");
        }
        
    }
    
    @FXML
    private void handleLoad(ActionEvent event) {
        try {
            String fname = getResponse("Enter filename");
            document = new Document(fname);
            oriText.setText(document.getUnformattedText());
        } catch (IOException ex) {
            displayError("Can't read the file");
        }
    }


    @FXML
    private void handleDefault(ActionEvent event) {
        
        document = new Document();
        oriText.setText(document.getUnformattedText());
        
    }

    @FXML
    private void handleClear(ActionEvent event) {
        oriText.setText("");
        alignedText.setText("");
        rdoLeft.setSelected(false);
        rdoRight.setSelected(false);
        rdoCentered.setSelected(false);
        rdoJustified.setSelected(false);
        rdoAugment.setSelected(false);
        
    }
    
    @FXML
    private void handleAlignLeft(ActionEvent event) {
        
        Paragraph p = new Paragraph(new LeftAlign());
        p.setText(oriText.getText());
        
        if (rdoAugment.isSelected()) {
            alignedText.setText(p.alignAndAugmentText());
        } else {
            alignedText.setText(p.alignText());
        }
        
    }
    
    @FXML
    private void handleAlignRight(ActionEvent event) {
        Paragraph p = new Paragraph(new RightAligned());
        p.setText(oriText.getText());
        
        if (rdoAugment.isSelected()) {
            alignedText.setText(p.alignAndAugmentText());
        } else {
            alignedText.setText(p.alignText());
        }
        
    }
    
    @FXML
    private void handleAlignCentered(ActionEvent event) {
        Paragraph p = new Paragraph(new Centered());
        p.setText(oriText.getText());
        
        if (rdoAugment.isSelected()) {
            alignedText.setText(p.alignAndAugmentText());
        } else {
            alignedText.setText(p.alignText());
        }
        
    }
    
    @FXML
    private void handleAlignJustified(ActionEvent event) {
         Paragraph p = new Paragraph(new Justified());
        p.setText(oriText.getText());
        
        if (rdoAugment.isSelected()) {
            alignedText.setText(p.alignAndAugmentText());
        } else {
            alignedText.setText(p.alignText());
        }
        
    
        
    }
    
    private String getResponse(String title) throws NoSuchElementException {
        // Create a new dialog for each load invocation.
        TextInputDialog lfid = new TextInputDialog();
        lfid.setTitle(title);
        lfid.setHeaderText(null);
        // Block until a response has been provided.
        Optional<String> result = lfid.showAndWait();
        if (!result.isPresent()) {
            return "cancelled";
        }
        return result.get();
    }
    
    private void displayError(String message) {
        Alert a = new Alert(AlertType.ERROR);
        a.setTitle("Error");
        a.setHeaderText(message);
        a.showAndWait();
    }
    
}
