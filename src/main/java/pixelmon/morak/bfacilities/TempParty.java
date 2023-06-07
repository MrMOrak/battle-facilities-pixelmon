package pixelmon.morak.bfacilities;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.PartyStorage;
import net.minecraft.entity.player.PlayerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class TempParty {

    final static Logger LOGGER = LoggerFactory.getLogger(TempParty.class);

    PlayerEntity player;
    Pokemon[] pokemons;

   private static PartyStorage trainerParty;

    public TempParty(Pokemon[] pokemons, PlayerEntity player){
        this.player = player;
        this.pokemons = pokemons;

        PartyStorage trainerParty = new PartyStorage(player.getUniqueID());
        this.trainerParty = trainerParty;
        trainerParty.setInTemporaryMode(true, Color.RED);
        LOGGER.info("Temp-Mode activated");
        for (int i = 0; i < pokemons.length; i++){
            LOGGER.info("Added mon" + i+1);
            trainerParty.add(pokemons[i]);
        }
    }

    public static void leaveTempMode(){
        trainerParty.setInTemporaryModeClient(false, trainerParty.getTempPartyColor());
    }
}
