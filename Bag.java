package Model;

import java.util.ArrayList;

public class Bag {
	private ArrayList<ArrayList<Item>> Items;
	private int sizeMax = 20;
	private int size = 0;
	private Player player;
	
	public Bag(Player player){
		this.player = player;
	}
	
	public ArrayList getWeapons(){
		return Items.get(0);
	}
	
	public ArrayList getCoins(){
		return Items.get(1);
	}
	
	public ArrayList getPotions(){
		return Items.get(2);
	}
	
	public void addWeapons(Weapon weapon){
		ArrayList Weapons = this.getWeapons();
		if (size <= sizeMax){
			Weapons.add(weapon);
			Items.set(0, Weapons);
			this.size +=1;
		}
	}
		
	public void addCoins(Coin coin){
		ArrayList Coins = this.getCoins();
		if (size <= sizeMax){
			Coins.add(coin);
			Items.set(1, Coins);
		}
	}
	
	public void addPotions(Potion potion){
		ArrayList Potions = this.getPotions();
		if (size <= sizeMax){
			Potions.add(potion);
			Items.set(2, Potions);
			this.size +=1;
		}
	}

}
