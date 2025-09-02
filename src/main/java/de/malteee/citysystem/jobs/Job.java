package de.malteee.citysystem.jobs;

import org.bukkit.Material;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Job {

    BUILDER(Arrays.asList()),
    HUNTER(Arrays.asList()),
    LUMBERJACK(Arrays.asList()), //everything with wood, planks,
    MINER(Arrays.asList()),
    TRADER(Arrays.asList()), //Villager
    FISHER(Arrays.asList()),
    NONE(Arrays.asList());

    public static final ArrayList<Material> allJobBlocks = new ArrayList<>();

    static {
        for (Job job : Job.values())
            allJobBlocks.addAll(job.getBlocks());
    }

    private List<Material> blocks;

    Job(List<Material> blocks) {
        this.blocks = blocks;
    }

    public List<Material> getBlocks() {
        return blocks;
    }

    public List<Material> getInvertedBlocks() {
        ArrayList<Material> list = (ArrayList<Material>) allJobBlocks.clone();
        list.removeAll(this.blocks);
        return list;
    }
}
