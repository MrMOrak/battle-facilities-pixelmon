package de.monver.bfacilities.events;

import com.pixelmonmod.pixelmon.api.pokemon.Pokemon;
import com.pixelmonmod.pixelmon.api.storage.StorageProxy;
import com.pixelmonmod.pixelmon.items.SpriteItem;
import de.monver.bfacilities.utils.StatsHelper;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

public class ToolTipEvent {

    @SubscribeEvent
    public void onToolTipRender(ItemTooltipEvent event) {

        if (event.getItemStack().getItem() instanceof SpriteItem) {
            List<Pokemon> team = StorageProxy.getParty(event.getPlayer().getUniqueID()).getTeam();
            if (StorageProxy.getParty(event.getPlayer().getUniqueID()).inTemporaryMode()) {
                if (team.stream().anyMatch(str -> str.getSpecies().getDex() == event.getItemStack().getTag().getInt("ndex"))) {
                    event.getToolTip().clear();
                    Pokemon chosenOne = team.stream()
                            .filter(pkm -> pkm.getSpecies().getDex() == event.getItemStack().getTag().getInt("ndex")).findFirst().get();
                    event.getToolTip().add(new StringTextComponent(TextFormatting.GREEN + chosenOne.getDisplayName()));
                    event.getToolTip().add(new StringTextComponent(StatsHelper.getAbility(chosenOne)));
                    event.getToolTip().add(new StringTextComponent(StatsHelper.getNature(chosenOne)));
                    event.getToolTip().add(new StringTextComponent(StatsHelper.getIvs(chosenOne)));
                    event.getToolTip().add(new StringTextComponent(StatsHelper.getEvs(chosenOne)));
                }
            }
        }
    }
}
