package de.monver.bfacilities.config;

import com.electronwill.nightconfig.core.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import de.monver.bfacilities.utils.Generations;

public final class BFacilitiesConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue LEVEL;
    public static final ForgeConfigSpec.IntValue TEAM_SIZE;
    public static final ForgeConfigSpec.EnumValue<Generations> GENERATION;
    public static final ForgeConfigSpec.BooleanValue MULTIPLE_GEN;
    public static final ForgeConfigSpec.BooleanValue REGIONAL;
    public static final ForgeConfigSpec.IntValue REROLLS;
    public static final ForgeConfigSpec.BooleanValue HELD_ITEMS;
    public static final ForgeConfigSpec.BooleanValue HELD_ITEMS_BIAS;
    public static final ForgeConfigSpec.DoubleValue HELD_ITEMS_BIAS_CHANCE;


    static {
        BUILDER.push("Team Generation Config");
        BUILDER.comment("Sets the level for the battle facilities teams");
        LEVEL = BUILDER.defineInRange("level", 50, 1, 100);

        BUILDER.comment("Sets the maximum team size");
        TEAM_SIZE = BUILDER.defineInRange("team size", 6, 1, 6);

        BUILDER.comment("Sets the generations from which the pokemon are pulled");
        GENERATION = BUILDER.defineEnum("generation", Generations.ALL);

        BUILDER.comment("Either create only the generation (false) or up until that generation(true)");
        MULTIPLE_GEN = BUILDER.define("multiple gen", true);

        BUILDER.comment("Sets the amount of rerolls for the individual teams");
        REROLLS = BUILDER.defineInRange("rerolls", 2, 0, 1000000);

        BUILDER.comment("Allow regional forms");
        REGIONAL = BUILDER.define("enable regional forms", true);

        BUILDER.comment("Allow random held items for generated pokemon");
        HELD_ITEMS = BUILDER.define("random held items", true);
        HELD_ITEMS_BIAS = BUILDER.define("biased item choice for special pokemon", true);
        HELD_ITEMS_BIAS_CHANCE = BUILDER.defineInRange("chance for biased held items", 0.33F, 0F, 1F);

        BUILDER.pop();
        Config.setInsertionOrderPreserved(true);
        SPEC = BUILDER.build();
    }
}
