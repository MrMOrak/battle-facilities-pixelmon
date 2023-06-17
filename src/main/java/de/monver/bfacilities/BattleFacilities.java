package de.monver.bfacilities;

import de.monver.bfacilities.commands.InitiateBattleFacility;
import de.monver.bfacilities.config.BFacilitiesConfig;
import de.monver.bfacilities.tempParty.TempParty;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import de.monver.bfacilities.commands.CancelBattlefacilities;

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
        InitiateBattleFacility.register(event.getDispatcher());
        CancelBattlefacilities.register(event.getDispatcher());
    }

    public static class PlayerLoggedOutEventListener {
        @SubscribeEvent
        public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
            TempParty.leaveTempMode(event.getPlayer());
        }
    }

}