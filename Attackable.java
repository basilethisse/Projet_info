package Model;

public interface Attackable {
	void attackableAttach(AttackableObserver po);
	void attackableNotifyObserver();
}
