package Model;

import java.util.ArrayList;

public class Player extends Character implements DemisableObserver, Demisable, AttackableObserver{
	private Bag Bag ;
	private int life;
	private Player Player;
	
	private ArrayList<DemisableObserver> observers = new ArrayList<DemisableObserver>();
	
	
	public Player(int x, int y,int life){
		super(x,y,2);
		Bag Bag = new Bag(this.Player);
		this.Bag = Bag;
		this.life = life;
	}
	
	@Override
	public void move(int X, int Y){
		super.posX = this.posX + X;
		this.posY = this.posY + Y;
	}

	@Override
	public void attacked(Attackable e) {
		
	}
	
	
	public Item Attack(String type){
		Item weapon = null;
		if(type.equals("weapon")){
			weapon = new Weapon(this.posX, this.posY, 8, 2);
			weapon.demisableAttach(this);
			((Weapon) weapon).looseLife();
			return weapon;
		}
		return null;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
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

	
	@Override
	public void demise(Demisable ps, ArrayList<GameObject> loot) {	
	}
	
	
}

