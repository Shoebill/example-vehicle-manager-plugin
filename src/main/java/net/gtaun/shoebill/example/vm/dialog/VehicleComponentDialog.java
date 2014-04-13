package net.gtaun.shoebill.example.vm.dialog;

import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.shoebill.common.dialog.ListDialogItem;
import net.gtaun.shoebill.constant.VehicleComponentModel;
import net.gtaun.shoebill.constant.VehicleComponentSlot;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.VehicleComponent;
import net.gtaun.util.event.EventManager;

public class VehicleComponentDialog extends ListDialog
{
	private VehicleComponent vehicleComponent;
	
	
	public VehicleComponentDialog(Player player, EventManager eventManager, Vehicle veh)
	{
		super(player, eventManager);
		this.vehicleComponent = veh.getComponent();
		setCaption(String.format("Vehicle Component - %s (Id: %d)", veh.getModelName(), veh.getId()));
	}
	
	@Override
	public void show()
	{
		items.clear();
		for (VehicleComponentSlot slot : VehicleComponentSlot.values())
		{
			int componentId = vehicleComponent.get(slot);
			String item = String.format("Slot %s: %s", slot.name(), VehicleComponentModel.getName(componentId));
			items.add(new ListDialogItem(item));	
		}
		
		super.show();
	}
}
