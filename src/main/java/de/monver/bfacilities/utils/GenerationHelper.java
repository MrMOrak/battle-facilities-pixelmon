package de.monver.bfacilities.utils;

import com.pixelmonmod.pixelmon.api.util.helpers.RandomHelper;
import de.monver.bfacilities.config.BFacilitiesConfig;

public class GenerationHelper {

    public static int getDexNumber() {

        Generations generations = BFacilitiesConfig.GENERATION.get();

        if (BFacilitiesConfig.MULTIPLE_GEN.get()) {

            return RandomHelper.getRandomNumberBetween(1, generations.getGeneration());
        }

        int lowerBound = generations.getPrevGen() + 1;
        int upperBound = generations.getGeneration();

        return RandomHelper.getRandomNumberBetween(lowerBound, upperBound);
    }

}
