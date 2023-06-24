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

        BUILDER.pop();
        Config.setInsertionOrderPreserved(true);
        SPEC = BUILDER.build();
    }
}
