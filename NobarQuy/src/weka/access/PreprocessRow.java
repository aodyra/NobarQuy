/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weka.access;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 *
 * @author ryanyonata
 */
public class PreprocessRow {
    public PreprocessRow(int number, String attribute){
        this.number = number;
        this.attribute = attribute;
        this.isSelected = new SimpleBooleanProperty(false);
    }

    public String toString(){
        return Integer.toString(number) + ' ' + isSelected + ' ' + attribute;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public BooleanProperty isSelected() {
        return isSelected;
    }

    public void setSelected(BooleanProperty selected) {
        isSelected = selected;
    }
    private int number;
    private String attribute;
    private BooleanProperty isSelected;
}
