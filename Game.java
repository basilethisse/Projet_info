package Model;
import View.Window;

import java.util.ArrayList;
import java.util.Random;

public class Game implements DemisableObserver{
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	private Window window;
	private int size;
	private int bombTimer = 300;
	private int numberOfBreakableBlocks = 20;
	
	public Game(Window window,int size){
		this.window = window;
		this.size = size;

		// Creating one Player at position (10,10)
		objects.add(new Player(10,10,3));
		
		objects.add(new Enemy(6,6,3));
		
		// Map building 
		int j = 0; // map number
		int p = 1; // numéro de la map impaire
		int q = 1; // numéro de la map paire
		while (j<2){
			if (j==0){
				for(int i = 0; i < size; i++){
					objects.add(new BlockUnbreakable(i,0));
					objects.add(new BlockUnbreakable(0,i));
					objects.add(new BlockUnbreakable(i, size-1));
					objects.add(new BlockUnbreakable(size-1, i));
				}
				int index = 2*size+6; // index of the door in the list
				GameObject blockReplaced = objects.get(index);
				int x = blockReplaced.getPosX();
				int y = blockReplaced.getPosY();
				objects.set(index,new Door(x,y,false)); // replace the block by a door in the list
			}else if (j%2==1){
				for(int i = p*size; i < (p+1)*size; i++){
                    objects.add(new BlockUnbreakable((p-1)*size-1,i));
                    objects.add(new BlockUnbreakable(i, (p-1)*size-1));
                    objects.add(new BlockUnbreakable((p+1)*size-1, i));
				}
				int index = 4*size+6+(j-1)*3; // on saute les maps d'avant
				GameObject blockReplaced = objects.get(index);
				int x = blockReplaced.getPosX();
				int y = blockReplaced.getPosY();
				objects.set(index,new Door(x,y,false));
				p++;
			}else{
				for(int i = q*size-1; i < (q+1)*size; i++){
					objects.add(new BlockUnbreakable(i,q*size-1));
					objects.add(new BlockUnbreakable(i,(q+1)*size-1));
					objects.add(new BlockUnbreakable((q+1)*size-1,i));
				}
				int index = 5*size+6+(j-1)*3;
				GameObject blockReplaced = objects.get(index);
				int x = blockReplaced.getPosX();
				int y = blockReplaced.getPosY();
				objects.set(index,new Door(x,y,false));
				q++;
			}
		j++; // j maps
		}	
		
		Random rand = new Random();
		for(int i = 0; i < numberOfBreakableBlocks; i++){
			int x = rand.nextInt(size-4)+2;
			int y = rand.nextInt(size-4)+2;
			BlockBreakable block = new BlockBreakable(x,y);
			block.demisableAttach(this);
			objects.add(block);
		}
		window.setGameObjects(this.getGameObjects());
		notifyView();
	}
	
	
	
	
	public void Attack(String weaponType, int playerNumber){
		Player player = ((Player) objects.get(playerNumber));
		
		Weapon weaponAttacked = (Weapon) player.Attack(weaponType);
		if(weaponAttacked != null){
			weaponAttacked.demisableAttach(this);
			for(GameObject object : objects){
				if(object instanceof Attackable){
					((Weapon) object).attackableAttach(((AttackableObserver) weaponAttacked));
				}
				if(object instanceof AttackableObserver){
					weaponAttacked.attackableAttach(((AttackableObserver) object));
				}
			}
			objects.add(weaponAttacked);
			notifyView();
		}
	}
	
	public void movePlayer(int x, int y, int playerNumber){
		Player player = ((Player) objects.get(playerNumber));
		int nextX = player.getPosX()+x;
		int nextY = player.getPosY()+y;
		
		boolean obstacle = false;
		for(GameObject object : objects){
			if(object.isAtPosition(nextX, nextY)){
				obstacle = object.isObstacle();
			}
			if(obstacle == true){
				break;
			}
		}
		
		if(obstacle == false){
			player.move(x,y);
			notifyView();
		}
	}
	
	private void notifyView(){
		window.update();
	}

	public ArrayList<GameObject> getGameObjects(){
		return this.objects;
	}
	
	@Override
	synchronized public void demise(Demisable ps, ArrayList<GameObject> loot) {
		objects.remove(ps);
		if(loot != null){
			objects.addAll(loot);
		}
		notifyView();
	}	
}