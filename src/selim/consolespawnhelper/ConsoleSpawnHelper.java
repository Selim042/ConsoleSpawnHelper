package selim.consolespawnhelper;

import org.bukkit.plugin.java.JavaPlugin;

public class ConsoleSpawnHelper extends JavaPlugin {

	public static ConsoleSpawnHelper INSTANCE;

	@Override
	public void onEnable() {
		INSTANCE = this;
		this.getCommand("csummon").setExecutor(new CommandCSummon());
	}

	@Override
	public void onDisable() {
		INSTANCE = null;
	}

}
