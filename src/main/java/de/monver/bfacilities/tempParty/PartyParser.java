package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.InitializeCategory;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import de.monver.bfacilities.config.BFacilitiesConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import org.jetbrains.annotations.NotNull;

import static com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper.getRandomChance;

public class PartyParser {

    public static Pokemon @NotNull [] parseItemsToTeam(ItemStack[] itemStacks, PlayerEntity player) {
        Pokemon[] teamArray = new Pokemon[6];
        final int level = BFacilitiesConfig.LEVEL.get();
        for (int i = 0; i < itemStacks.length; i++) {
            CompoundNBT dexTag = itemStacks[i].getTag();
            int dexNumber = dexTag.getInt("ndex");
            if (itemStacks[i].getItem() == Items.AIR) {
                teamArray[i] = null;
            } else {
                Pokemon photMon = PokemonFactory.create(PixelmonSpecies.fromNationalDex(dexNumber));
                photMon.initialize(InitializeCategory.INTRINSIC);
                photMon.setOriginalTrainer(player);
                photMon.setExperience(0);
                photMon.setLevel(level);
                photMon.setDoesLevel(false);
                MoveGenerator.generateMoveSetForPokemon(photMon);
                FormHelper.checkForRegionalForm(photMon);
                if (getRandomChance(0.01F)) {
                    photMon.setShiny(true);
                }
                if (getRandomChance(0.2F) && photMon.getForm().getAbilities().getHiddenAbilities().length != 0){
                    photMon.setAbility(photMon.getForm().getAbilities().getHiddenAbilities()[0]);
                } else {
                    photMon.setAbility(photMon.getForm().getAbilities().getRandomAbility());
                }
                photMon.setHeldItem(HeldItemGenerator.generateHeldItem(photMon));
                teamArray[i] = photMon;
            }
        }
        return teamArray;
    }
}
