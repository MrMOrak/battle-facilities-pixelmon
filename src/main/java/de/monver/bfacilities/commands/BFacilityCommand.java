package de.monver.bfacilities.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.pixelmonmod.pixelmon.comm.ChatHandler;
import de.monver.bfacilities.config.BFacilitiesConfig;
import de.monver.bfacilities.gui.TeamSelectionContainer;
import de.monver.bfacilities.tempParty.TempParty;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

public class BFacilityCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(
                Commands.literal("bfacility")
                        .then(Commands.literal("rental")
                                .executes(commandContext -> openRental(commandContext)))
                        .then(Commands.literal("return")
                                .executes(commandContext -> returnRental(commandContext)))
                        .then(Commands.literal("config")
                                .executes(commandContext -> displayConfig(commandContext)))
                        .executes(commandContext -> openRental(commandContext))
        );
    }

    static int openRental(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        Entity entity = commandContext.getSource().getEntity();
        if (entity instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            TeamSelectionContainer container = new TeamSelectionContainer(1601, player);
            player.openContainer(container);
            return 1;
        } else {
            Message errorMessageComponent = new StringTextComponent("Not a player!");
            throw new CommandSyntaxException(new SimpleCommandExceptionType(errorMessageComponent), errorMessageComponent);
        }
    }

    static int returnRental(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        Entity entity = commandContext.getSource().getEntity();
        if (entity instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            TempParty.leaveTempMode(player);
            StringTextComponent feedback = new StringTextComponent("Original Party is restored");
            commandContext.getSource().sendFeedback(feedback, true);
            return 1;
        } else {
            Message errorMessageComponent = new StringTextComponent("Not a player!");
            throw new CommandSyntaxException(new SimpleCommandExceptionType(errorMessageComponent), errorMessageComponent);
        }
    }

    static int displayConfig(CommandContext<CommandSource> commandContext) throws CommandSyntaxException {
        Entity entity = commandContext.getSource().getEntity();
        if (entity instanceof ServerPlayerEntity){
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            ChatHandler.sendChat(player, TextFormatting.GREEN + "Current Config Settings:");
            ChatHandler.sendChat(player, TextFormatting.GREEN + "Level: " + BFacilitiesConfig.LEVEL.get());
            if (BFacilitiesConfig.MULTIPLE_GEN.get()){
                ChatHandler.sendChat(player, TextFormatting.GREEN + "Generation: up until Gen " + BFacilitiesConfig.GENERATION.get());
            } else {
                ChatHandler.sendChat(player, TextFormatting.GREEN + "Generation: only Gen " + BFacilitiesConfig.GENERATION.get());
            }
            ChatHandler.sendChat(player, TextFormatting.GREEN + "Rerolls: " + BFacilitiesConfig.REROLLS.get());
            ChatHandler.sendChat(player, TextFormatting.GREEN + "Regional Forms: " + BFacilitiesConfig.REGIONAL.get());
            return 1;
        } else {
            Message errorMessageComponent = new StringTextComponent("Not a player!");
            throw new CommandSyntaxException(new SimpleCommandExceptionType(errorMessageComponent), errorMessageComponent);
        }
    }
}
