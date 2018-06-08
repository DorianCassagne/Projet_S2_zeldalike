package controller;

import model.event.condition.*;
import model.event.action.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ConditionAddControler implements Initializable{

    @FXML
    private ChoiceBox<Type> conditionType;

    @FXML
    private ChoiceBox<LogicalLink> logicalLink;
    @FXML
    private ChoiceBox<ConditionValidity> conditionValue;

    @FXML
    private TextField numeriqueValue;

    @FXML
    private ChoiceBox<ConditionValue> conditionInnerValue;

    @FXML
    private TableView<?> conditionTable;

    @FXML
    private TableColumn<?, ?> conditionIdCol;

    @FXML
    private TableColumn<?, ?> conditionValiditeCol;

    @FXML
    private TableColumn<?, ?> conditionTextCol;

    @FXML
    private TableColumn<?, ?> conditionLogicalCol;

    @FXML
    private ChoiceBox<TypeAction> actionCategorie;

    @FXML
    private ChoiceBox<GeneralType> actionGlobalType;

    @FXML
    private ChoiceBox<SpecificType> actionSousType;

    @FXML
    private TextField actionIdCell;

    @FXML
    private TextField actionData;

    @FXML
    private Button addAction;

    @FXML
    private TableView<?> actionTableView;

    @FXML
    private TableColumn<?, ?> actionTextCol;

    @FXML
    private TableView<?> DataTableView;

    @FXML
    private TableColumn<?, ?> idEventCol;

    @FXML
    private TableColumn<?, ?> EventConditionCol;

    @FXML
    private TableColumn<?, ?> EventAction;

    private ObservableList<Type> conditionTypeItems;
    private ObservableList<ConditionValue> conditionValueItems;
    private ObservableList<ConditionValidity> conditionValidityItems;
    private ObservableList<LogicalLink> logicalLinkItems;
    private ObservableList<TypeAction> actionTypeItems;
    private ObservableList<TypeAction> actionGeneralTypeItems;
    private ObservableList<TypeAction> actionSpecificTypeItems;
    private MapLoader mapLoader;
    
    public ConditionAddControler() {
    	this.initAllValues();
    }
    
    public void setMapLoader(MapLoader mapLoader) {
    	this.mapLoader = mapLoader;
    }
    
    private void initAllValues() {
		
    	this.conditionTypeItems = FXCollections.observableArrayList();
		this.conditionValueItems = FXCollections.observableArrayList();
		this.conditionValidityItems = FXCollections.observableArrayList();
		this.actionTypeItems = FXCollections.observableArrayList();
		this.actionGeneralTypeItems = FXCollections.observableArrayList();
		this.actionSpecificTypeItems = FXCollections.observableArrayList();
		this.logicalLinkItems = FXCollections.observableArrayList();
    }
    
    private void setConditionTypeItems() {
		this.conditionTypeItems.addAll(Type.values());
		this.conditionType.setItems(this.conditionTypeItems);
    }
    
    private void setConditionValueItems() {		
    	this.conditionValueItems.setAll(ConditionValue.values());
		this.conditionInnerValue.setItems(this.conditionValueItems);
    }

    private void setConditionValidityItems() {
		this.conditionValidityItems.addAll(ConditionValidity.values());
		this.conditionValue.setItems(this.conditionValidityItems);
    }

    private void setLogicalLinkItems() {
    	this.logicalLinkItems.addAll(LogicalLink.values());
    	this.logicalLink.setItems(this.logicalLinkItems);
    }
    
    @FXML
    void addCondition(ActionEvent event) {

    }
    
    @FXML
    void chooseOnMap(ActionEvent event) {
    	System.out.println(this.mapLoader.getCurrentCell());
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setConditionTypeItems();
		this.setConditionValidityItems();
		this.setConditionValueItems();
		this.setLogicalLinkItems();
	}

}
