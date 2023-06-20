package de.monver.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import de.monver.bfacilities.utils.GenerationHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;

public class RerollHelper {
    static ItemStack rerollButton = new ItemStack((Items.RED_STAINED_GLASS_PANE)).setDisplayName(new StringTextComponent("Reroll"));

    public static ItemStack reroller(int row, int[] rerollsContainer, PlayerEntity player, Inventory inventory) {
        if (rerollsContainer[row] == 0) {
            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
            return new ItemStack((Items.GRAY_STAINED_GLASS_PANE));
        }

        rerollsContainer[row]--;

        for (int i = 0; i < 6; ++i) {
            int rand = GenerationHelper.getDexNumber();
            ItemStack photo = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromNationalDex(rand)));
            inventory.setInventorySlotContents((row + 1) * 9 + i, photo.setDisplayName(PixelmonSpecies.fromNationalDex(rand).getTranslatedName()));
        }
        if(rerollsContainer[row] == 0){
            return new ItemStack((Items.GRAY_STAINED_GLASS_PANE));
        }
        rerollButton.setCount(rerollsContainer[row]);
        return rerollButton;
    }
}
