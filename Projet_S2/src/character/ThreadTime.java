package character;

import model.Game;

public class ThreadTime extends Thread{
	private Game game;
	private boolean run;
	public ThreadTime(Game game) {
		this.game=game;
	}
	
	public void run() {
		//run =false;
		while(true){
			//	if(run) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("test th");
				game.turn();
			//}
		}
	}
	public void startTime() {
		this.run =true;
	}
	
	public void stopTime() {
		this.run=false;
	}
	
	public void changeRun() {
		this.run=!this.run;
	}
}
