package de.monver.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.StringTextComponent;

import java.util.Random;

public class Utils {
    static Random random = new Random();

    public static ItemStack reroller(int row, int[] rerollsContainer, PlayerEntity player, Inventory inventory) {
        if (rerollsContainer[row] == 0) {
            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
            return ItemStack.EMPTY;
        }

        rerollsContainer[row]--;

        for (int i = 0; i < 6; ++i) {
            int rand = random.nextInt(904) + 1;
            ItemStack photo = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromNationalDex(rand)));
            inventory.setInventorySlotContents((row + 1) * 9 + i, photo.setDisplayName(PixelmonSpecies.fromNationalDex(rand).getTranslatedName()));
        }
        return ItemStack.EMPTY;
    }
}
