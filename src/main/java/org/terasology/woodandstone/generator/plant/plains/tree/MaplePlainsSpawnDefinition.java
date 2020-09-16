// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.woodandstone.generator.plant.plains.tree;

import org.terasology.anotherWorld.AnotherWorldBiomes;
import org.terasology.anotherWorld.decorator.BlockCollectionPredicate;
import org.terasology.anotherWorldPlants.tree.MapleGrowthDefinition;
import org.terasology.engine.world.generator.plugin.RegisterPlugin;
import org.terasology.growingflora.PlantType;
import org.terasology.growingflora.generator.GrowthBasedPlantSpawnDefinition;
import org.terasology.woodandstone.generator.Blocks;

import java.util.Arrays;

@RegisterPlugin
public class MaplePlainsSpawnDefinition extends GrowthBasedPlantSpawnDefinition {
    public MaplePlainsSpawnDefinition() {
        super(PlantType.TREE, MapleGrowthDefinition.ID, AnotherWorldBiomes.PLAINS.getId().toLowerCase(), 0.5f, 0.2f,
                new BlockCollectionPredicate(Arrays.asList(Blocks.getBlock("CoreAssets:Grass"))));
    }
}