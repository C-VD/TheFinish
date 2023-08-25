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
    private TableView<Edge> tableEdges;

    @FXML
    private TextField txtNewEdgeName;
    @FXML
    private TextField txtNewEdgeFrom;

    @FXML
    private TextField txtNewEdgeTo;
    @FXML
    private TextField txtNewEdgeCost;

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
    @FXML
    void onAddEdgeBtnClick(){
        Model.addEdge(
                txtNewEdgeName.getText(),
                Integer.parseInt(txtNewEdgeFrom.getText()),
                Integer.parseInt(txtNewEdgeTo.getText())
        );
    }

    public void initialize() {
        Model.setup();
        initTableNodes();
        initTableEdges();
    }

    void initTableNodes() {
        tableNodes.getColumns().clear();
        TableColumn<Node, Integer> columnId = new TableColumn<>("№");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Node, String> columnName = new TableColumn<>("Название");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableNodes.getColumns().add(columnId);
        tableNodes.getColumns().add(columnName);
        tableNodes.setItems(Model.nodesList);
    }
    void initTableEdges() {
        tableEdges.getColumns().clear();
        TableColumn<Edge, Integer> columnId = new TableColumn<>("№");
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Edge, String> columnName = new TableColumn<>("Название");
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Edge, Integer> columnFrom = new TableColumn<>("From");
        columnFrom.setCellValueFactory(new PropertyValueFactory<>("from"));
        columnFrom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Edge, Integer> columnTo = new TableColumn<>("To");
        columnTo.setCellValueFactory(new PropertyValueFactory<>("to"));
        columnTo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        tableEdges.getColumns().add(columnId);
        tableEdges.getColumns().add(columnName);
        tableEdges.getColumns().add(columnFrom);
        tableEdges.getColumns().add(columnTo);
        tableEdges.setItems(Model.edgesList);
    }

}