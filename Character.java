package Model;

import java.util.ArrayList;

public abstract class Character extends GameObject {
	private int life = 0;
	
	public Character(int x,int y,int color){
		super(x,y,color);
	}
	
	public abstract void move(int x,int y);
	
	public void looseLife(int damage){
		this.life -= damage;
	}
	
	@Override
	public boolean isObstacle() {
		return false;
	}
	
}
