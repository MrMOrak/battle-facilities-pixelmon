package pixelmon.morak.bfacilities.gui.customslots;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import org.jetbrains.annotations.NotNull;

public class TeamSlot extends Slot{
    public TeamSlot(IInventory inventory, int slotIndex, int xPosition, int yPosition) {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    // Override canTakeItems method to prevent item extraction
    @Override
    public boolean canTakeStack(@NotNull PlayerEntity player) {
        return false; // Prevents item extraction from the slot
    }

}