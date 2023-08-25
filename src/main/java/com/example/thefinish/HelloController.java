package com.example.thefinish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label labOutput;

    @FXML
    protected void onCalculateAction() {
        PathToNode path = Model.tmpVoid();
        for (Edge e : path.getPath()) {
            labOutput.setText(labOutput.getText() + "Ребро: " + e.getName() + "\n");
        }
        labOutput.setText(labOutput.getText() + "Общая стоимость: "  + path.getCost() + "\n");
    }
}