package gay.xujun.swimcraft.item;

import gay.xujun.swimcraft.Swimcraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import java.util.Map;
import java.util.function.Function;

public class ModItems {
    public static final Item BOXER = registerSwimsuit("boxer");
    public static final Item DARK_BOXER = registerSwimsuit("dark_boxer");
    public static final Item LIME_BOXER = registerSwimsuit("lime_boxer");
    public static final Item PURPLE_BOXER = registerSwimsuit("purple_boxer");
    public static final Item WHITE_BOXER = registerSwimsuit("white_boxer");

    public static final Item ETCHU_FUNDOSHI = registerSwimsuit("etchu_fundoshi");
    public static final Item RED_ETCHU_FUNDOSHI = registerSwimsuit("red_etchu_fundoshi");
    public static final Item WHITE_ETCHU_FUNDOSHI = registerSwimsuit("white_etchu_fundoshi");

    public static final Item ROKUSHAKU_FUNDOSHI = registerSwimsuit("rokushaku_fundoshi");
    public static final Item RED_ROKUSHAKU_FUNDOSHI = registerSwimsuit("red_rokushaku_fundoshi");
    public static final Item WHITE_ROKUSHAKU_FUNDOSHI = registerSwimsuit("white_rokushaku_fundoshi");

    public static final Item TRUNKS = registerSwimsuit("trunks");
    public static final Item BLUE_TRUNKS = registerSwimsuit("blue_trunks");
    public static final Item GREEN_TRUNKS = registerSwimsuit("green_trunks");
    public static final Item RED_TRUNKS = registerSwimsuit("red_trunks");
    public static final Item YELLOW_TRUNKS = registerSwimsuit("yellow_trunks");

    public static final Item BRIEF = registerSwimsuit("brief");
    public static final Item BLACK_BRIEF = registerSwimsuit("black_brief");
    public static final Item BLUE_BRIEF = registerSwimsuit("blue_brief");
    public static final Item WHITE_BRIEF = registerSwimsuit("white_brief");

    public static final Item BLUE_RACING_BRIEF = registerSwimsuit("blue_racing_brief");
    public static final Item BLACK_RACING_BRIEF = registerSwimsuit("black_racing_brief");
    public static final Item LIFE_SAVER_RACING_BRIEF = registerSwimsuit("life_saver_racing_brief");

    private static Item registerSwimsuit(String name) {
        ResourceKey<EquipmentAsset> assetKey = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(Swimcraft.MOD_ID, name));
        ArmorMaterial material = new ArmorMaterial(15, Map.of(ArmorType.LEGGINGS, 2), 15, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, TagKey.create(BuiltInRegistries.ITEM.key(), Identifier.fromNamespaceAndPath(Swimcraft.MOD_ID, "repairs_" + name)), assetKey);
        return register(name, ArmorItem::new, new Item.Properties().humanoidArmor(material, ArmorType.LEGGINGS).durability(ArmorType.LEGGINGS.getDurability(15)));
    }


    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Swimcraft.MOD_ID, name));
        T item = itemFactory.apply(settings.setId(itemKey));
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);
        return item;
    }

    public static void init() {
        Swimcraft.LOGGER.info("Registering ModItems for " + Swimcraft.MOD_ID);
    }
}

