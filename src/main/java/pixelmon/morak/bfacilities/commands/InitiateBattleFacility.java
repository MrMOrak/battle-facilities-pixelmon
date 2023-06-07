package pixelmon.morak.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import pixelmon.morak.bfacilities.gui.TeamSelectionContainer;

public class InitiateBattleFacility {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(
                Commands.literal("initiateBattleFacility")
                        .executes(context -> execute(context.getSource()))
                );
    }

    private static int execute(CommandSource source) throws CommandException, CommandSyntaxException {
        if (source.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) source.getEntity();
            player.openContainer(new TeamSelectionContainer(1, player));
        }
        return 1;
    }
}
