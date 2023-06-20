package de.monver.bfacilities.utils;

public enum Generations {
    I(151, 0, 151),
    II(251, 151, 100),
    III(386, 251, 135),
    IV(493, 386, 107),
    V(649, 493, 156),
    VI(721, 649, 72),
    VII(809, 721, 88),
    VIII(905, 809, 96),
    IX(1010, 905, 105),
    ALL(905, 0, 904);

    final int generation;
    final int prevGen;
    final int tillNextGen;


    Generations(int generation, int prevGen, int tillNextGen) {
        this.generation = generation;
        this.prevGen = prevGen;
        this.tillNextGen = tillNextGen;
    }

    public int getPrevGen() {
        return prevGen;
    }

    public int getTillNextGen() {
        return tillNextGen;
    }

    public int getGeneration() {
        return generation;
    }
}
