package net.gtaun.shoebill.example.vm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.gtaun.shoebill.resource.Plugin;

public class VehicleManagerPlugin extends Plugin {

    private Logger logger;
    private PlayerController playerController;

    @Override
    protected void onEnable() throws Throwable {
        logger = getLogger();
        playerController = new PlayerController(getEventManager());

        logger.info(getDescription().getName() + " Enabled.");
    }

    @Override
    protected void onDisable() throws Throwable {
        playerController.uninitialize();

        logger.info(getDescription().getName() + " Disabled.");
    }
}
