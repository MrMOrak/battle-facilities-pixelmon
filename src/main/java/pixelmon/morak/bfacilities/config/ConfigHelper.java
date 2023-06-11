package pixelmon.morak.bfacilities.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper{
        // We store a reference to the ModConfigs here to be able to change the values in them from our code
        // (For example from a config GUI)
        private static ModConfig serverConfig;

        public static void bakeServer(final ModConfig config) {
            serverConfig = config;

            BFacilitiesConfig.generation = ConfigHolder.SERVER.GENERATION.get();
            BFacilitiesConfig.level = ConfigHolder.SERVER.LEVEL.get();

        }

        private static void setValueAndSave(final net.minecraftforge.fml.config.ModConfig modConfig, final String path, final Object newValue) {
            modConfig.getConfigData().set(path, newValue);
            modConfig.save();
        }


}
