package de.monver.bfacilities.tempParty;

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
    }

    public void enterTempMode(Pokemon @NotNull [] pokemons) {
        if(playerParty.inTemporaryMode()){
            playerParty.setInTemporaryMode(false, Color.blue);
        }
        playerParty.enterTemporaryMode(Color.BLUE);
        LOGGER.info("Temp-Mode activated");
        for (Pokemon pokemon : pokemons) {
            playerParty.add(pokemon);
        }
    }

    public static void leaveTempMode(PlayerEntity player) {
        if (StorageProxy.getParty(player.getUniqueID()).inTemporaryMode()) {
            StorageProxy.getParty(player.getUniqueID()).setInTemporaryMode(false, Color.BLUE);
        }
    }
}
