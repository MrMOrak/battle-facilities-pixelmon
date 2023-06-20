package de.monver.bfacilities.utils;

import com.envyful.gts.forge.EnvyGTSForge;
import de.monver.bfacilities.events.PokemonSellEvent;
import de.monver.bfacilities.gui.TeamSelectionContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModIntegration {

    final static Logger LOGGER = LoggerFactory.getLogger(TeamSelectionContainer.class);

    public static boolean isModLoaded(String modId){
        return ModList.get().isLoaded(modId);
    }

    public static void initialize(){
        if (isModLoaded("envygts")){
            LOGGER.info("envygts detected");
            MinecraftForge.EVENT_BUS.register(new PokemonSellEvent());
        } else {
            LOGGER.info("no envygts detected");
        }
    }
}
