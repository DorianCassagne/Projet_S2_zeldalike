package model.event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Scenario {
	
	private final static String COMMENT = "//";
	private ObservableList<Evenement> myEvents;
	
	public Scenario() {
		this.myEvents = FXCollections.observableArrayList();
	}
	
	public void addEvent(Evenement event) {
		if(event != null)
			this.myEvents.add(event);
		else
			throw new IllegalArgumentException("INVALID EVENT");
	}
	
	public void deleteEvent(int index) {
		if(index >= 0 && index < myEvents.size())
			this.myEvents.remove(index);
	}
	
	public Evenement getEventAt(int index) {
		return this.myEvents.get(index);
	}
	
	public ObservableList<Evenement> getEvents(){
		return this.myEvents;
	}
	@Override
	public String toString() {
		String scenario = "";
		
		for(Evenement event : myEvents) {
			scenario += COMMENT + event.getComment() + "\n";
			scenario += event.toString() + "\n";
			
		}
		return scenario;

	}
	
	
	public String generateScenario() {
		String path = System.getProperty("user.home")+"/Scenario-ZeldaLike.txt";
		File file = new File(path);
		if(file.exists()) {
			try{
				PrintWriter writer = new PrintWriter(path,"UTF-8");
				writer.println(this.toString());
				writer.close();
			}catch (Exception e) {
				e.printStackTrace();
				throw new IllegalArgumentException("THIS FILE WAS NOT SAVED");
			}		

		}
		else
			throw new IllegalArgumentException("THIS FILE ALREADY EXISTS PLEASE REMOVE IT");
		return path;
	
	}
	
	
}
