/*
 *  Copyright (C) <2024> <XiaoMoMi>
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

package net.momirealms.customfishing.api.mechanic.competition;

import net.momirealms.customfishing.common.plugin.feature.Reloadable;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.UUID;

/**
 * Interface for managing fishing competitions.
 */
public interface CompetitionManager extends Reloadable {

    /**
     * Register a competition
     *
     * @param competitionConfig the config
     */
    boolean registerCompetition(CompetitionConfig competitionConfig);

    /**
     * Starts a competition by its name.
     *
     * @param competition the name of the competition to start.
     * @param force whether to force start the competition.
     * @param serverGroup the server group, may be null.
     * @return true if the competition started successfully, false otherwise.
     */
    boolean startCompetition(String competition, boolean force, @Nullable String serverGroup);

    /**
     * Starts a competition with a given configuration.
     *
     * @param config the configuration of the competition to start.
     * @param force whether to force start the competition.
     * @param serverGroup the server group, may be null.
     * @return true if the competition started successfully, false otherwise.
     */
    boolean startCompetition(CompetitionConfig config, boolean force, @Nullable String serverGroup);

    /**
     * Gets the ongoing competition, if any.
     *
     * @return the ongoing competition, or null if there is none.
     */
    @Nullable
    FishingCompetition getOnGoingCompetition();

    /**
     * Gets the time until the next competition starts, in seconds.
     *
     * @return the time until the next competition starts, in seconds.
     */
    int getNextCompetitionInSeconds();

    /**
     * Gets the configuration for a competition by its key.
     *
     * @param key the key of the competition configuration.
     * @return the competition configuration, or null if not found.
     */
    @Nullable
    CompetitionConfig getCompetition(String key);

    /**
     * Gets the IDs of all available competitions.
     *
     * @return a collection of competition IDs.
     */
    Collection<String> getCompetitionIDs();

    /**
     * Count the online players.
     */
    int onlinePlayerCountProvider();

    /**
     * Updates the player count for a specific UUID.
     *
     * @param uuid the UUID of the server.
     * @param count the new player count.
     */
    void updatePlayerCount(UUID uuid, int count);

    /**
     * Gets the UUID of the world where the fishing competition event takes place.
     * When set, only catches made in this world count towards the competition.
     *
     * @return the UUID of the competition world, or null if any world counts.
     */
    @Nullable
    UUID getCompetitionWorld();

    /**
     * Sets the UUID of the world where the fishing competition event takes place.
     * Pass null to clear the restriction and count catches from every world.
     *
     * @param worldId the UUID of the competition world, or null to clear it.
     */
    void setCompetitionWorld(@Nullable UUID worldId);

    /**
     * Checks whether a player is fishing in the configured competition world.
     * When no competition world has been set up, every player is allowed.
     *
     * @param player the player to check.
     * @return true if the player's world is the competition world (or no world is set), false otherwise.
     */
    boolean isInCompetitionWorld(@Nullable Player player);
}
