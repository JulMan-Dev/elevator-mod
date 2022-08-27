package fr.julman.elevatorblock.items;

import java.util.List;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.Utils;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundSetActionBarTextPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class TeleportCoreItem extends ModItem {
    public TeleportCoreItem() {
        super(new Properties().tab(ElevatorBlockMod.TAB));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> components, TooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            components.add(
                    new TranslatableComponent("item.elevator_block.teleport_core.tooltip.shift.0"));
            components.add(new TextComponent(""));
            components.add(
                    new TranslatableComponent("item.elevator_block.teleport_core.tooltip.shift.1"));
        } else
            components.add(
                    new TranslatableComponent("item.elevator_block.tooltip.default"));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Vec3 pos = context.getClickLocation();

        if (pos != null) {
            Player player = context.getPlayer();
            Level world = context.getLevel();

            BlockPos target = context.getClickedPos().relative(context.getClickedFace());

            BlockState blockState1 = world.getBlockState(target);
            BlockState blockState2 = world.getBlockState(target.above());

            // Check if the blocks not is a solid block or is air block
            if (blockState1.canOcclude() || blockState2.canOcclude()) {
                player.playSound(
                        new SoundEvent(new ResourceLocation("minecraft:entity.villager.no")),
                        1.0f, 1.0f);

                ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(
                        new TranslatableComponent("item.elevator_block.teleport_core.text.fail"));

                Utils.setActionBarText(player, packet);

                return InteractionResult.SUCCESS;
            }

            player.teleportTo(
                    target.getX() + 0.5,
                    target.getY(),
                    target.getZ() + 0.5);

            if (!player.isCreative())
                context.getItemInHand().shrink(1);

            player.getCooldowns().addCooldown(this, 20);

            player.playSound(
                    new SoundEvent(new ResourceLocation("minecraft:entity.enderman.teleport")),
                    1.0f, 1.0f);

            ClientboundSetActionBarTextPacket packet = new ClientboundSetActionBarTextPacket(
                    new TranslatableComponent("item.elevator_block.teleport_core.text.success"));

            Utils.setActionBarText(player, packet);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }
}
