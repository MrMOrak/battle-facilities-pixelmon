package de.monver.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import de.monver.bfacilities.config.BFacilitiesConfig;
import de.monver.bfacilities.gui.customslots.PlaceHolderSlot;
import de.monver.bfacilities.gui.customslots.RerollSlot;
import de.monver.bfacilities.gui.customslots.SelectSlot;
import de.monver.bfacilities.gui.customslots.TeamSlot;
import de.monver.bfacilities.tempParty.PartyParser;
import de.monver.bfacilities.tempParty.TempParty;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


public class TeamSelectionContainer extends Container implements INamedContainerProvider {


    final static Logger LOGGER = LoggerFactory.getLogger(TeamSelectionContainer.class);
    private boolean pickUped = false;

    private int rerolls1;
    private int rerolls2;
    private int rerolls3;
    private int rerolls4;

    Random random = new Random();

    PlayerEntity player;

    private final Inventory inventory;

    public TeamSelectionContainer(int windowId, PlayerEntity player) {
        super(ContainerType.GENERIC_9X6, windowId);
        this.player = player;
        int ROWS = 6;
        int COLUMNS = 9;
        this.inventory = new Inventory(ROWS * COLUMNS);

        this.rerolls1 = BFacilitiesConfig.REROLLS.get();
        this.rerolls2 = BFacilitiesConfig.REROLLS.get();
        this.rerolls3 = BFacilitiesConfig.REROLLS.get();
        this.rerolls4 = BFacilitiesConfig.REROLLS.get();

        int slotIndex = 0;


        ItemStack paneYellow = new ItemStack(Items.YELLOW_STAINED_GLASS_PANE);
        ItemStack paneCyan = new ItemStack(Items.CYAN_STAINED_GLASS_PANE);
        ItemStack rerollButton = new ItemStack((Items.RED_STAINED_GLASS_PANE)).setDisplayName(new StringTextComponent("Reroll"));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                if (i == 5 || i == 0) {
                    this.addSlot(new PlaceHolderSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                    if (slotIndex % 2 == 0) {
                        inventory.setInventorySlotContents(slotIndex, paneYellow);
                    } else {
                        inventory.setInventorySlotContents(slotIndex, paneCyan);
                    }
                } else {
                    if (j < 6) {
                        this.addSlot(new TeamSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        int rand = random.nextInt(904) + 1;
                        ItemStack photo = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromNationalDex(rand)));
                        inventory.setInventorySlotContents(slotIndex, photo.setDisplayName(PixelmonSpecies.fromNationalDex(rand).getTranslatedName()));
                    }
                    if (j == 6) {
                        this.addSlot(new PlaceHolderSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, new ItemStack((Items.GRAY_STAINED_GLASS_PANE)));
                    }
                    if (j == 7) {
                        this.addSlot(new SelectSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, new ItemStack((Items.GREEN_STAINED_GLASS_PANE)).setDisplayName(new StringTextComponent("Select this team")));
                    }
                    if (j == 8) {
                        this.addSlot(new RerollSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                        inventory.setInventorySlotContents(slotIndex, rerollButton);
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
    public boolean canInteractWith(@NotNull PlayerEntity playerIn) {
        return true;
    }

    @Override
    public Container createMenu(int windowId, @NotNull PlayerInventory playerInventory, @NotNull PlayerEntity player) {
        return new TeamSelectionContainer(windowId, player);
    }

    @Override
    public @NotNull ITextComponent getDisplayName() {
        return new StringTextComponent("Select your Team");
    }

    @Override
    public @NotNull ItemStack slotClick(int slotId, int dragType, @NotNull ClickType clickType, @NotNull PlayerEntity player) {

        Slot slot = inventorySlots.get(slotId);

        ItemStack clickedItem = inventory.getStackInSlot(slotId);
        inventory.setInventorySlotContents(slotId, clickedItem);

        if (pickUped) {
            pickUped = false;
            return clickedItem;
        }

        pickUped = true;

        if (clickType == ClickType.PICKUP) {
            if (slot instanceof RerollSlot) {
                switch (slotId / 9) {
                    case 1:
                        if (this.rerolls1 == 0) {
                            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
                            return clickedItem;
                        }
                        this.rerolls1 -= 1;
                        break;
                    case 2:
                        if (this.rerolls2 == 0) {
                            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
                            return clickedItem;
                        }
                        this.rerolls2 -= 1;
                        break;
                    case 3:
                        if (this.rerolls3 == 0) {
                            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
                            return clickedItem;
                        }
                        this.rerolls3 -= 1;
                        break;
                    case 4:
                        if (this.rerolls4 == 0) {
                            player.sendMessage(new StringTextComponent("Out of rerolls for this line"), player.getUniqueID());
                            return clickedItem;
                        }
                        this.rerolls4 -= 1;
                        break;
                }
                for (int i = 0; i < 6; ++i) {
                    int row = slotId / 9;
                    int rand = random.nextInt(904) + 1;
                    ItemStack photo = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromNationalDex(rand)));
                    inventory.setInventorySlotContents(row * 9 + i, photo.setDisplayName(PixelmonSpecies.fromNationalDex(rand).getTranslatedName()));
                }
            }

        }

        if (clickType != ClickType.SWAP) {
            if (slot instanceof SelectSlot) {
                ItemStack[] team = new ItemStack[6];
                for (int i = 0; i < 6; ++i) {
                    int row = slotId / 9;
                    {
                        team[i] = inventory.getStackInSlot(row * 9 + i);
                    }
                }
                LOGGER.info("Initiate temporary party for" + player.getName().getString());
                TempParty tempParty = new TempParty(player);
                tempParty.enterTempMode(PartyParser.parseItemsToTeam(team, player));
            }
        }


        return clickedItem;
    }

}
