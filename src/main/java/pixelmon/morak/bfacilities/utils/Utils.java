package pixelmon.morak.bfacilities.utils;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.fml.config.ModConfig;
import pixelmon.morak.bfacilities.config.BFacilitiesConfig;

import java.util.Random;

public class Utils {

    private static final Random RANDOM = new Random();


    public Pokemon[] parseItemsToTeam(ItemStack[] itemStacks) {
        Pokemon[] teamArray = new Pokemon[6];
        for (int i = 0; i < itemStacks.length; i++) {
            CompoundNBT dexTag = itemStacks[i].getTag();
            int dexNumber = dexTag.getInt("ndex");
            if (itemStacks[i].getItem() == Items.AIR) {
                teamArray[i] = null;
            } else {
                Pokemon photMon = PokemonFactory.create(PixelmonSpecies.fromDex(dexNumber).get());
                final int level = BFacilitiesConfig.level;
                photMon.setLevel(level);
                if (RANDOM.nextInt(4097) > 4095) {
                    photMon.setShiny(true);
                }
                teamArray[i] = photMon;
            }
        }
        return teamArray;
    }
}
