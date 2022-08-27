package fr.julman.elevatorblock.events;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import fr.julman.elevatorblock.ElevatorBlockMod;
import fr.julman.elevatorblock.block.ElevatorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ElevatorBlockMod.MOD_ID)
public class EventListener {
    public static final int MAX_SEARCH_DISTANCE = 10;

    public static final Map<UUID, Date> COOLDOWNS = new HashMap<>();

    @SubscribeEvent
    public static void onLivingEntityJump(LivingJumpEvent event) {
        if (!(event.getEntityLiving() instanceof Player))
            return;

        Player player = (Player) event.getEntityLiving();

        Block block = player.getLevel().getBlockState(
                new BlockPos(player.position().subtract(0, 1, 0))).getBlock();

        if (!(block instanceof ElevatorBlock))
            return;

        ElevatorBlock base = (ElevatorBlock) block;

        int min_height = Math.min(
                (int) player.getY() + MAX_SEARCH_DISTANCE,
                player.getLevel().getMaxBuildHeight());

        for (int y = player.getBlockY(); y < min_height; y++) {
            Block block_below = player.getLevel().getBlockState(
                    new BlockPos(player.getBlockX(), y, player.getBlockZ())).getBlock();

            if (block_below instanceof ElevatorBlock) {
                ElevatorBlock elevatorBlock = (ElevatorBlock) block_below;

                if (!base.canTeleportTo(elevatorBlock, player.getLevel(),
                        new BlockPos(player.position().subtract(0, 1, 0)),
                        new BlockPos(player.getBlockX(), y, player.getBlockZ())))
                    continue;

                player.teleportTo(
                        player.getX(),
                        y + 1,
                        player.getZ());
                player.playSound(
                        new SoundEvent(new ResourceLocation("entity.enderman.teleport")),
                        1, 1);
                break;
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        boolean sneaking = event.player.isShiftKeyDown();

        if (!sneaking)
            return;

        if (!COOLDOWNS.containsKey(event.player.getUUID()))
            COOLDOWNS.put(event.player.getUUID(), Date.from(Instant.now().plusMillis(100)));

        if (COOLDOWNS.get(event.player.getUUID()).after(Date.from(Instant.now())))
            return;

        COOLDOWNS.put(event.player.getUUID(), Date.from(Instant.now().plusMillis(500)));

        EventListener.onPlayerSneak(event.player);
    }

    public static void onPlayerSneak(Player player) {
        Block block = player.getLevel().getBlockState(
                new BlockPos(player.position().subtract(0, 1, 0))).getBlock();

        if (!(block instanceof ElevatorBlock))
            return;

        ElevatorBlock base = (ElevatorBlock) block;

        int max_height = Math.max(
                (int) player.getY() - MAX_SEARCH_DISTANCE,
                player.getLevel().getMinBuildHeight());

        for (int y = player.getBlockY() - 2; y > max_height; y--) {
            Block block_above = player.getLevel().getBlockState(
                    new BlockPos(player.getBlockX(), y, player.getBlockZ())).getBlock();

            if (block_above instanceof ElevatorBlock) {
                ElevatorBlock elevatorBlock = (ElevatorBlock) block_above;

                if (!base.canTeleportTo(elevatorBlock, player.getLevel(),
                        new BlockPos(player.position().subtract(0, 1, 0)),
                        new BlockPos(player.getBlockX(), y, player.getBlockZ())))
                    continue;

                player.teleportTo(
                        player.getX(),
                        y + 1,
                        player.getZ());

                player.playSound(
                        new SoundEvent(new ResourceLocation("entity.enderman.teleport")),
                        1, 1);

                break;
            }
        }
    }
}
