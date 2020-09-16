// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.woodandstone.generator.plant.forest.tree;

import org.terasology.anotherWorld.AnotherWorldBiomes;
import org.terasology.anotherWorld.decorator.BlockCollectionPredicate;
import org.terasology.anotherWorldPlants.tree.SakuraGrowthDefinition;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.growingflora.PlantType;
import org.terasology.growingflora.generator.GrowthBasedPlantSpawnDefinition;
import org.terasology.woodandstone.generator.Blocks;

import java.util.Arrays;

@RegisterPlugin
public class SakuraForestSpawnDefinition extends GrowthBasedPlantSpawnDefinition {
    public SakuraForestSpawnDefinition() {
        super(PlantType.TREE, SakuraGrowthDefinition.ID, AnotherWorldBiomes.FOREST.getId().toLowerCase(), 0.2f, 0.6f,
                new BlockCollectionPredicate(Arrays.asList(Blocks.getBlock("CoreAssets:Grass"))));
    }
}