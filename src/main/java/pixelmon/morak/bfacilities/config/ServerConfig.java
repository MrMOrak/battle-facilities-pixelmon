package pixelmon.morak.bfacilities.config;

import net.minecraftforge.common.ForgeConfigSpec;
import pixelmon.morak.bfacilities.utils.GenerationEnums;

final class ServerConfig {
    final ForgeConfigSpec.IntValue LEVEL;
    final ForgeConfigSpec.ConfigValue<GenerationEnums> GENERATION;

    ServerConfig(final ForgeConfigSpec.Builder BUILDER){
        BUILDER.push("teamGeneration");
        LEVEL = BUILDER
                .comment("Sets the level for the battle facility teams")
                .defineInRange("level", 50, 1, 100);
        GENERATION = BUILDER
                .comment("Sets the generation from which the pokemon are pulled")
                .comment("Choose between: ALL, I, II , III, IV, V, VI, VII, VIII, IX")
                .defineEnum("generation", GenerationEnums.ALL);
        BUILDER.pop();
    }

}
