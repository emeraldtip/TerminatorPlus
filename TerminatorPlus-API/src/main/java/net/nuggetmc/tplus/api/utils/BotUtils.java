package net.nuggetmc.tplus.api.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.NumberConversions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BotUtils {

    public static final Set<Material> NO_FALL = new HashSet<>(Arrays.asList(
        Material.WATER,
        Material.LAVA,
        Material.TWISTING_VINES,
        Material.VINE,
        Material.LADDER,
        Material.COBWEB
    ));

    public static UUID randomSteveUUID() {
        UUID uuid = UUID.randomUUID();

        if (uuid.hashCode() % 2 == 0) {
            return uuid;
        }

        return randomSteveUUID();
    }
    
    public static boolean solidAt(BoundingBox playerBox, BoundingBox blockBox) {
    	return playerBox.overlaps(blockBox);
    }
    
    public static double getHorizSqDist(Location blockLoc, Location pLoc) {
    	return NumberConversions.square(blockLoc.getX() + 0.5 - pLoc.getX()) + NumberConversions.square(blockLoc.getZ() + 0.5 - pLoc.getZ());
    }
}
