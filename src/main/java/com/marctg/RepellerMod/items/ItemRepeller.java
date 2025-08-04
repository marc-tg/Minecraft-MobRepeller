package com.marctg.RepellerMod.items;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemRepeller extends Item {

    public ItemRepeller(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);

        if (!world.isClientSide && entity instanceof Player player && selected) {
            List<Mob> mobs = world.getEntitiesOfClass(Mob.class, player.getBoundingBox().inflate(10), mob -> mob instanceof Monster);

            for (Mob mob : mobs) {
                mob.setTarget(null); // Que no te ataque
                double dx = mob.getX() - player.getX();
                double dz = mob.getZ() - player.getZ();
                double dist = Math.sqrt(dx * dx + dz * dz);

                if (dist > 0) {
                    // Empuja al mob lejos del jugador
                    mob.setDeltaMovement(dx / dist * 0.5, mob.getDeltaMovement().y, dz / dist * 0.5);
                    mob.hurtMarked = true; // Para que Minecraft actualice el movimiento
                }
            }
        }
    }
}
