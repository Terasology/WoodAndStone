// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.woodandstone.generator.plant.forest.tree;

import org.terasology.anotherWorld.AnotherWorldBiomes;
import org.terasology.anotherWorld.decorator.BlockCollectionPredicate;
import org.terasology.anotherWorldPlants.tree.PineGrowthDefinition;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.growingflora.PlantType;
import org.terasology.growingflora.generator.GrowthBasedPlantSpawnDefinition;
import org.terasology.woodandstone.generator.Blocks;

import java.util.Arrays;

@RegisterPlugin
public class PineForestSpawnDefinition extends GrowthBasedPlantSpawnDefinition {
    public PineForestSpawnDefinition() {
        super(PlantType.TREE, PineGrowthDefinition.ID, AnotherWorldBiomes.FOREST.getId().toLowerCase(), 0.6f, 0.8f,
                new BlockCollectionPredicate(Arrays.asList(Blocks.getBlock("CoreAssets:Grass"))));
    }
}