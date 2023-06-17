package de.monver.bfacilities.config;

import com.electronwill.nightconfig.core.Config;
import net.minecraftforge.common.ForgeConfigSpec;
import de.monver.bfacilities.utils.GenerationEnums;

public final class BFacilitiesConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.IntValue LEVEL;
    public static final ForgeConfigSpec.ConfigValue<GenerationEnums> GENERATION;
    public static final ForgeConfigSpec.BooleanValue REGIONAL;
    public static final ForgeConfigSpec.BooleanValue GIMMICKS;
    public static final ForgeConfigSpec.IntValue REROLLS;


    static {
        BUILDER.push("Team Generation Config");
        BUILDER.comment("Sets the level for the battle facilities teams");
        LEVEL = BUILDER.defineInRange("level", 50, 1, 100);

        BUILDER.comment("Sets the generation from which the pokemon are pulled");
        GENERATION = BUILDER.define("generation", GenerationEnums.ALL);

        BUILDER.comment("Sets the amount of rerolls for the individual teams");
        REROLLS = BUILDER.defineInRange("rerolls", 2, 0, 1000000);

        BUILDER.comment("Allow regional forms");
        REGIONAL = BUILDER.define("enable region forms", true);

        BUILDER.comment("Allow regional gimmicks (dynamax/megas :D)");
        GIMMICKS = BUILDER.define("enable gimmicks", true);

        BUILDER.pop();
        Config.setInsertionOrderPreserved(true);
        SPEC = BUILDER.build();
    }
}
