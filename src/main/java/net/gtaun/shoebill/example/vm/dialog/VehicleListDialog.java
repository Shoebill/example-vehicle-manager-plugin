package net.gtaun.shoebill.example.vm.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.gtaun.shoebill.common.dialog.ListDialogItem;
import net.gtaun.shoebill.common.dialog.PageListDialog;
import net.gtaun.shoebill.object.Player;
import net.gtaun.shoebill.object.Vehicle;
import net.gtaun.util.event.EventManager;

public class VehicleListDialog extends PageListDialog {
    private Comparator<Vehicle> vehicleComparator;


    public VehicleListDialog(Player player, EventManager eventManager, Comparator<Vehicle> comparator) {
        super(player, eventManager);
        this.vehicleComparator = comparator;
        setCaption((d) -> String.format("Vehicle List - Page %d/%d", getCurrentPage() + 1, getMaxPage() + 1));
    }

    @Override
    public void show() {
        List<Vehicle> vehicles = new ArrayList<>(Vehicle.get());
        Collections.sort(vehicles, vehicleComparator);

        items.clear();
        for (final Vehicle vehicle : vehicles) {
            float distance = player.getLocation().distance(vehicle.getLocation());
            String item = String.format("%s (Id: %d), Distance: %f", vehicle.getModelName(), vehicle.getId(), distance);
            items.add(new ListDialogItem(item, (d) -> VehicleDialog.create(player, eventManagerNode.getParent(), vehicle).show()));
        }

        super.show();
    }
}
