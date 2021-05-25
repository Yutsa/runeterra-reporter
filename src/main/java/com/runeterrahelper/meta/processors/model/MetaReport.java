package com.runeterrahelper.meta.processors.model;

import java.util.List;

public class MetaReport {
    private final List<Archetype> archetypes;

    public MetaReport(List<Archetype> archetypes) {
        this.archetypes = archetypes;
    }

    public List<Archetype> getArchetypes() {
        return archetypes;
    }
}
