package pixelmon.morak.bfacilities.gui;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class TeamSlot extends Slot{
    public TeamSlot(IInventory inventory, int slotIndex, int xPosition, int yPosition) {
        super(inventory, slotIndex, xPosition, yPosition);
    }

    // Override canTakeItems method to prevent item extraction
    @Override
    public boolean canTakeStack(PlayerEntity player) {
        return false; // Prevents item extraction from the slot
    }

    @Override
    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack){
        //TempParty tempParty = new TempParty();

        return null;
    }
}