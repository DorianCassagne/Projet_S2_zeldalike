package controleur;


public class Movement {

	private int key; 			//id
	private Coordinate start; 		//depart (x,y)
	private Coordinate finish;		//arrive (x,y)
	private int duration; 			//duree le nombre de tick pour un mouvement, ou le nombre de frame pour accomplir un mouvement
	private int progress; 			//avancement progression  
	private int imageValue;			//image a afficher lors du deplacement 


	public Movement(int key, Coordinate start, Coordinate finish, int duration, int imagevalue) {
		this.setKey(key);
		this.start=start;
		this.finish=finish;
		this.setDuration(duration);
		this.progress=0;
		this.imageValue=imagevalue;
	}

	//DEBUT GETTERS SETTERS 
	public double getKey() {
		return key;
	}

	public void setKey(int key) {
		if(key < 0) {
			throw new Error ("Movement 'Key' is below 0");
		}
		else{
			this.key = key;
		}
	}
	
	public Coordinate getStart() {
		return start;
	}

	public void setStart(Coordinate start) {
		this.start = start;
	}

	public Coordinate getFinish() {
		return finish;
	}

	public void setFinish(Coordinate finish) {
		this.finish = finish;
	}

	public double getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		if(key < 0) {
			throw new Error ("Movement 'Duration' is below 0");
		}
		else{
			this.duration = duration;
		}
	}
	
	public int getProgress() {
		return progress;
	}
	
	public void setProgress(int progress) {
		this.progress = progress;
	}
		
	public int getImagevalue() {
		return imageValue;
	}

	public void setImagevalue(int imagevalue) {
		this.imageValue = imagevalue;
	}

	// FIN DES GETTERS SETTERS
	
	
	public void incrementer() {
		this.progress++;
	}
	
	public Coordinate doTick() { 
		double ratio= (double)this.progress/(double)this.duration;
		Coordinate coord;
		
		if( start.superieurX(finish)) {
			if(start.superieurY(finish)){
				coord= new Coordinate(this.getStart().getPosX()+ratio, this.getStart().getPosY()+ratio);
			}
			else if(!start.superieurY(finish)) {
				coord= new Coordinate(this.getStart().getPosX()+ratio, this.getStart().getPosY()-ratio);
			}
			else {
				coord= new Coordinate(this.getStart().getPosX()+ratio, this.getStart().getPosY());
			}
		}
		else if( !start.superieurX(finish)){	
			if(start.superieurY(finish)){
				coord= new Coordinate(this.getStart().getPosX()-ratio, this.getStart().getPosY()+ratio);
			}
			else if(!start.superieurY(finish)){
				coord= new Coordinate(this.getStart().getPosX()-ratio, this.getStart().getPosY()-ratio);
			}
			else {
				coord= new Coordinate(this.getStart().getPosX()-ratio, this.getStart().getPosY());
			}
		}
		else {
			if(start.superieurY(finish)){
				coord= new Coordinate(this.getStart().getPosX(), this.getStart().getPosY()+ratio);
			}
			else if(!start.superieurY(finish)){
				coord= new Coordinate(this.getStart().getPosX(), this.getStart().getPosY()-ratio);
			}
			else {
				coord= new Coordinate(this.getStart().getPosX(), this.getStart().getPosY());
			}
		}
		
		this.incrementer();
		return coord;
	}
	
	
	
}
