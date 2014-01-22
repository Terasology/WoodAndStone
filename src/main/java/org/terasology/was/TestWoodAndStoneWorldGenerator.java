/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.was;

import org.terasology.anotherWorld.BiomeProvider;
import org.terasology.anotherWorld.ChunkDecorator;
import org.terasology.anotherWorld.ChunkInformation;
import org.terasology.anotherWorld.PerlinLandscapeGenerator;
import org.terasology.anotherWorld.PluggableWorldGenerator;
import org.terasology.anotherWorld.coreBiome.DesertBiome;
import org.terasology.anotherWorld.coreBiome.ForestBiome;
import org.terasology.anotherWorld.coreBiome.PlainsBiome;
import org.terasology.anotherWorld.coreBiome.TundraBiome;
import org.terasology.anotherWorld.decorator.layering.DefaultLayersDefinition;
import org.terasology.anotherWorld.decorator.layering.LayeringDecorator;
import org.terasology.anotherWorld.decorator.ore.OreDecorator;
import org.terasology.anotherWorld.util.PDist;
import org.terasology.engine.CoreRegistry;
import org.terasology.engine.SimpleUri;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockManager;
import org.terasology.world.chunks.Chunk;
import org.terasology.world.generator.RegisterWorldGenerator;
import org.terasology.world.liquid.LiquidType;

import java.util.Collections;

/**
 * @author Marcin Sciesinski <marcins78@gmail.com>
 */
@RegisterWorldGenerator(id = "woodAndStoneTest", displayName = "Test Wood and Stone", description = "Generates the world for playing 'Wood and Stone' content mod")
public class TestWoodAndStoneWorldGenerator extends PluggableWorldGenerator {

    public TestWoodAndStoneWorldGenerator(SimpleUri uri) {
        super(uri);
    }

    @Override
    protected void appendGenerators() {
        setSeaLevel(50);

        BlockManager blockManager = CoreRegistry.get(BlockManager.class);

        final Block mantle = blockManager.getBlock("Core:MantleStone");
        final Block stone = blockManager.getBlock("Core:Stone");
        final Block water = blockManager.getBlock("Core:Water");
        final Block sand = blockManager.getBlock("Core:Sand");
        final Block dirt = blockManager.getBlock("Core:Dirt");
        final Block grass = blockManager.getBlock("Core:Grass");
        final Block snow = blockManager.getBlock("Core:Snow");

        setLandscapeGenerator(
                new PerlinLandscapeGenerator(0.6f, mantle, stone, water, LiquidType.WATER));

        //setupLayers(sand, dirt, grass, snow);

        OreDecorator oreDecorator = new OreDecorator(Collections.singleton(stone));
        //oreDecorator.addOreDefinition("dirt", new ClusterOreDefinition(new PDist(1f, 0f), sand, new PDist(8f, 0f), new PDist(40f, 20f)));
//        oreDecorator.addOreDefinition("dirt", new PocketOreDefinition(
//                new PocketOreDefinition.PocketBlockProvider() {
//                    @Override
//                    public Block getBlock(float distanceFromCenter) {
//                        return sand;
//                    }
//                }, new PDist(0.025f, 0f), new PDist(10f, 2f), new PDist(6f, 1f), new PDist(30f, 10f), new PDist(0.1f, 0.1f),
//                new PDist(1f, 0f), new PDist(1f, 0f), new PDist(1f, 0f), new PDist(0.3f, 0f)));

//        oreDecorator.addOreDefinition("dirt",
//                new VeinsOreDefinition(
//                        new PDist(0.025f, 0f), new VeinsOreDefinition.VeinsBlockProvider() {
//                    @Override
//                    public Block getClusterBlock(float distanceFromCenter) {
//                        return sand;
//                    }
//
//                    @Override
//                    public Block getBranchBlock() {
//                        return dirt;
//                    }
//                }, new PDist(5f, 0f), new PDist(30f, 20f), new PDist(3f, 1f), new PDist(0f, 0.5f), new PDist(40f, 0f), new PDist(10f, 0f),
//                        new PDist(0f, 0f), new PDist(0.4f, 0f), new PDist(3f, 0f), new PDist(0.4f, 0.2f), new PDist(0.8f, 0.3f), new PDist(1f, 0f), new PDist(1f, 0f)));

        addChunkDecorator(oreDecorator);

        addChunkDecorator(
                new ChunkDecorator() {
                    @Override
                    public void initializeWithSeed(String seed) {
                    }

                    @Override
                    public void generateInChunk(Chunk chunk, ChunkInformation chunkInformation, BiomeProvider biomeProvider, int seaLevel) {
                        for (int x = 0; x < chunk.getChunkSizeX(); x++) {
                            for (int z = 0; z < chunk.getChunkSizeZ(); z++) {
                                for (int y = 1; y <= chunkInformation.getGroundLevel(x, z); y++) {
                                    if (chunk.getBlock(x, y, z) == stone) {
                                        chunk.setBlock(x, y, z, BlockManager.getAir());
                                    }
                                }
                            }
                        }
                    }
                }
        );
    }

    private void setupLayers(Block sand, Block dirt, Block grass, Block snow) {
        LayeringDecorator layering = new LayeringDecorator();

        DefaultLayersDefinition desertDef = new DefaultLayersDefinition();
        desertDef.addLayerDefinition(new PDist(3, 1), sand, false);
        desertDef.addLayerDefinition(new PDist(4, 2), dirt, true);
        layering.addBiomeLayers(DesertBiome.ID, desertDef);

        DefaultLayersDefinition forestAndPlainsDef = new DefaultLayersDefinition();
        forestAndPlainsDef.addLayerDefinition(new PDist(1, 0), grass, false);
        forestAndPlainsDef.addLayerDefinition(new PDist(4, 2), dirt, true);
        layering.addBiomeLayers(ForestBiome.ID, forestAndPlainsDef);
        layering.addBiomeLayers(PlainsBiome.ID, forestAndPlainsDef);

        DefaultLayersDefinition tundraDef = new DefaultLayersDefinition();
        tundraDef.addLayerDefinition(new PDist(1, 0), snow, false);
        tundraDef.addLayerDefinition(new PDist(4, 2), dirt, true);
        layering.addBiomeLayers(TundraBiome.ID, tundraDef);

        addChunkDecorator(layering);
    }
}
