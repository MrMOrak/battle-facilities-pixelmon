package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PlayerPartyStorage;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.PlayerEntity;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class TempParty {

    private static PlayerPartyStorage playerParty;

    public TempParty(PlayerEntity player) {
        this.playerParty = StorageProxy.getParty(player.getUniqueID());
    }

    public void enterTempMode(Pokemon @NotNull [] pokemons) {
        if (playerParty.inTemporaryMode()) {
            playerParty.setInTemporaryMode(false, Color.blue);
        }
        playerParty.enterTemporaryMode(Color.BLUE);
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
