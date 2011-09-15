package de.bananaco.orbconomy;

import org.bukkit.entity.Player;

public class OrbBalance {
	
	private final Player player;
	protected OrbBalance(Player player) {
		this.player = player;
	}
	
	public int getAmount() {
		int amount = player.getTotalExperience();
		return amount;
		
	}
	
	public void setAmount(int amount) {
		if(getAmount() > amount)
			subtract(getAmount() - amount);
		if(getAmount() < amount)
			add(amount - getAmount());
	}
	
	public void add(int amount) {
		for(int i=0; i<amount; i++)
			player.setExperience(player.getExperience()+1);
	}
	
	public void subtract(int amount) {
		for(int i=0; i<amount; i++)
			if(player.getExperience() > 0)
			player.setExperience(player.getExperience()-1);
			else if(player.getTotalExperience() > 0)
				player.setTotalExperience(player.getTotalExperience()-1);
			else {
				System.err.println("Cannot set balance lower than 0");
			}
	}
	
	public boolean hasOver(int amount) {
		return (player.getTotalExperience() > amount);
	}
	
	public boolean hasEnough(int amount) {
		return (player.getTotalExperience() >= amount);
	}
	
	@Override
	public String toString() {
		return player.getName() + "=" + String.valueOf(player.getTotalExperience());
	}
	

}
