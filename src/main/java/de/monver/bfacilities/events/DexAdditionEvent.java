package de.monver.bfacilities.events;

import com.pixelmonmod.pixelmon.api.events.PokedexEvent;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraftforge.eventbus.api.SubscribeEvent;


public class DexAdditionEvent {

    @SubscribeEvent
    public void onPokedexEvent(PokedexEvent.Pre event) {
        if (StorageProxy.getParty(event.getPlayer()).inTemporaryMode()) {
            event.setCanceled(true);
        }
    }
}
