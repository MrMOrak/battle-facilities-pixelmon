package pixelmon.morak.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import pixelmon.morak.bfacilities.gui.TeamSelectionContainer;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class InitiateBattleFacility {

    private static final AtomicInteger counter = new AtomicInteger();

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(
                Commands.literal("initiateBattleFacility")
                        .executes(context -> execute(context))
        );
    }

    private static int execute(CommandContext<CommandSource> context) throws CommandException {
        PlayerEntity player = Objects.requireNonNull(context.getSource().getEntity()).world.getPlayerByUuid(context.getSource().getEntity().getUniqueID());
        assert player != null;
        player.openContainer(new TeamSelectionContainer(counter.incrementAndGet(), player));
        return 1;
    }
}
