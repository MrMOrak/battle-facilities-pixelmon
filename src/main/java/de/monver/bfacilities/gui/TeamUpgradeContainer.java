package de.monver.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import de.monver.bfacilities.gui.customslots.PlaceHolderSlot;
import de.monver.bfacilities.gui.customslots.RerollSlot;
import de.monver.bfacilities.gui.customslots.SelectSlot;
import de.monver.bfacilities.gui.customslots.TeamSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class TeamUpgradeContainer extends Container implements INamedContainerProvider {


    final static Logger LOGGER = LoggerFactory.getLogger(TeamUpgradeContainer.class);
    PlayerEntity player;

    List<Pokemon> playerTeam;
    ItemStack selectButton = new ItemStack((Items.GREEN_STAINED_GLASS_PANE)).setDisplayName(new StringTextComponent("Change Stats"));
    ItemStack evoButon = new ItemStack((Items.RED_STAINED_GLASS_PANE)).setDisplayName(new StringTextComponent("Force Evolution"));

    public TeamUpgradeContainer(int windowId, PlayerEntity player) {
        super(ContainerType.GENERIC_9X6, windowId);
        this.player = player;
        int ROWS = 6;
        int COLUMNS = 9;
        Inventory inventory = new Inventory(ROWS * COLUMNS);

        int slotIndex = 0;
        this.playerTeam = StorageProxy.getParty(player.getUniqueID()).getTeam();

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                {
                    if (j == 0) {
                        this.addSlot(new TeamSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        ItemStack photo = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromNationalDex(playerTeam.get(i).getSpecies().getDex())));
                        inventory.setInventorySlotContents(slotIndex, photo.setDisplayName(playerTeam.get(i).getTranslatedName()));
                    }
                    if (j > 0 && j < 7) {
                        this.addSlot(new PlaceHolderSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, new ItemStack(Items.GRAY_STAINED_GLASS_PANE));
                    }
                    if (j == 7) {
                        this.addSlot(new SelectSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, selectButton);
                    }
                    if (j == 8) {
                        this.addSlot(new RerollSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, evoButon);
                    }
                }
                slotIndex++;
            }
        }

        for (int row = 0; row < 3; ++row) {
            for (int column = 0; column < 9; ++column) {
                int x = 9 + column * 18;
                int y = 84 + row * 18;
                this.addSlot(new Slot(player.inventory, column + row * 9 + 9, x, y));
            }
        }
        // Add player hotbar slots
        for (int column = 0; column < 9; ++column) {
            int x = 9 + column * 18;
            int y = 142;
            this.addSlot(new Slot(player.inventory, column, x, y));
        }

    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, @NotNull ClickType clickType, @NotNull PlayerEntity player){

        if(this.getSlot(slotId)instanceof SelectSlot){
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
            StatContainer container = new StatContainer(1611, serverPlayer, this.playerTeam.get(slotId / 9));
            serverPlayer.closeContainer();
            LOGGER.debug("Created new container with title {}", container.getDisplayName());
            player.openContainer(container);
            return ItemStack.EMPTY;
        }


        return super.slotClick(slotId, dragType, clickType, player);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Upgrade your Team:");
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new TeamUpgradeContainer(p_createMenu_1_, player);
    }
}
