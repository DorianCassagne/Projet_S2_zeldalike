package model.event.action.generator;

import java.util.ArrayList;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.action.elements.*;

public class Action {
	
	private GeneralType categorie;
	private SpecificType generalType;
	private TypeAction actionInnerType;
	private StringProperty detail;
	private StringProperty  cellId;
	private final static ArrayList<String> monstersId;
	private final static String SEPARATOR = "-";
	
	static {
		monstersId = new ArrayList<String>();
	}
	
	
	public Action(GeneralType categorie,SpecificType generalType,TypeAction actionInnerType,String detail,String cellId) {
		this.setCategorie(categorie);
		this.setGeneralType(generalType);
		this.setActionSpecificType(actionInnerType);
		this.setCellId(cellId);
		this.setDetail(detail);
	}

	private void setCategorie(GeneralType category) {
		if(category != null)
			this.categorie = category;
		else
			throw new IllegalArgumentException("INVALID CATEGORIE");
	}
	
	private void setGeneralType(SpecificType generalType) {
		if(generalType != null)
			this.generalType = generalType;
		else
			throw new IllegalArgumentException("INVALID GENERALTYPE");
	}
	
	private void setActionSpecificType(TypeAction actionSpecificType) {
		if(listIncludeAtLeast(actionSpecificType,this.generalType.getAllowedActions())) {
			this.actionInnerType = actionSpecificType;
		}
		else 
			throw new IllegalArgumentException("INVALID SPECIFIC TYPE");
	}
	
	private void setDetail(String detail) {
		
		boolean isMonster = this.generalType.equals(SpecificType.MONSTER);
		if(isMonster) {
			if(monstersId.contains(detail)) {
				throw new IllegalArgumentException("THIS MONSTER ID ALREADY EXIST");
			}else if(detail == null || detail.length() == 0) {
				throw new IllegalArgumentException("MONSTER ID MUST NOT BE EMPTY");
			}
			else{
				monstersId.add(detail);
			}
		}
		this.detail = new SimpleStringProperty(detail);

	}
	
	private void setCellId(String cellId) {
		GeneralType allowedTypes[] = {GeneralType.CREATE,GeneralType.DROP};
		if(listIncludeAtLeast(this.categorie,allowedTypes)) {
			try {
				Integer.parseInt(cellId);
			}catch(NumberFormatException e) {
				throw new IllegalArgumentException("INVALID CELLID FOR ACTION");
			}
		}
		this.cellId = new SimpleStringProperty(cellId);
	}
	
	
	private final static boolean listIncludeAtLeast(Object element,Object[] possibleValues) {
		
		boolean doInclude = possibleValues == null || possibleValues.length == 0;
		if(!doInclude){
			int index = 0;
			while(index < possibleValues.length && !doInclude) {
				doInclude = element.equals(possibleValues[index]);
				index++;
			}
		}
				
		return doInclude;
	}

	public GeneralType getCategorie() {
		return this.categorie;
	}
	
	public SpecificType getGeneralType() {
		return this.generalType;
	}
	
	public TypeAction getActionInnerType() {
		return this.actionInnerType;
	}
	
	public StringProperty getDetail() {
		return this.detail;
	}
	
	public StringProperty getCellId() {
		return this.cellId;
	}
	
	
	
	public String getCategorieText() {
		return this.categorie.getValue();
	}
	
	public String getGeneralTypeText() {
		return this.generalType.getValue();
	}
	
	private String objectToString(Object object) {
		String representation = "";
		if(object != null)
			representation = object.toString();
		return representation;
	}
	
	private String propertyToString(Property property) {
		String representation = "";
		if(property != null)
			representation = property.getValue().toString();
		return representation;
	}

	
	public String getSpecificTypeText() {
		return objectToString(this.actionInnerType);
	}
	
	public String getDetailText() {
		return propertyToString(this.detail);
	}
	
	public String getCellIdText() {
		return propertyToString(this.cellId);
	}
	
	@Override
	public String toString() {
		String encode = this.getCategorieText();
		encode += SEPARATOR  + this.getGeneralTypeText();
		encode += SEPARATOR + this.getSpecificTypeText();
		encode += SEPARATOR + this.getDetailText();
		encode += SEPARATOR + this.getCellIdText();
		return encode;
	}

	
}
