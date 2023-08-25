package com.example.thefinish;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

public class HelloController {
    @FXML
    private Label labOutput;
    @FXML
    private TextField txtPathFrom;
    @FXML
    private TextField txtPathTo;
    @FXML
    private TextField txtNewNodeName;
    @FXML
    private TableView<Node> tableNodes;

    @FXML
    protected void onCalculateAction() {
        String from = txtPathFrom.getText();
        String to = txtPathTo.getText();
        labOutput.setText("");
        PathToNode path = Model.tmpVoid(Integer.parseInt(from), Integer.parseInt(to));
        for (Edge e : path.getPath()) {
            labOutput.setText(labOutput.getText() + "Ребро: " + e.getName() + " до " + e.getTo().getName() + "\n");
        }
        labOutput.setText(labOutput.getText() + "Общая стоимость: " + path.getCost() + "\n");
    }

    @FXML
    void onAddNodeBtnClick() {
        Model.addNode(txtNewNodeName.getText());
    }

    public void initialize() {
        Model.setup();
        inittableNodes();
    }

    void inittableNodes() {
        tableNodes.getColumns().clear();
        TableColumn<Node, Integer> columnId = new TableColumn<>("№");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Node, String> columnB = new TableColumn<>("Название");
        columnB.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableNodes.getColumns().add(columnId);
        tableNodes.getColumns().add(columnB);
        tableNodes.setItems(Model.nodesList);
    }

}