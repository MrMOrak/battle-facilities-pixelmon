package de.monver.bfacilities.events;

import com.pixelmonmod.pixelmon.api.events.BeatTrainerEvent;
import com.pixelmonmod.pixelmon.api.events.DropEvent;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.UUID;

public class BFacilitiesWinEvent {

    @SubscribeEvent
    public void onNPCBattleEnd(BeatTrainerEvent event) {
        ServerPlayerEntity player = (ServerPlayerEntity) event.player.getEntity();
        UUID playerId = player.getUniqueID();
        if (StorageProxy.getParty(playerId).inTemporaryMode()) {
            int coins = player.getPersistentData().getInt("WaheyCoins");
            player.getPersistentData().putInt("WaheyCoins", coins + 2);
        }
    }

    @SubscribeEvent
    public void onLootEvent(DropEvent event) {
        if (StorageProxy.getParty(event.player.getUniqueID()).inTemporaryMode()) {
            event.setCanceled(true);
        }
    }
}
