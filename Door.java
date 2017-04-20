package Model;

public class Door extends GameObject{
	private boolean open;

	public Door(int x,int y,boolean open){
		super(x,y,6);
		this.open = open;
	}

	public boolean getStatut(){
		return open;
	}

	public void setStatut(boolean open){
		open = this.getStatut();
		if (open == false){
			this.open = true;
		}
	}

	@Override
	public boolean isObstacle() {
		boolean open = this.getStatut();
		return open;
	}

}