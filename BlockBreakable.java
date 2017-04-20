package Model;

import java.util.ArrayList;

public class BlockBreakable extends Block implements Demisable, AttackableObserver{

	private ArrayList<DemisableObserver> observers = new ArrayList<DemisableObserver>();
	
	public BlockBreakable(int X, int Y) {
		super(X, Y, 1);
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
	public void attacked(Attackable e) {
		Weapon weapon = (Weapon) e;
		boolean distanceX = Math.abs(this.getPosX() - weapon.getPosX()) <= weapon.getRange();
		boolean distanceY = Math.abs(this.getPosY() - weapon.getPosY()) <= weapon.getRange();
		if(distanceX && distanceY){
			demisableNotifyObserver();	
		}
	}
	
}