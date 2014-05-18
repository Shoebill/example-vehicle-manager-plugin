package net.gtaun.shoebill.example.vm.dialog;

import java.util.Arrays;

import net.gtaun.shoebill.common.dialog.ListDialog;
import net.gtaun.shoebill.common.dialog.ListDialogItem;
import net.gtaun.shoebill.constant.VehicleComponentModel;
import net.gtaun.shoebill.constant.VehicleComponentSlot;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleComponentDialog extends ListDialog
{
	// Shoebill 1.0 Style
	public static ListDialog create(Player player, EventManager rootEventManager, Vehicle veh)
	{
		return ListDialog.create(player, rootEventManager)
			.caption(String.format("Vehicle Component - %s (Id: %d)", veh.getModelName(), veh.getId()))
			.execute((b) -> Arrays.stream(VehicleComponentSlot.values()).forEach((slot) ->
			{
				// XXX: Buggy Eclipse JDT Compiler
				((AbstractListDialogBuilder<?, ?>) b).item(String.format("Slot %s: %s", slot.name(), VehicleComponentModel.getName(veh.getComponent().get(slot))));
			}))
			.build();
	}
	
	
	// Shoebill Milestone 2 Style
	public VehicleComponentDialog(Player player, EventManager eventManager, Vehicle veh)
	{
		super(player, eventManager);
		setCaption(String.format("Vehicle Component - %s (Id: %d)", veh.getModelName(), veh.getId()));
		
		for (VehicleComponentSlot slot : VehicleComponentSlot.values())
		{
			int componentId = veh.getComponent().get(slot);
			String item = String.format("Slot %s: %s", slot.name(), VehicleComponentModel.getName(componentId));
			items.add(new ListDialogItem(item));	
		}
	}
}
