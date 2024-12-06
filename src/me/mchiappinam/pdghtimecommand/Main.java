package me.mchiappinam.pdghtimecommand;

import java.io.File;
import java.util.Calendar;

import me.mchiappinam.pdghtimecommand.Utils;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	private int diaAutoStart;
	private int horaAutoStart;
	private int minAutoStart;
	protected boolean canStart = true;
	
	@Override
    public void onEnable() {
		File file = new File(getDataFolder(),"config.yml");
		if(!file.exists()) {
			try {
				saveResource("config_template.yml",false);
				File file2 = new File(getDataFolder(),"config_template.yml");
				file2.renameTo(new File(getDataFolder(),"config.yml"));
			}
			catch(Exception e) {}
		}
		
		diaAutoStart = Utils.strToCalendar(getConfig().getString("Dia.AutoStart.Hora"));
		horaAutoStart = Integer.parseInt(getConfig().getString("AutoStart.Hora").substring(0,2));
		minAutoStart = Integer.parseInt(getConfig().getString("AutoStart.Hora").substring(2,4));
		getLogger().info("Hora = "+(horaAutoStart<10?"0"+horaAutoStart:horaAutoStart)+":"+(minAutoStart<10?"0"+minAutoStart:minAutoStart));
		
		getServer().getScheduler().runTaskTimer(this, new Runnable() {
			public void run() {
				if(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)==diaAutoStart)
					if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)==horaAutoStart)
						if(Calendar.getInstance().get(Calendar.MINUTE)==minAutoStart)
							getServer().dispatchCommand(getServer().getConsoleSender(), "comando");
							//prepareGladiador();
			}
		}, 0, 1000);
}
	}
