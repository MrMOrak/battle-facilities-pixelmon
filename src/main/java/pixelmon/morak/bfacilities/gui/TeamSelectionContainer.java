package pixelmon.morak.bfacilities.gui;

import com.pixelmonmod.pixelmon.api.pokemon.PokemonFactory;
import com.pixelmonmod.pixelmon.api.registries.PixelmonSpecies;
import com.pixelmonmod.pixelmon.api.util.helpers.SpriteItemHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import pixelmon.morak.bfacilities.TempParty;
import pixelmon.morak.bfacilities.Utils;
import java.util.Random;


public class TeamSelectionContainer extends Container implements INamedContainerProvider {

    private static final int ROWS = 6;
    private static final int COLUMNS = 9;

    Random random = new Random();


    Utils utils = new Utils();


    private final Inventory inventory = new Inventory(ROWS*COLUMNS);

    public TeamSelectionContainer(int windowId, PlayerEntity player){
        super(ContainerType.GENERIC_9X6, windowId);

        int slotIndex = 0;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 9; j++) {
                    this.addSlot(new PlaceHolderSlot(inventory, slotIndex, 8 + j * 18, 18 + i * 18));
                    slotIndex++;
            }
        }

        ItemStack stack = SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.CHARIZARD.getValueUnsafe()));

        ItemStack[] testStacks = new ItemStack[]{stack, stack, stack, stack, stack, stack};

        new TempParty(utils.parseItemstoTeam(testStacks), player);

        prefillContainerWithSeparation();
        fillContainerWithRandomTeams();
    }



    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity player) {
        return new TeamSelectionContainer(windowId, player);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("Select your Team");
    }

    private void prefillContainerWithSeparation(){
        ItemStack paneYellow = new ItemStack(Items.YELLOW_STAINED_GLASS_PANE);
        ItemStack paneCyan = new ItemStack(Items.CYAN_STAINED_GLASS_PANE);
        for(int i = 1; i<9;){
            inventory.setInventorySlotContents(i, paneYellow);
            i = i+2;
        }
        for(int i = 10; i<18;) {
            inventory.setInventorySlotContents(i, paneCyan);
            i = i+2;
        }
        for(int i = 19; i<27;){
            inventory.setInventorySlotContents(i, paneYellow);
            i = i+2;
        }
        for(int i = 28; i<36;){
            inventory.setInventorySlotContents(i, paneCyan);
            i = i+2;
        }
        for(int i = 37; i<45;){
            inventory.setInventorySlotContents(i, paneYellow);
            i = i+2;
        }
        for(int i = 46; i<54;){
            inventory.setInventorySlotContents(i, paneCyan);
            i = i+2;
        }
    }

    public void fillContainerWithRandomTeams(){
        for(int i = 0; i<10;){
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
        for(int i = 9; i<19;) {
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
        for(int i = 18; i<28;){
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
        for(int i = 27; i<37;){
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
        for(int i = 36; i<46;){
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
        for(int i = 45; i<55;){
            inventory.setInventorySlotContents(i, SpriteItemHelper.getPhoto(PokemonFactory.create(PixelmonSpecies.fromDex(random.nextInt(152)).get())));
            i = i+2;
        }
    }


}
