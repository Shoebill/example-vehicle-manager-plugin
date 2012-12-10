package net.gtaun.shoebill.example.vm.dialog;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.constant.VehicleComponentSlot;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.VehicleComponent;
import net.gtaun.util.event.EventManager;

public class VehicleComponentDialog extends AbstractListDialog
{
	private VehicleComponent vehicleComponent;
	

	public VehicleComponentDialog(VehicleComponent component, Player player, Shoebill shoebill, EventManager eventManager)
	{
		super(player, shoebill, eventManager);
		this.vehicleComponent = component;
		
		Vehicle vehicle = vehicleComponent.getVehicle();
		setCaption("Vehicle Component - Id: " + vehicle.getId() + " Model: " + vehicle.getModelId());
	}
	
	@Override
	public void show()
	{
		dialogListItems.clear();
		
		for (VehicleComponentSlot slot : VehicleComponentSlot.values())
		{
			int componentId = vehicleComponent.get(slot);
			String itemName = "Slot " + slot.getValue() + ": " + componentId;
			dialogListItems.add(new DialogListItem(itemName)
			{
				@Override
				public void onItemSelect()
				{
					
				}
			});	
		}
		
		super.show();
	}
}
