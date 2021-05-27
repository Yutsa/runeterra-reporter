package com.runeterrahelper.meta.processors.model;

import java.util.List;

public class MetaReport {
    private final List<ArchetypeStat> archetypeStats;

    public MetaReport(List<ArchetypeStat> archetypeStats) {
        this.archetypeStats = archetypeStats;
    }

    public List<ArchetypeStat> getArchetypes() {
        return archetypeStats;
    }
}
