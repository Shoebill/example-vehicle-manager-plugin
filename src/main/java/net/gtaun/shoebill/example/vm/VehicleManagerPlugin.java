package net.gtaun.shoebill.example.vm;

import org.slf4j.Logger;

import net.gtaun.shoebill.resource.Plugin;

public class VehicleManagerPlugin extends Plugin
{
	private static Logger logger;
	public Logger logger()
	{
		return logger;
	}
	
	
	private PlayerManager playerManager;
	
	
	public VehicleManagerPlugin()
	{
		
	}
	
	@Override
	protected void onEnable() throws Throwable
	{
		logger = getLogger();
		
		playerManager = new PlayerManager(getShoebill(), getEventManager());
		
		logger().info(getDescription().getName() + " Enabled.");
	}
	
	@Override
	protected void onDisable() throws Throwable
	{
		playerManager.uninitialize();
		
		logger().info(getDescription().getName() + " Disabled.");
	}
}
