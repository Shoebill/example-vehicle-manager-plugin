package net.gtaun.shoebill.example.vm.dialog;

import net.gtaun.shoebill.Shoebill;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.shoebill.object.VehicleComponent;
import net.gtaun.util.event.EventManager;

public class VehicleDialog extends AbstractListDialog
{
	private final Vehicle vehicle;
	
	
	public VehicleDialog(Vehicle vehicle, Player player, Shoebill shoebill, EventManager eventManager)
	{
		super(player, shoebill, eventManager);
		this.vehicle = vehicle;
		
		setCaption("Vehicle - Id: " + vehicle.getId() + " Model: " + vehicle.getModelId());
	}
	
	@Override
	public void show()
	{
		if (player.getVehicle() != vehicle) dialogListItems.add(new DialogListItem("Enter")
		{
			@Override
			public void onItemSelect()
			{
				player.setVehicle(vehicle);
			}
		});
		
		if (player.getVehicle() != vehicle) dialogListItems.add(new DialogListItem("Fetch")
		{
			@Override
			public void onItemSelect()
			{
				vehicle.setLocation(player.getLocation());
			}
		});
		
		dialogListItems.add(new DialogListItem("Repair")
		{
			@Override
			public void onItemSelect()
			{
				VehicleDialog.this.vehicle.repair();
			}
		});
		
		if (VehicleDialog.this.vehicle.isStatic() == false) dialogListItems.add(new DialogListItem("Destroy")
		{
			@Override
			public void onItemSelect()
			{
				VehicleDialog.this.vehicle.destroy();
			}
		});
		
		dialogListItems.add(new DialogListItem("Respawn")
		{
			@Override
			public void onItemSelect()
			{
				VehicleDialog.this.vehicle.respawn();
			}
		});
		
		dialogListItems.add(new DialogListItem("Boom")
		{
			@Override
			public void onItemSelect()
			{
				VehicleDialog.this.vehicle.setHealth(0.0f);
			}
		});
		
		dialogListItems.add(new DialogListItem("Component")
		{
			@Override
			public void onItemSelect()
			{
				VehicleComponent component = VehicleDialog.this.vehicle.getComponent();
				new VehicleComponentDialog(component, VehicleDialog.this.player, VehicleDialog.this.shoebill, VehicleDialog.this.eventManager).show();
			}
		});
		
		super.show();
	}
}