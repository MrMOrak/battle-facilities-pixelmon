package de.monver.bfacilities.utils;

import com.pixelmonmod.pixelmon.api.pokemon.Nature;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.ability.Ability;
import com.pixelmonmod.pixelmon.api.pokemon.stats.BattleStatsType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

public class StatsHelper {

    public static String getAbility(Pokemon pokemon) {
        Ability ability = pokemon.getAbility();
        if (pokemon.hasHiddenAbility()) {
            return "Ability: " + ability.getName() + " (HA)";
        }
        return "Ability: " + ability.getName();
    }

    public static String getNature(Pokemon pokemon) {
        Nature nature = pokemon.getNature();
        String incStat = getStatName(nature.getIncreasedStat());
        String decStat = getStatName(nature.getDecreasedStat());
        if (incStat.isEmpty()) {
            return "Nature: " + pokemon.getNature().getString();
        }
        return "Nature: " + pokemon.getNature().getString() + TextFormatting.RED + " (+" + incStat + ") " + TextFormatting.BLUE + " (-" + decStat + ")";
    }

    public static String getIvs(Pokemon pokemon) {
        int[] ivs = pokemon.getIVs().getArray();
        return "Ivs: " + ivs[0] + " / " + ivs[1] + " / " + ivs[2] + " / " + ivs[3] + " / " + ivs[4] + " / " + ivs[5];
    }

    public static String getEvs(Pokemon pokemon) {
        int[] evs = pokemon.getEVs().getArray();
        return "Evs: " + evs[0] + " / " + evs[1] + " / " + evs[2] + " / " + evs[3] + " / " + evs[4] + " / " + evs[5];
    }

    public static Pokemon raiseByVitaminSelect(Pokemon pokemon, ItemStack vitamin) {
        String ivName = vitamin.getItem().getDefaultInstance().getDisplayName().getString();
        BattleStatsType statToUpgrad = BattleStatsType.HP;
        switch (ivName) {
            case ("Carbos"):
                statToUpgrad = BattleStatsType.SPEED;
                break;
            case ("Calcium"):
                statToUpgrad = BattleStatsType.SPECIAL_ATTACK;
                break;
            case ("Protein"):
                statToUpgrad = BattleStatsType.ATTACK;
                break;
            case ("Zinc"):
                statToUpgrad = BattleStatsType.SPECIAL_DEFENSE;
                break;
            case ("Iron"):
                statToUpgrad = BattleStatsType.DEFENSE;
                break;
        }
        pokemon.getEVs().addEVsOfType(statToUpgrad, 10, 252);
        return pokemon;
    }


    public static String getStatName(BattleStatsType stat) {
        switch (stat) {
            case HP:
                return "HP";
            case ATTACK:
                return "Atk";
            case DEFENSE:
                return "Def";
            case SPECIAL_ATTACK:
                return "SpAtk";
            case SPECIAL_DEFENSE:
                return "SpDef";
            case SPEED:
                return "Spd";
        }
        return "";
    }
}
