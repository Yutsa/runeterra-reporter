package com.runeterrahelper.meta.processors.model;

import java.util.List;
import java.util.stream.Collectors;

public class MetaReport {
    private final List<ArchetypeStat> archetypeStats;

    public MetaReport(List<ArchetypeStat> archetypeStats) {
        this.archetypeStats = archetypeStats;
    }

    public List<ArchetypeStat> getArchetypes() {
        return archetypeStats.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
