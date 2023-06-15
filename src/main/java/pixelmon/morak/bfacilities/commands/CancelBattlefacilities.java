package pixelmon.morak.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import pixelmon.morak.bfacilities.tempParty.TempParty;

import java.util.Objects;

public class CancelBattlefacilities {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(
                Commands.literal("cancelBF")
                        .executes(context -> execute(context)));
    }

    private static int execute(CommandContext<CommandSource> context){
        PlayerEntity player = Objects.requireNonNull(context.getSource().getEntity()).world.getPlayerByUuid(context.getSource().getEntity().getUniqueID());
        assert player != null;
        TempParty.leaveTempMode(player);
        StringTextComponent feedback = new StringTextComponent("Original Party is restored");
        context.getSource().sendFeedback(feedback, true);
        return 1;
    }
}
