/*
 * Copyright 2014 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.crafting.system.recipe.render;

import com.google.common.base.Function;
import org.joml.Vector2i;
import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.logic.common.DisplayNameComponent;
import org.terasology.module.inventory.systems.InventoryUtils;
import org.terasology.engine.logic.inventory.ItemComponent;
import org.terasology.module.inventory.ui.GetItemTooltip;
import org.terasology.module.inventory.ui.ItemIcon;
import org.terasology.engine.utilities.Assets;
import org.terasology.engine.world.block.items.BlockItemComponent;
import org.terasology.joml.geom.Rectanglei;
import org.terasology.nui.Canvas;

public class ItemSlotIngredientRenderer implements CraftIngredientRenderer {
    private ItemIcon itemIcon;
    private Function<Integer, Integer> multiplierFunction;

    public ItemSlotIngredientRenderer() {
        itemIcon = new ItemIcon();
    }

    public void update(EntityRef entity, int slot, Function<Integer, Integer> newMultiplierFunction) {
        multiplierFunction = newMultiplierFunction;

        EntityRef item = InventoryUtils.getItemAt(entity, slot);
        ItemComponent itemComp = item.getComponent(ItemComponent.class);
        BlockItemComponent blockItemComp = item.getComponent(BlockItemComponent.class);
        if (itemComp != null && itemComp.icon != null) {
            itemIcon.setIcon(itemComp.icon);
        } else if (blockItemComp != null) {
            itemIcon.setMesh(blockItemComp.blockFamily.getArchetypeBlock().getMesh());
            itemIcon.setMeshTexture(Assets.getTexture("engine:terrain").get());
        }
        GetItemTooltip tooltipEvent;
        DisplayNameComponent displayName = item.getComponent(DisplayNameComponent.class);
        if (displayName != null) {
            tooltipEvent = new GetItemTooltip(displayName.name);
        } else {
            tooltipEvent = new GetItemTooltip();
        }
        item.send(tooltipEvent);

        itemIcon.setTooltipLines(tooltipEvent.getTooltipLines());
    }

    @Override
    public Vector2i getPreferredSize(Canvas canvas, int multiplier) {
        return new Vector2i(56, 56);
    }

    @Override
    public void render(Canvas canvas, Rectanglei region, int multiplier) {
        itemIcon.setQuantity(multiplierFunction.apply(multiplier));
        canvas.drawWidget(itemIcon, region);
    }
}
