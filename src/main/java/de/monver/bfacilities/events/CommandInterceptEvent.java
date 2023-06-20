package de.monver.bfacilities.events;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.CommandEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandInterceptEvent {

    final static Logger LOGGER = LoggerFactory.getLogger(CommandInterceptEvent.class);

    @SubscribeEvent
    public void onCommandExecute(CommandEvent event) throws CommandSyntaxException {
        if (StorageProxy.getParty(event.getParseResults().getContext().getSource().asPlayer()).inTemporaryMode())
            if (event.getParseResults().getReader().getRead().contains("/sts") || event.getParseResults().getReader().getRead().contains("/simplests") || event.getParseResults().getReader().getRead().contains("/ssts") || event.getParseResults().getReader().getRead().contains("/selltoserver")){
                LOGGER.info("Cancel command " + event.getParseResults().getReader().getRead());
                event.getParseResults().getContext().getSource().sendFeedback(new StringTextComponent("Cant use that command while in temp-mode"), true);
                event.setCanceled(true);
            }
    }
}
