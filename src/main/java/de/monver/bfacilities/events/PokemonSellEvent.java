package de.monver.bfacilities.events;

import com.envyful.gts.forge.event.TradeCreateEvent;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class PokemonSellEvent {

    @SubscribeEvent
    public void onPokemonSell(TradeCreateEvent event){
        if (StorageProxy.getParty(event.getPlayer().getParent()).inTemporaryMode()){
            event.getTrade().adminRemove(event.getPlayer());
            event.setCanceled(true);
        }
    }
}
