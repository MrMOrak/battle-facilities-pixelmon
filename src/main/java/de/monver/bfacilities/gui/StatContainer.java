package de.monver.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.registries.PixelmonItems;
import com.pixelmonmod.pixelmon.items.IncreaseEVItem;
import de.monver.bfacilities.gui.customslots.PlaceHolderSlot;
import de.monver.bfacilities.gui.customslots.RerollSlot;
import de.monver.bfacilities.gui.customslots.SelectSlot;
import de.monver.bfacilities.utils.StatsHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

public class StatContainer extends Container implements INamedContainerProvider {


    final static Logger LOGGER = LoggerFactory.getLogger(StatContainer.class);

    ServerPlayerEntity player;

    private final Inventory inventory;

    Pokemon selectedMon;

    public StatContainer(int windowId, PlayerEntity player, Pokemon selectedMon) {
        super(ContainerType.GENERIC_9X1, windowId);
        this.player = (ServerPlayerEntity) player;
        int COLUMNS = 9;
        this.selectedMon = selectedMon;
        this.inventory = new Inventory(COLUMNS);
        for (int i = 0; i < COLUMNS; i++) {
            IncreaseEVItem[] upgradeItems = {
                    PixelmonItems.hp_up,
                    PixelmonItems.protein,
                    PixelmonItems.iron,
                    PixelmonItems.calcium,
                    PixelmonItems.zinc,
                    PixelmonItems.carbos
            };
            if (i < upgradeItems.length) {
                this.addSlot(new PlaceHolderSlot(inventory, i, 8, 18 + i * 18));
                inventory.setInventorySlotContents(i, new ItemStack(upgradeItems[i]));
            }
            if (i >= upgradeItems.length && i < 8) {
                this.addSlot(new RerollSlot(inventory, i, 8, 18 + i * 18));
                ItemStack placeholder = Items.BLUE_STAINED_GLASS_PANE.getDefaultInstance();
                inventory.setInventorySlotContents(i, placeholder);
            }
            if (i == 8) {
                this.addSlot(new SelectSlot(inventory, i, 8, 18 + i * 18));
                ItemStack selectButton = Items.RED_STAINED_GLASS_PANE.getDefaultInstance();
                selectButton.setDisplayName(new StringTextComponent("Return to previous window"));
                inventory.setInventorySlotContents(i, selectButton);
            }
            LOGGER.debug(MessageFormat.format("Slot at {0} is {1}", i, this.getSlot(i).getClass().getName()));
        }

        // Main Player Inventory
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(player.inventory, col + row * 9 + 9, 8 + col * 18, 166 - (4 - row) * 18 - 10));
            }
        }

        // Player Hotbar
        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(player.inventory, col, 8 + col * 18, 142));
        }
    }


    @Override
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new StringTextComponent(TextFormatting.BLUE + "Choose stat to raise:");
    }

    @Override
    public Container createMenu(int p_createMenu_1_, @NotNull PlayerInventory p_createMenu_2_, @NotNull PlayerEntity p_createMenu_3_) {
        return new StatContainer(p_createMenu_1_, p_createMenu_3_, this.selectedMon);
    }

    @Override
    public @NotNull ItemStack slotClick(int slotId, int dragType, @NotNull ClickType clickType, @NotNull PlayerEntity player) {

        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;

        if (getSlot(slotId) instanceof SelectSlot) {
            TeamUpgradeContainer container = new TeamUpgradeContainer(1601, serverPlayer);
            serverPlayer.closeContainer();
            serverPlayer.openContainer(container);
            return ItemStack.EMPTY;
        }

        if (getSlot(slotId) instanceof PlaceHolderSlot) {
            StatsHelper.raiseByVitaminSelect(this.selectedMon, inventory.getStackInSlot(slotId));
            TeamUpgradeContainer container = new TeamUpgradeContainer(1601, serverPlayer);
            serverPlayer.closeContainer();
            serverPlayer.openContainer(container);
            return ItemStack.EMPTY;
        }


        return super.slotClick(slotId, dragType, clickType, player);
    }

}
