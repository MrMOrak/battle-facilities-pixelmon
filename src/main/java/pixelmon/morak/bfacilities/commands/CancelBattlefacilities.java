package pixelmon.morak.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import pixelmon.morak.bfacilities.TempParty;

public class CancelBattlefacilities {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(
                Commands.literal("cancelBF")
                        .executes(context -> execute(context.getSource())));
    }

    private static int execute(CommandSource source){
        TempParty.leaveTempMode();
        StringTextComponent feedback = new StringTextComponent("Original Party is restored");
        source.sendFeedback(feedback, true);
        return 1;
    }
}
