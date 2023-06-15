package pixelmon.morak.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class TempParty {

    final static Logger LOGGER = LoggerFactory.getLogger(TempParty.class);

    private static PlayerPartyStorage playerParty;

    public TempParty(PlayerEntity player) {
        this.playerParty = StorageProxy.getParty(player.getUniqueID());
        playerParty.updatePlayer();
    }

    public void enterTempMode(Pokemon @NotNull [] pokemons) {
        LOGGER.info("Temp-Mode activated");
        playerParty.setInTemporaryMode(true, Color.BLUE, pokemons);
        playerParty.updatePlayer();
    }

    public static void leaveTempMode(PlayerEntity player) {
        if (StorageProxy.getParty(player.getUniqueID()).inTemporaryMode()) {
            StorageProxy.getParty(player.getUniqueID()).setInTemporaryMode(false, Color.BLUE);
            StorageProxy.getParty(player.getUniqueID()).setInTemporaryMode(false, Color.BLUE);
            playerParty.updatePlayer();
            playerParty.updatePlayer();
        }
    }
}
