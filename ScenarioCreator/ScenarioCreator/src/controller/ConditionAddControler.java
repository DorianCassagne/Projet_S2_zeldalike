package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import model.event.Evenement;
import model.event.Scenario;
import model.event.action.elements.*;
import model.event.action.generator.Action;
import model.event.action.generator.ActionGenerator;
import model.event.condition.elements.*;
import model.event.condition.generator.*;

public class ConditionAddControler implements Initializable{

	
	/*
	 * Condition
	 */
    @FXML 	private ChoiceBox<Type> conditionType;
    @FXML 	private ChoiceBox<LogicalLink> logicalLink;
    @FXML 	private ChoiceBox<ConditionValidity> conditionValue;
    @FXML 	private TextField numeriqueValue;
    @FXML 	private ChoiceBox<ConditionValue> conditionInnerValue;
    @FXML 	private TableColumn<Condition, String> conditionIdCol;
    @FXML	private TableColumn<Condition, String> conditionValidityCol;
    @FXML   private TableColumn<Condition, String> conditionValueCol;
    @FXML	private TableColumn<Condition, String> conditionLinkCol;
    @FXML 	private TableColumn<Condition, String> conditionTypeCol;
    @FXML   private TableView<Condition> conditionTable;
    @FXML   private TextField conditionCellId;
    
    
    /*
     * Action
     */
    @FXML	private ChoiceBox<GeneralType> actionCategorie;
    @FXML   private ChoiceBox<SpecificType> actionGlobalType;
    @FXML   private ChoiceBox<TypeAction> actionSousType;
    @FXML   private TextField actionIdCell;
    @FXML   private TextField actionData;
    @FXML   private Button addAction;
    @FXML   private TableView<Action> actionTableView;
    @FXML   private TableColumn<Action, String> actionCategorieCol;
    @FXML   private TableColumn<Action, String> actionGlobalTypeCol;
    @FXML   private TableColumn<Action, String> actionSpecificTypeCol;
    @FXML   private TableColumn<Action,String> actionDetailCol;
    @FXML   private TableColumn<Action, String> actionCellIdCol;
    @FXML   private TextField actionCellId;
    
    
    /*
     * Evenements
     */
    
    @FXML   private TableView<Evenement> DataTableView;
    @FXML   private TableColumn<Evenement, String> idEventCol;
    @FXML   private TableColumn<Evenement, String> EventConditionCol;
    @FXML   private TableColumn<Evenement, String> EventAction;
    @FXML	private Label titleText;
    @FXML   private TableColumn<Evenement,String> commentCol;

    private ObservableList<Type> conditionTypeItems;
    private ObservableList<ConditionValue> conditionValueItems;
    private ObservableList<ConditionValidity> conditionValidityItems;
    private ObservableList<LogicalLink> logicalLinkItems;
    private ObservableList<GeneralType> actionTypeItems;
    private ObservableList<SpecificType> actionGeneralTypeItems;
    private ObservableList<TypeAction> actionSpecificTypeItems;
    private MapLoader mapLoader;
    private ConditionGenerator conditionGenerator;
    private ActionGenerator actionGenerator;
    private Scenario scenario;
    
    /*
     * Partie initialisation
     */
    
    
    public ConditionAddControler() {
    	this.initAllValues();
    	this.conditionGenerator = new ConditionGenerator();
    	this.actionGenerator = new ActionGenerator();
    	this.scenario = new Scenario();
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
		this.actionTypeItems = FXCollections.observableArrayList();
		this.actionGeneralTypeItems = FXCollections.observableArrayList();
		this.actionSpecificTypeItems = FXCollections.observableArrayList();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setConditionValueItems();
		this.setConditionTypeItems();
		this.setConditionValidityItems();
		this.setLogicalLinkItems();
		this.setActionSpecificTypeItems();
		this.setActionGeneralTypeItems();
		this.setActionTypeItems();
		initConditionTable();
		initFullReportTable();
		
	}
	
	private void initFullReportTable() {
		this.DataTableView.setItems(scenario.getEvents());
		this.EventAction.setCellValueFactory(cellData->cellData.getValue().getActionGenerator().representationProperty());
		this.EventConditionCol.setCellValueFactory(cellData->cellData.getValue().getConditionGenerator().representationProperty());
		this.idEventCol.setCellValueFactory(cellData->cellData.getValue().getId());
		this.commentCol.setCellValueFactory(cellData->cellData.getValue().commentProperty());
	}

	
    
    
    
    
    
    /*
     * Partie Conditions
     */

    
    private void setConditionTypeItems() {
		this.conditionTypeItems.addAll(Type.values());
		this.conditionType.setItems(this.conditionTypeItems);
		this.conditionType.getSelectionModel().selectedItemProperty().addListener(
				(obs,oldValue,newValue)->{
					ConditionValue[] values = newValue.getValidValues();
					if(values.length == 0) {
						numeriqueValue.setEditable(true);
						this.conditionValueItems.clear();
					}
					else {
						numeriqueValue.setText("");
						numeriqueValue.setEditable(false);
						this.conditionValueItems.setAll(newValue.getValidValues());
						this.conditionInnerValue.getSelectionModel().select(0);
					}
					
				}
		);
		
		this.conditionType.getSelectionModel().select(0);
    }
    
    private void setConditionValueItems() {		
		this.conditionInnerValue.setItems(this.conditionValueItems);
    }

    private void setConditionValidityItems() {
		this.conditionValidityItems.addAll(ConditionValidity.values());
		this.conditionValue.setItems(this.conditionValidityItems);
		this.conditionValue.getSelectionModel().select(0);
    }

    private void setLogicalLinkItems() {
    	this.logicalLinkItems.addAll(LogicalLink.values());
    	this.logicalLink.setItems(this.logicalLinkItems);
    	this.logicalLink.getSelectionModel().select(LogicalLink.END);
    }
    
        @FXML
    void conditionChooseOnMap(ActionEvent event) {
    	this.conditionCellId.setText(""+this.mapLoader.getCurrentCell());
    }
        
    private void initTableViewItems(ActionGenerator actionGenerator,ConditionGenerator conditionGenerator) {
		this.conditionGenerator = conditionGenerator;
		this.actionGenerator = actionGenerator;
		this.actionTableView.setItems(actionGenerator.getActions());
		this.conditionTable.setItems(conditionGenerator.getConditions());
    }
        
        
        
	private void initConditionTable() {
		this.conditionTable.setItems(this.conditionGenerator.getConditions());
		this.conditionIdCol.setCellValueFactory(cellData -> cellData.getValue().getId()) ;
		this.conditionLinkCol.setCellValueFactory(cellData-> cellData.getValue().getLink().representationProperty());
		this.conditionTypeCol.setCellValueFactory(cellData-> cellData.getValue().getType().representationProperty());
		this.conditionValidityCol.setCellValueFactory(cellData-> cellData.getValue().getValidity().representationProperty());
		this.conditionValueCol.setCellValueFactory(cellData-> {
			if(cellData.getValue() == null)
				return cellData.getValue().getValue().representationProperty();
			else
				return null;
		});
		this.conditionTable.getSelectionModel().selectedItemProperty().addListener(
				(obs,oldVal,newVal)-> this.setCurrentCondition(newVal)
		);
		
		this.actionTableView.setItems(this.actionGenerator.getActions());
		this.actionCategorieCol.setCellValueFactory(cellData->cellData.getValue().getCategorie().representationProperty());
		this.actionGlobalTypeCol.setCellValueFactory(cellData->cellData.getValue().getGeneralType().representationProperty());
		this.actionSpecificTypeCol.setCellValueFactory(cellData->{
			TypeAction specific = cellData.getValue().getActionInnerType();
			StringProperty representation = null;
			if(specific != null) 
				representation = specific.representationProperty();
			return representation;
		});
		this.actionCellIdCol.setCellValueFactory(cellData->cellData.getValue().getCellId());
		this.actionDetailCol.setCellValueFactory(cellData->cellData.getValue().getDetail());
		this.actionTableView.getSelectionModel().selectedItemProperty().addListener(
				(obs,oldValue,newValue)->this.setCurrentAction(newValue)
		);
	}
	
    @FXML
    void addCondition(ActionEvent event) {
    	
    	try {
        	Condition condition = getCurrentCondition();
    		this.conditionGenerator.addCondition(condition);
    	}catch(IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
			alert.showAndWait();
		}

    }
    
    private Condition getCurrentCondition() {
    	ConditionValidity validity = this.conditionValue.getValue();
    	ConditionValue value = this.conditionInnerValue.getValue();
    	LogicalLink link = this.logicalLink.getValue();
    	Type type = this.conditionType.getValue();
    	String id = this.conditionCellId.getText();
    	Condition current = new Condition(type,id,validity,value,link);
    	
    	return current;

    }


	
	@FXML
	private void modifyCondition() {
		try {
			int index = conditionTable.getSelectionModel().getSelectedIndex();
			Condition condition = this.getCurrentCondition();
			this.conditionGenerator.modifyCondition(condition,index);
		}catch(IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
			alert.showAndWait();
		}
	}
	
	@FXML
	private void deleteCondition() {
		int index = conditionTable.getSelectionModel().getSelectedIndex();
		this.conditionGenerator.removeCondition(index);
	}
	
	private void setCurrentCondition(Condition condition) {
		if(condition != null) {
			this.conditionCellId.textProperty().unbind();
			this.conditionType.getSelectionModel().select(condition.getType());
			if(condition.getId() != null)
				this.conditionCellId.setText(condition.getId().get());
			this.conditionValue.getSelectionModel().select(condition.getValidity());
			this.conditionInnerValue.getSelectionModel().select(condition.getValue());
			this.logicalLink.getSelectionModel().select(condition.getLink());
		}
	}




		
	
	
	
	
	/*
	 * Partie Action
	 */
	
    @FXML
    void actionChooseOnMap(ActionEvent event) {
    	this.actionCellId.setText(""+this.mapLoader.getCurrentCell());
    }
    

	
    private void setActionTypeItems() {
    	this.actionTypeItems.addAll(GeneralType.values());
    	this.actionCategorie.setItems(this.actionTypeItems);
    	this.actionCategorie.getSelectionModel().selectedItemProperty().addListener(
    			(obs,oldValue,newValue)->{
    				this.actionGeneralTypeItems.setAll(newValue.getAllowedGlobalTypes());
    				if(this.actionGeneralTypeItems.size() > 0)
    					this.actionGlobalType.getSelectionModel().select(0);		
    			}
    	);
    	this.actionCategorie.getSelectionModel().select(0);
    }
    
    private void setActionGeneralTypeItems() {
    	this.actionGlobalType.setItems(this.actionGeneralTypeItems);
    	this.actionGlobalType.getSelectionModel().selectedItemProperty().addListener(
			(obs,oldValue,newValue)->{
				if(newValue.getAllowedActions() != null ) {
					this.actionSpecificTypeItems.setAll(newValue.getAllowedActions());
					this.actionSousType.getSelectionModel().select(0);
				}
				else
					this.actionSpecificTypeItems.clear();
			}   
  		);
    }

    
    private void setActionSpecificTypeItems() {
    	this.actionSousType.setItems(this.actionSpecificTypeItems);
    }

	
	
	@FXML
	private void addAction() {
		Action action = this.getAction();
		if(action != null)
			this.actionGenerator.addAction(action);
	}
	
	private Action getAction() {
		Action action = null;
		GeneralType categorie = this.actionCategorie.getValue();
		SpecificType generalType = this.actionGlobalType.getValue();
		TypeAction type = this.actionSousType.getValue();
		String detail = this.actionData.getText();
		String cellId = this.actionCellId.getText();
		try {
			action = new Action(categorie,generalType,type,detail,cellId);
		}catch(IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
			alert.showAndWait();
		}
		return action;

	}
	
	@FXML
	private void modifyAction() {
		int index = actionTableView.getSelectionModel().getSelectedIndex();
		Action lastAction = this.getAction();
		if(index != -1 && lastAction != null) {
			this.actionGenerator.modifyAction(lastAction,index );
		}
	}
	
	@FXML
	private void deleteAction() {
		int index = actionTableView.getSelectionModel().getSelectedIndex();
		this.actionGenerator.removeAction(index);
	}
	
	
	private void setCurrentAction(Action action) {
		if(action != null) {
			this.actionData.textProperty().unbind();
			this.actionCellId.textProperty().unbind();
			this.actionCategorie.getSelectionModel().select(action.getCategorie());
			this.actionGlobalType.getSelectionModel().select(action.getGeneralType());
			this.actionSousType.getSelectionModel().select(action.getActionInnerType());
			if(action.getDetail() != null)
				this.actionData.setText(action.getDetail().get());
			if(action.getCellId() != null)
				this.actionCellId.setText(action.getCellId().get());
		}
	}
	

	
	/*
	 * Evenements
	 */
	@FXML
	private void addEvent() {
		try {
			Evenement event = new Evenement(this.actionGenerator,this.conditionGenerator,this.getTitle()) ;
			this.initTableViewItems(new ActionGenerator(),new ConditionGenerator());
			this.scenario.addEvent(event);
		}catch(IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
			alert.showAndWait();
		}
	}
	
	private String getTitle() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setHeaderText("Commentaire / Titre");
		dialog.setContentText("Saisissez un commentaire :"); 
		Optional<String> result = dialog.showAndWait();
		String title = null;
		if(result.isPresent()) {
			title = result.get();
		}	
		return title;
	}
	
	@FXML
	private void delEvent() {
		int index = this.DataTableView.getSelectionModel().getSelectedIndex();
		try {
			this.scenario.deleteEvent(index);
		}catch(IllegalArgumentException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR,e.getMessage());
			alert.showAndWait();
		}
	}
	
	@FXML
	private void loadEvent() {
		Evenement event = this.DataTableView.getSelectionModel().getSelectedItem();
		if(event != null) {
			this.initTableViewItems(event.getActionGenerator(),event.getConditionGenerator());
			this.titleText.setText(event.getComment());
		}
	}
	
	@FXML
	private void saveFile() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		try{
			String path = this.scenario.generateScenario();
			alert.setAlertType(Alert.AlertType.INFORMATION);
			alert.setResizable(true);
			alert.setContentText("FILE SAVED AT : "+path);
		}catch(IllegalArgumentException e) {
			e.printStackTrace();
			alert.setContentText(e.getMessage());
		}
		alert.showAndWait();

	}

}


