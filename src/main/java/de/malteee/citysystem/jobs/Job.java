package de.malteee.citysystem.jobs;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Job {

    BUILDER(Arrays.asList()),
    HUNTER(Arrays.asList()),
    LUMBERJACK(Arrays.asList(Material.ACACIA_WOOD, Material.BIRCH_WOOD, Material.CHERRY_WOOD, Material.OAK_WOOD)),
    MINER(Arrays.asList()),
    TRADER(Arrays.asList()), //Villager
    FISHER(Arrays.asList()),
    NONE(Arrays.asList());

    private List<Material> blocks;

    Job(List<Material> blocks) {
        this.blocks = blocks;
    }
}
