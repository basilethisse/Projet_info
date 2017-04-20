package Model;

import java.util.ArrayList;

public class Damage extends GameObject implements Demisable {
	
	private ArrayList<DemisableObserver> observers = new ArrayList<DemisableObserver>();
	
	public Damage(int X, int Y) {
		super(X, Y, 3);
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
	public boolean isObstacle() {
		return false;
	}
	
	public void looseLife() {
		while(true){
			this.demisableNotifyObserver();
		}
	}
}
