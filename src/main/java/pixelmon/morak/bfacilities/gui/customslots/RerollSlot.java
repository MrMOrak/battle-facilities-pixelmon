package pixelmon.morak.bfacilities.gui.customslots;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import org.jetbrains.annotations.NotNull;

public class RerollSlot extends Slot {
    public RerollSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }
    @Override
    public boolean canTakeStack(@NotNull PlayerEntity player) {
        return false; // Prevents item extraction from the slot
    }
}
