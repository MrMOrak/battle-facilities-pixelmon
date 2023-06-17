package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.stats.Moveset;
import com.pixelmonmod.pixelmon.battles.attacks.ImmutableAttack;

import java.util.*;

public class MoveGenerator {

    public static Pokemon generateMoveSetForPokemon(Pokemon pokemon) {
        Moveset moveSet = pokemon.getMoveset();
        List<ImmutableAttack> allMoves = pokemon.getForm().getMoves().getAllMoves();
        if (allMoves.size() <= 4) {
            for (int i = 0; i < allMoves.size(); i++) {
                moveSet.set(i, allMoves.get(i).ofMutable());
            }
        } else {
            Collections.shuffle(allMoves);
            for (int i = 0; i < 4; i++) {
                moveSet.set(i, allMoves.get(i).ofMutable());
            }
        }
        return pokemon;
    }

}
