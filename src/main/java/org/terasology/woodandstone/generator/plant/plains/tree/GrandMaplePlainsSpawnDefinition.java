// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.woodandstone.generator.plant.plains.tree;

import org.terasology.anotherWorld.AnotherWorldBiomes;
import org.terasology.anotherWorld.decorator.BlockCollectionPredicate;
import org.terasology.anotherWorldPlants.tree.GrandMapleGrowthDefinition;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.growingflora.PlantType;
import org.terasology.growingflora.generator.GrowthBasedPlantSpawnDefinition;
import org.terasology.woodandstone.generator.Blocks;

import java.util.Arrays;

@RegisterPlugin
public class GrandMaplePlainsSpawnDefinition extends GrowthBasedPlantSpawnDefinition {
    public GrandMaplePlainsSpawnDefinition() {
        super(PlantType.TREE, GrandMapleGrowthDefinition.ID, AnotherWorldBiomes.PLAINS.getId().toLowerCase(), 0.6f,
                0.05f,
                new BlockCollectionPredicate(Arrays.asList(Blocks.getBlock("CoreAssets:Grass"))));
    }
}