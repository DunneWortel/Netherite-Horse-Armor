package com.dunnewortel.netheritehorsearmor;

import com.dunnewortel.netheritehorsearmor.items.NetheriteHorseArmorItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.BinomialLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class NetheriteHorseArmor implements ModInitializer {

    public static final String MODID = "netheritehorsearmor";
    private static final Identifier NETHERITE_HORSE_ARMOR_ID = new Identifier(MODID, "netherite_horse_armor");
    private static final Identifier BASTION_TREASURE_ID = new Identifier("minecraft", "chests/bastion_treasure");

    // Items
    public static Item NETHERITE_HORSE_ARMOR;

    @Override
    public void onInitialize() {
        // Delay the registration of Netherite Horse Armor to ensure it's after Diamond Horse Armor
        if (Registries.ITEM.getId(Items.DIAMOND_HORSE_ARMOR) != null) {
            registerItems();
        } else {
            // If Diamond Horse Armor is not registered yet, you might need to wait and check again later.
            // Implement a delayed task or an event listener to handle this case.
        }
        modifyLootTables();
    }

    private void registerItems() {
        NETHERITE_HORSE_ARMOR = Registry.register(Registries.ITEM, NETHERITE_HORSE_ARMOR_ID,
                new NetheriteHorseArmorItem(15, "netherite"));
    }

    private void modifyLootTables() {
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (!source.isBuiltin()) {
                return;
            }

            if (BASTION_TREASURE_ID.equals(id)) {
                addLootPoolToLootTable(tableBuilder);
            }
        });
    }

    private void addLootPoolToLootTable(LootTable.Builder tableBuilder) {
        LootPool pool = LootPool.builder()
                .with(ItemEntry.builder(NETHERITE_HORSE_ARMOR))
                .rolls(BinomialLootNumberProvider.create(3, 0.25f))
                .build();
        tableBuilder.pool(pool);
    }
}
