package Model;

import java.util.ArrayList;

public abstract class Item extends GameObject implements Demisable{
	protected ArrayList<DemisableObserver> demisableObservers = new ArrayList<DemisableObserver>();
	
	
	public Item(int x,int y,int color){
		super(x,y,color);
	}
	
	public boolean isObstacle(){
		return false;
	}
	
	@Override
	public void demisableAttach(DemisableObserver po) {
		demisableObservers.add(po);		
	}

	@Override
	public void demisableNotifyObserver() {
		for (DemisableObserver o : demisableObservers) {
			o.demise(this, null);
		}	
	}
	
}
