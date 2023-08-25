package com.example.thefinish;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Label labOutput;
    @FXML
    private TextField txtPathFrom;
    @FXML
    private TextField txtPathTo;

    @FXML
    protected void onCalculateAction() {
        String from = txtPathFrom.getText();
        String to = txtPathTo.getText();
        labOutput.setText("");
        PathToNode path = Model.tmpVoid(Integer.parseInt(from), Integer.parseInt(to));
        for (Edge e : path.getPath()) {
            labOutput.setText(labOutput.getText() + "Ребро: " + e.getName() + "\n");
        }
        labOutput.setText(labOutput.getText() + "Общая стоимость: "  + path.getCost() + "\n");
    }
}