package net.gtaun.shoebill.example.vm;

import net.gtaun.shoebill.event.player.PlayerCommandEvent;
import net.gtaun.shoebill.example.vm.dialog.VehicleManagerDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.util.event.EventManager;
import net.gtaun.util.event.EventManagerNode;
import net.gtaun.util.event.HandlerPriority;

public class PlayerController
{
	private EventManagerNode eventManager;
	
	
	public PlayerController(EventManager rootEventManager)
	{
		eventManager = rootEventManager.createChildNode();
		eventManager.registerHandler(PlayerCommandEvent.class, HandlerPriority.NORMAL, (e) ->
		{
			Player player = e.getPlayer();
			String command = e.getCommand();
			
			if (command.trim().equals("/vm"))
			{
				VehicleManagerDialog.create(player, rootEventManager).show();
				e.setProcessed();
				return;
			}
		});
	}
	
	public void uninitialize()
	{
		eventManager.destroy();
	}
}
