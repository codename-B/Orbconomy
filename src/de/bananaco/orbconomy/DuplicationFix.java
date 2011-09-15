package de.bananaco.orbconomy;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DuplicationFix extends EntityListener {
	
	private final OrbconomyManager ocm;
	public static HashMap<String, Integer> ocmBalances;
	public DuplicationFix(JavaPlugin p) {
		new DuplicationFixPart2(p);
		this.ocm = Orbconomy.ocm; 
		ocmBalances = new HashMap<String, Integer>();
	}
	
	public void onEntityDeath(EntityDeathEvent event) {
		if(event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			ocmBalances.put(player.getName(), ocm.getBalance(player).getAmount());
			ocm.getBalance(player).setAmount(0);
		}
	}

}
class DuplicationFixPart2 extends PlayerListener {
	private final OrbconomyManager ocm;
	protected DuplicationFixPart2(JavaPlugin p) {
		this.ocm = Orbconomy.ocm; 
		p.getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, this, Priority.Normal, p);
	}
	
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		if(DuplicationFix.ocmBalances.get(player.getName()) == null)
			return;
		int amount = DuplicationFix.ocmBalances.get(player.getName());
		
		ocm.getBalance(player).setAmount(amount);
	}
	
}