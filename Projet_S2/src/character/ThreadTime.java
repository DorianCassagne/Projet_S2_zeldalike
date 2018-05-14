package character;

import model.Game;

public class ThreadTime extends Thread{
	Game game;
	public ThreadTime(Game game) {
		this.game=game;
	}
	
	public void run() {
		while(true){
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println("test th");
			game.turn();
		}
	}
}
