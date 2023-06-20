package de.monver.bfacilities.tempParty;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.species.Stats;
import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import de.monver.bfacilities.config.BFacilitiesConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FormHelper {

    final static Logger LOGGER = LoggerFactory.getLogger(FormHelper.class);

    public static Pokemon checkForRegionalForm(Pokemon pokemon) {

        if (BFacilitiesConfig.REGIONAL.get()) {
            List<Stats> regionalForms = pokemon.getSpecies().getForms().stream().filter(Stats::isRegional).collect(Collectors.toList());
            regionalForms.add(pokemon.getSpecies().getDefaultForm());
            LOGGER.info("The forms for the pokemon are " + pokemon.getLocalizedName() + ":  " + regionalForms);
                pokemon.setForm(RandomHelper.getRandomElementFromCollection(regionalForms));
        }
        return pokemon;
    }

}
