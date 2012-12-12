package net.gtaun.shoebill.example.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gtaun.shoebill.resource.Plugin;

public class VehicleManagerPlugin extends Plugin
{
	private static final Logger LOGGER = LoggerFactory.getLogger(VehicleManagerPlugin.class);
	
	
	private PlayerManager playerManager;
	
	
	public VehicleManagerPlugin()
	{
		
	}
	
	@Override
	protected void onEnable() throws Throwable
	{
		playerManager = new PlayerManager(getShoebill(), getEventManager());
		
		LOGGER.info(getDescription().getName() + " Enabled.");
	}
	
	@Override
	protected void onDisable() throws Throwable
	{
		playerManager.uninitialize();
		
		LOGGER.info(getDescription().getName() + " Disabled.");
	}
}
