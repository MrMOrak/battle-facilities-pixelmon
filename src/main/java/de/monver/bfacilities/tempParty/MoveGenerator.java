package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.battles.AttackCategory;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.Moveset;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {

    public static Pokemon generateMoveSetForPokemon(Pokemon pokemon) {
        boolean type = checkPhySpc(pokemon);
        Moveset moveset = pokemon.getMoveset();
        List<AttackCategory> moveCats = new ArrayList<>();
        for (int i = 0; i < moveset.size(); i++) {
            moveCats.add(i, moveset.get(i).getAttackCategory());
        }
        if (type) {
            while (!moveCats.contains(AttackCategory.PHYSICAL)) {
                pokemon.rerollMoveset();
                moveset = pokemon.getMoveset();
                for (int i = 0; i < moveset.size(); i++) {
                    moveCats.add(i, moveset.get(i).getAttackCategory());
                }
            }
        } else {
            while (!moveCats.contains(AttackCategory.SPECIAL)) {
                pokemon.rerollMoveset();
                moveset = pokemon.getMoveset();
                for (int i = 0; i < moveset.size(); i++) {
                    moveCats.add(i, moveset.get(i).getAttackCategory());
                }
            }
        }
        return pokemon;
    }

    //true=physical
    private static boolean checkPhySpc(Pokemon pokemon) {
        int spa = pokemon.getStats().getSpecialAttack();
        int atk = pokemon.getStats().getAttack();
        return spa <= atk;
    }

}
