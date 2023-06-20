package de.monver.bfacilities;

import com.pixelmonmod.pixelmon.Pixelmon;
import de.monver.bfacilities.commands.bfacility;
import de.monver.bfacilities.config.BFacilitiesConfig;
import de.monver.bfacilities.events.DexAdditionEvent;
import de.monver.bfacilities.tempParty.TempParty;
import de.monver.bfacilities.utils.ModIntegration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bfacilities")
public class BattleFacilities {

    public static final String MOD_ID = "bfacilities";
    public static BFacilitiesConfig modConfig;

    public BattleFacilities() {
        MinecraftForge.EVENT_BUS.register(new PlayerLoggedOutEventListener());

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, BFacilitiesConfig.SPEC, MOD_ID + "-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event) {
        bfacility.register(event.getDispatcher());
    }

    @SubscribeEvent
    public void serverStarting(FMLServerStartingEvent event){
        Pixelmon.EVENT_BUS.register(new DexAdditionEvent());
        ModIntegration.initialize();
    }

    public static class PlayerLoggedOutEventListener {
        @SubscribeEvent
        public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            TempParty.leaveTempMode(event.getPlayer());
        }
    }

}
