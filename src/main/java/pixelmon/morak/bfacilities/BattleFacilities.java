package pixelmon.morak.bfacilities;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;
import pixelmon.morak.bfacilities.commands.CancelBattlefacilities;
import pixelmon.morak.bfacilities.commands.InitiateBattleFacility;
import pixelmon.morak.bfacilities.config.BFacilitiesConfig;
import pixelmon.morak.bfacilities.config.ConfigHelper;
import pixelmon.morak.bfacilities.config.ConfigHolder;
import pixelmon.morak.bfacilities.tempParty.TempParty;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bfacilities")
public class BattleFacilities {

    public static final String MOD_ID = "bfacilities";
    public static BFacilitiesConfig modConfig;

    public BattleFacilities() {
        MinecraftForge.EVENT_BUS.register(new PlayerLoggedOutEventListener());

        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));
        ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC, MOD_ID + "-server.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onModConfigEvent(final net.minecraftforge.fml.config.ModConfig.ModConfigEvent event) {
        final ModConfig config = event.getConfig();
            ConfigHelper.bakeServer(config);
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
