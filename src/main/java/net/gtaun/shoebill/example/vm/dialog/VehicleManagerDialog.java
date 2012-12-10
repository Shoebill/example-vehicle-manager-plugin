package net.gtaun.shoebill.example.vm.dialog;

import java.util.Comparator;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleManagerDialog extends AbstractListDialog
{
	public VehicleManagerDialog(Player player, Shoebill shoebill, EventManager eventManager)
	{
		super(player, shoebill, eventManager);
		setCaption("Vehicle Manager");
	}
	
	private final Comparator<Vehicle> VEHICLE_DISTANCE_COMPARATOR = new Comparator<Vehicle>()
	{
		@Override
		public int compare(Vehicle o1, Vehicle o2)
		{
			Location playerLoc = player.getLocation();
			return (int) (playerLoc.distance(o1.getLocation()) - playerLoc.distance(o2.getLocation()));
		}
	};
	
	@Override
	public void show()
	{
		if (player.getVehicle() != null) dialogListItems.add(new DialogListItem("My Vehicle")
		{
			@Override
			public void onItemSelect()
			{
				Vehicle vehicle = player.getVehicle();
				if (vehicle != null) new VehicleDialog(vehicle, player, shoebill, eventManager).show();
			}
		});
		
		dialogListItems.add(new DialogListItem("List all vehicles, Sort by distance")
		{
			@Override
			public void onItemSelect()
			{
				new VehicleListDialog(player, shoebill, eventManager, VEHICLE_DISTANCE_COMPARATOR).show();
			}
		});
		
		super.show();
	}
}
