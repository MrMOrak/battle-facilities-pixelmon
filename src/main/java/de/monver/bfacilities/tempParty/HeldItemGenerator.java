package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import net.minecraft.item.ItemStack;

public class HeldItemGenerator {

    private static final ItemStack[] heldItems = {
            new ItemStack(PixelmonItems.leftovers),
            new ItemStack(PixelmonItems.choice_band),
            new ItemStack(PixelmonItems.choice_specs),
            new ItemStack(PixelmonItems.focus_sash),
            new ItemStack(PixelmonItems.life_orb),
            new ItemStack(PixelmonItems.assault_vest),
            new ItemStack(PixelmonItems.rocky_helmet),
            new ItemStack(PixelmonItems.lum_berry),
            new ItemStack(PixelmonItems.black_sludge),
            new ItemStack(PixelmonItems.air_balloon),
            new ItemStack(PixelmonItems.expert_belt),
            new ItemStack(PixelmonItems.eviolite),
            new ItemStack(PixelmonItems.quick_claw),
            new ItemStack(PixelmonItems.safety_goggles)
    };

    public static ItemStack generateHeldItem(Pokemon pokemon) {

        return RandomHelper.getRandomElementFromArray(heldItems);
    }
}
