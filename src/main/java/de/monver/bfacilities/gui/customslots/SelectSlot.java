package de.monver.bfacilities.gui.customslots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import org.jetbrains.annotations.NotNull;

public class SelectSlot extends Slot {
    public SelectSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean canTakeStack(@NotNull PlayerEntity player) {
        return false; // Prevents item extraction from the slot
    }
}
