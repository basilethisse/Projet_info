package Model;

import java.util.ArrayList;

public class Weapon extends Item implements Demisable, Attackable, AttackableObserver{
	private int range;
	
	protected ArrayList<DemisableObserver> demisableObservers = new ArrayList<DemisableObserver>();
	private ArrayList<AttackableObserver> attackableObservers = new ArrayList<AttackableObserver>();
	
	public Weapon(int x,int y,int color,int range){
		super(x,y,color);
		this.range = range;	
	}
	
	public int getRange(){
		return range;
	}
	
	public void looseLife() {
		this.demisableNotifyObserver();
		this.attackableNotifyObserver();
	}
	
	
//////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void attacked(Attackable e){
		Weapon weapon = (Weapon) e;
		boolean distanceX = Math.abs(this.getPosX() - weapon.getPosX()) <= weapon.getRange();
		boolean distanceY = Math.abs(this.getPosY() - weapon.getPosY()) <= weapon.getRange();
		if(distanceX || distanceY){
			demisableNotifyObserver();	
		}
	}

	@Override
	synchronized public void demisableNotifyObserver() {
		ArrayList<GameObject> loot = new ArrayList<GameObject>();
		int range = this.getRange();
		int x = this.getPosX();
		int y = this.getPosY();
		for(int i = x-range; i <= x+range; i++){
			Damage damage = new Damage(i,y);
			for(DemisableObserver observer : demisableObservers){
				damage.demisableAttach(observer);
			}
			loot.add(damage);
		}
		for(int i = y-range; i <= y+range; i++){
			Damage damage = new Damage(x,i);
			for(DemisableObserver observer : demisableObservers){
				damage.demisableAttach(observer);
			}
			loot.add(damage);
		}
		for (DemisableObserver o : this.demisableObservers) {
			o.demise(this, loot);
		}
	}
	
	
	@Override
	public void attackableAttach(AttackableObserver eo) {
		attackableObservers.add(eo);
	}

	@Override
	public void attackableNotifyObserver() {
		for (AttackableObserver o : attackableObservers) {
			o.attacked(this);
		}
	}
		
}
