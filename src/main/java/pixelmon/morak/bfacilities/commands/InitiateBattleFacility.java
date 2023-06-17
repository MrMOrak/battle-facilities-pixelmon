package pixelmon.morak.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import pixelmon.morak.bfacilities.gui.TeamSelectionContainer;

public class InitiateBattleFacility {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("initiateBattleFacility")
                        .executes(context -> execute(context))
        );
    }

    private static int execute(CommandContext<CommandSource> context) throws CommandSyntaxException {
        Entity entity = context.getSource().getEntity();
        if(entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            TeamSelectionContainer container = new TeamSelectionContainer(1601, player);
            player.openContainer(container);
            return 1;
        } else {
            Message errorMessageComponent = new StringTextComponent("Not a player!");
            throw new CommandSyntaxException(new SimpleCommandExceptionType(errorMessageComponent), errorMessageComponent);
        }
    }
}
