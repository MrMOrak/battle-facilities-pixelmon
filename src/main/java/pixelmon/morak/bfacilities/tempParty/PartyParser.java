package pixelmon.morak.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import org.jetbrains.annotations.NotNull;
import pixelmon.morak.bfacilities.config.BFacilitiesConfig;

import java.util.Random;

public class PartyParser {

    private static final Random RANDOM = new Random();

    public static Pokemon @NotNull [] parseItemsToTeam(ItemStack[] itemStacks, PlayerEntity player) {
        Pokemon[] teamArray = new Pokemon[6];
        for (int i = 0; i < itemStacks.length; i++) {
            CompoundNBT dexTag = itemStacks[i].getTag();
            int dexNumber = dexTag.getInt("ndex");
            if (itemStacks[i].getItem() == Items.AIR) {
                teamArray[i] = null;
            } else {
                Pokemon photMon = PokemonFactory.create(PixelmonSpecies.fromDex(dexNumber).get());
                final int level = BFacilitiesConfig.LEVEL.get();
                photMon.setOriginalTrainer(player);
                photMon.setExperience(0);
                photMon.setLevel(level);
                photMon.setDoesLevel(false);
                MoveGenerator.generateMoveSetForPokemon(photMon);
                if (RANDOM.nextInt(4097) > 4095) {
                    photMon.setShiny(true);
                }
                teamArray[i] = photMon;
            }
        }
        return teamArray;
    }
}
