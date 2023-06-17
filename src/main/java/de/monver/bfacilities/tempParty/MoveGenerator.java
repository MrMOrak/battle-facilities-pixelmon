package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.battles.AttackCategory;
import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.Moveset;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;

import java.util.*;

public class MoveGenerator {

    public static Pokemon generateMoveSetForPokemon(Pokemon pokemon) {
        boolean type = checkPhySpc(pokemon);
        Moveset moveSet = pokemon.getMoveset();
        List<ImmutableAttack> allMoves = pokemon.getForm().getMoves().getAllMoves();
        List<AttackCategory> moveCats = new ArrayList<>();
        if (allMoves.size() <= 4) {
            for (int i = 0; i < allMoves.size(); i++) {
                moveSet.set(i, allMoves.get(i).ofMutable());
            }
        } else {
            Collections.shuffle(allMoves);
            for (int i = 0; i < 4; i++) {
                moveCats.add(i, moveSet.get(i).getAttackCategory());
                moveSet.set(i, allMoves.get(i).ofMutable());
            }
        /*
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
        This code block is retired till needed
         */
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
