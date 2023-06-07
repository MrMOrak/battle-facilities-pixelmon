package pixelmon.morak.bfacilities;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class Utils {


    public Pokemon[] parseItemstoTeam(ItemStack[] itemStacks){
        Pokemon[] teamArray = new Pokemon[6];
        for(int i = 0; i<itemStacks.length; i++){
            if(!itemStacks[i].getItem().getRegistryName().getPath().equals("pixelmon_sprite")){
                throw new IllegalArgumentException("The chosen item is not a photo!");
            }
            CompoundNBT dexTag = itemStacks[i].getTag();
            int dexNumber = dexTag.getInt("ndex");
            teamArray[i] = PokemonFactory.create(PixelmonSpecies.fromDex(dexNumber).get());
        }
        return teamArray;
    }
}
