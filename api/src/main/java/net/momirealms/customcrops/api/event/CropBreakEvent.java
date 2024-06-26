/*
 *  Copyright (C) <2022> <XiaoMoMi>
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.momirealms.customcrops.api.event;

import net.momirealms.customcrops.api.mechanic.misc.Reason;
import net.momirealms.customcrops.api.mechanic.world.level.WorldCrop;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An event that triggered when breaking a crop
 */
public class CropBreakEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private boolean cancelled;
    private final Location location;
    private final WorldCrop worldCrop;
    private final Entity entity;
    private final Reason reason;

    public CropBreakEvent(
            @Nullable Entity entity,
            @NotNull Location location,
            @NotNull WorldCrop worldCrop,
            @NotNull Reason reason
    ) {
        this.entity = entity;
        this.location = location;
        this.worldCrop = worldCrop;
        this.reason = reason;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }

    /**
     * Get the crop's data, it might be null if it's spawned by other plugins in the wild
     *
     * @return crop data
     */
    @Nullable
    public WorldCrop getWorldCrop() {
        return worldCrop;
    }

    /**
     * Get the crop location
     *
     * @return location
     */
    @NotNull
    public Location getLocation() {
        return location;
    }

    /**
     * Get the entity that triggered the event
     * This would be null if the crop is broken by respawn anchor
     * The breaker can be a TNT, creeper.
     * If the pot is a vanilla farmland, it can be trampled by entities
     *
     * @return entity
     */
    @Nullable
    public Entity getEntity() {
        return entity;
    }

    @Nullable
    public Player getPlayer() {
        if (entity instanceof Player player) {
            return player;
        }
        return null;
    }

    /**
     * Get the reason
     *
     * @return reason
     */
    @NotNull
    public Reason getReason() {
        return reason;
    }
}
