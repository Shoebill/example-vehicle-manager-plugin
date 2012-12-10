package net.gtaun.shoebill.example.vm.dialog;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import net.gtaun.shoebill.SampObjectStore;
import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.data.Location;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleListDialog extends AbstractPageListDialog
{
	private Comparator<Vehicle> vehicleComparator;
	
	
	public VehicleListDialog(Player player, Shoebill shoebill, EventManager eventManager, Comparator<Vehicle> comparator)
	{
		super(player, shoebill, eventManager);
		this.vehicleComparator = comparator;
	}
	
	@Override
	public void show()
	{
		SampObjectStore store = shoebill.getSampObjectStore();
		Collection<Vehicle> vehicles = store.getVehicles();
		
		Location playerLoc = player.getLocation();
		
		SortedSet<Vehicle> sortedVehicles = new TreeSet<>(vehicleComparator);
		for (Vehicle vehicle : vehicles) sortedVehicles.add(vehicle);
		
		dialogListItems.clear();
		for (final Vehicle vehicle : sortedVehicles)
		{			
			final float distance = playerLoc.distance(vehicle.getLocation());
			dialogListItems.add(new DialogListItem("ID: " + vehicle.getId() + "		Model: " + vehicle.getModelId() + "	" + "Distance: " + distance + "\n")
			{
				@Override
				public void onItemSelect()
				{
					new VehicleDialog(vehicle, player, shoebill, eventManager).show();
				}
			});
		}

		setCaption("Vehicle List - Page " + getCurrentPage() + "/" + getMaxPage());
		super.show();
	}
}
