package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.battles.attack.AttackRegistry;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.species.Species;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import com.pixelmonmod.pixelmon.battles.attacks.Attack;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;
import com.pixelmonmod.pixelmon.items.MemoryItem;
import de.monver.bfacilities.config.BFacilitiesConfig;
import net.minecraft.item.ItemStack;

import static com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper.getRandomChance;
import static com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper.getRandomElementFromArray;

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

    private static final ItemStack[] heldItemsDrives = {
            new ItemStack(PixelmonItems.burn_drive),
            new ItemStack(PixelmonItems.douse_drive),
            new ItemStack(PixelmonItems.chill_drive),
            new ItemStack(PixelmonItems.shock_drive)
    };

    private static final ItemStack[] heldItemsMemories = {
            new ItemStack(PixelmonItems.grass_memory),
            new ItemStack(PixelmonItems.fire_memory),
            new ItemStack(PixelmonItems.water_memory),
            new ItemStack(PixelmonItems.flying_memory),
            new ItemStack(PixelmonItems.bug_memory),
            new ItemStack(PixelmonItems.poison_memory),
            new ItemStack(PixelmonItems.electric_memory),
            new ItemStack(PixelmonItems.psychic_memory),
            new ItemStack(PixelmonItems.rock_memory),
            new ItemStack(PixelmonItems.ground_memory),
            new ItemStack(PixelmonItems.dark_memory),
            new ItemStack(PixelmonItems.ghost_memory),
            new ItemStack(PixelmonItems.steel_memory),
            new ItemStack(PixelmonItems.fighting_memory),
            new ItemStack(PixelmonItems.ice_memory),
            new ItemStack(PixelmonItems.dragon_memory),
            new ItemStack(PixelmonItems.fairy_memory)
    };

    public static ItemStack generateHeldItem(Pokemon pokemon) {

        float chance = BFacilitiesConfig.HELD_ITEMS_BIAS_CHANCE.get().floatValue();
        Species species = pokemon.getSpecies();
        if (BFacilitiesConfig.HELD_ITEMS_BIAS.get()) {
            if (PixelmonSpecies.DIALGA.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.adamant_orb);
                }
            } else if (PixelmonSpecies.GENESECT.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    if (pokemon.getMoveset().hasAttack(AttackRegistry.TECHNO_BLAST)) {
                        return getRandomElementFromArray(heldItemsDrives);
                    }
                }
            } else if (PixelmonSpecies.SILVALLY.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return getRandomElementFromArray(heldItemsDrives);
                }
            } else if (PixelmonSpecies.GIRATINA.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.griseous_orb);
                }
            } else if (PixelmonSpecies.FARFETCHD.getValueUnsafe().equals(species) || PixelmonSpecies.SIRFETCHD.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.leek);
                }
            } else if (PixelmonSpecies.PIKACHU.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.light_ball);
                }
            } else if (PixelmonSpecies.PALKIA.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.lustrous_orb);
                }
            } else if (PixelmonSpecies.ZAMAZENTA.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.rusted_shield);
                }
            } else if (PixelmonSpecies.ZACIAN.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.rusted_sword);
                }
            } else if (PixelmonSpecies.CUBONE.getValueUnsafe().equals(species) || PixelmonSpecies.MAROWAK.getValueUnsafe().equals(species)) {
                if (getRandomChance(chance)) {
                    return new ItemStack(PixelmonItems.thick_club);
                }
            }
        }
        return RandomHelper.getRandomElementFromArray(heldItems);
    }
}
