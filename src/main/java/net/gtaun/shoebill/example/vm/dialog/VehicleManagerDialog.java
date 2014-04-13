package net.gtaun.shoebill.example.vm.dialog;

import java.util.Comparator;

import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleManagerDialog
{
	public static ListDialog create(Player player, EventManager rootEventManager)
	{
		Comparator<Vehicle> distanceComparator = (veh1, veh2) ->
		{
			Location loc = player.getLocation();
			return (int) (loc.distance(veh1.getLocation()) - loc.distance(veh2.getLocation()));
		};
		
		return ListDialog.create(player, rootEventManager)
			.caption("Vehicle Manager")
			.item("My Vehicle", () -> player.isInAnyVehicle(), (d) -> VehicleDialog.create(player, rootEventManager, player.getVehicle()))
			.item("List all vehicles (Sort by distance)", (d) -> new VehicleListDialog(player, rootEventManager, distanceComparator).show())
			.build();
	}
}
