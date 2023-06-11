package pixelmon.morak.bfacilities;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pixelmon.morak.bfacilities.commands.CancelBattlefacilities;
import pixelmon.morak.bfacilities.commands.InitiateBattleFacility;
import pixelmon.morak.bfacilities.tempParty.TempParty;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("bfacilities")
public class BattleFacilities {

    public static final String MOD_ID = "bfacilities";

    public BattleFacilities() {
        MinecraftForge.EVENT_BUS.register(new PlayerLoggedOutEventListener());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCommands(RegisterCommandsEvent event){
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
