package org.openjfx;

// #shamelessripoff
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DataModel {

    private SimpleStringProperty txtData;
    private SimpleIntegerProperty intData;

    public DataModel(String txtData, int intData) {
        if(intData < 0) {
            throw new IllegalArgumentException("intData cannot be negative");
        }

        this.txtData = new SimpleStringProperty(txtData);
        this.intData = new SimpleIntegerProperty(intData);
    }

    public String getTxtData() {
        return txtData.getValue();
    }

    public void setTxtData(String txtData) {
        this.txtData.set(txtData);
    }

    public int getIntData() {
        return intData.getValue();
    }

    public void setIntData(int intData) {
        if(intData < 0) {
            throw new IllegalArgumentException("intData cannot be negative");
        }

        this.intData.set(intData);
    }
}