package de.monver.bfacilities.events;

import com.pixelmonmod.pixelmon.api.events.HeldItemChangedEvent;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HeldItemGlueEvent {

    @SubscribeEvent
    public void onHeldItemChange(HeldItemChangedEvent event){
        if (event.player != null) {
            if (StorageProxy.getParty(event.player).inTemporaryMode() && event.newHeldItem.getItem() == Items.AIR) {
                event.setCanceled(true);
                ItemStack thisItem = event.player.inventory.getItemStack();
                event.player.inventory.setItemStack(ItemStack.EMPTY);
                event.pokemon.setHeldItem(thisItem);
            }
        }
    }
}
