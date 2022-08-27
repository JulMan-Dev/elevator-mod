package fr.julman.elevatorblock;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class Tab extends CreativeModeTab {
    public static final ItemStack ITEM = ItemStack.EMPTY;

    public Tab() {
        super("elevator_block");
    }

    @Override
    public ItemStack makeIcon() {
        return ITEM;
    }

    @Override
    public boolean hasSearchBar() {
        return false;
    }
}
