package de.monver.bfacilities.utils;

import de.monver.bfacilities.events.CommandInterceptEvent;
import de.monver.bfacilities.events.PokemonSellEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModIntegration {

    final static Logger LOGGER = LoggerFactory.getLogger(ModIntegration.class);

    public static boolean isModLoaded(String modId){
        return ModList.get().isLoaded(modId);
    }

    public static void initialize(){
        if (isModLoaded("envygts")){
            LOGGER.info("EnvyGTS detected, added interception");
            MinecraftForge.EVENT_BUS.register(new PokemonSellEvent());
        } else if (isModLoaded("envysts")) {
            LOGGER.info("EnvySTS detected, added interception");
            MinecraftForge.EVENT_BUS.register(new CommandInterceptEvent());
        } else {
            LOGGER.info("no integration action was neccessary");
        }
    }
}
