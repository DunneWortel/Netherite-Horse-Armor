package com.dunnewortel.netheritehorsearmor.items;

import com.dunnewortel.netheritehorsearmor.NetheriteHorseArmor;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.util.Identifier;

public class NetheriteHorseArmorItem extends HorseArmorItem{

        private static final String TEXTURE_PATH_FORMAT = "textures/entity/horse/armor/horse_armor_%s.png";
        private final String texturePath;

        public NetheriteHorseArmorItem(int bonus, String materialName) {
            super(bonus, null, new Settings().maxCount(1).fireproof());
            validateMaterialName(materialName);
            this.texturePath = String.format(TEXTURE_PATH_FORMAT, materialName);
            registerToItemGroup();
        }

        private void validateMaterialName(String materialName) {
            if (materialName == null || materialName.trim().isEmpty()) {
                throw new IllegalArgumentException("Material name cannot be null or empty");
            }
        }

        private void registerToItemGroup() {
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> content.add(this));
        }

        @Override
        public Identifier getEntityTexture() {
            return new Identifier(NetheriteHorseArmor.MODID, texturePath);
        }
}

