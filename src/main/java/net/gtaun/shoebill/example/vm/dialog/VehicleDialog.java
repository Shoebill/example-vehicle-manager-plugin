package net.gtaun.shoebill.example.vm.dialog;

import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleDialog
{
	public static ListDialog create(Player player, EventManager rootEventManager, Vehicle veh)
	{
		return ListDialog.create(player, rootEventManager)
			.caption(String.format("%s (Id: %d)", veh.getModelName(), veh.getId()))
			.item("Enter",		() -> player.getVehicle() != veh,	(d) -> player.setVehicle(veh))
			.item("Fetch",		() -> player.getVehicle() != veh,	(d) -> veh.setLocation(player.getLocation()))
			.item("Repair",		() -> veh.getHealth() < 100.0f,		(d) -> veh.repair())
			.item("Destroy",	() -> !veh.isStatic(),				(d) -> veh.destroy())
			.item("Respawn",	(d) -> veh.respawn())
			.item("Boom",		(d) -> veh.setHealth(0.0f))
			.item("Components", (d) -> new VehicleComponentDialog(player, rootEventManager, veh).show())
			.build();
	}
}
