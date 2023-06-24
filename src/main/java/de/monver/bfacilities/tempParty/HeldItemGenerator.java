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
            new ItemStack(PixelmonItems.choice_scarf),
            new ItemStack(PixelmonItems.focus_sash),
            new ItemStack(PixelmonItems.life_orb),
            new ItemStack(PixelmonItems.assault_vest),
            new ItemStack(PixelmonItems.rocky_helmet),
            new ItemStack(PixelmonItems.lum_berry),
            new ItemStack(PixelmonItems.sitrus_berry),
            new ItemStack(PixelmonItems.black_sludge),
            new ItemStack(PixelmonItems.air_balloon),
            new ItemStack(PixelmonItems.expert_belt),
            new ItemStack(PixelmonItems.eviolite),
            new ItemStack(PixelmonItems.quick_claw),
            new ItemStack(PixelmonItems.safety_goggles),
            new ItemStack(PixelmonItems.mental_herb),
            new ItemStack(PixelmonItems.white_herb),
            new ItemStack(PixelmonItems.blunder_policy),
            new ItemStack(PixelmonItems.bright_powder),
            new ItemStack(PixelmonItems.shell_bell),
            new ItemStack(PixelmonItems.weakness_policy)
    };

    public static ItemStack generateHeldItem(Pokemon pokemon) {

        return RandomHelper.getRandomElementFromArray(heldItems);
    }
}
