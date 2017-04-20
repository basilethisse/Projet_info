package Model;

import java.util.ArrayList;

public class Enemy extends Character implements Demisable, AttackableObserver{
	private int life;
	private boolean enemyInView = false;
	private Player player;
    private Weapon weapon;

	private ArrayList<DemisableObserver> observers = new ArrayList<DemisableObserver>();

	
	public Enemy(int x, int y,int life){
		super(x,y,7);
		this.life = life;
		Weapon weapon = new Weapon(x,y,8,1);
	}
	
	@Override
	public void move(int X, int Y){
		super.posX = this.posX + X;
		this.posY = this.posY + Y;
	}
	
	
	@Override
	public void attacked(Attackable e){
		Weapon weapon = (Weapon) e;
		boolean distanceX = Math.abs(this.getPosX() - weapon.getPosX()) <= weapon.getRange();
		boolean distanceY = Math.abs(this.getPosY() - weapon.getPosY()) <= weapon.getRange();
		if(distanceX && distanceY){
			demisableNotifyObserver();	
		}
	}

	@Override
	public void demisableAttach(DemisableObserver po) {
		observers.add(po);	
	}

	@Override
	public void demisableNotifyObserver() {
		for (DemisableObserver o : observers) {
			o.demise(this, null);
		}
	}
}
