package gay.xujun.swimcraft.item;

import gay.xujun.swimcraft.Swimcraft;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final ResourceKey<CreativeModeTab> SWIMCRAFT_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Identifier.fromNamespaceAndPath(Swimcraft.MOD_ID, "swimcraft_tab"));

    public static final CreativeModeTab SWIMCRAFT_TAB = FabricCreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BOXER))
            .title(Component.translatable("itemGroup.swimcraft.swimcraft_tab"))
            .displayItems((parameters, output) -> {
                output.accept(ModItems.DARK_BOXER);
                output.accept(ModItems.LIME_BOXER);
                output.accept(ModItems.PURPLE_BOXER);
                output.accept(ModItems.WHITE_BOXER);

                output.accept(ModItems.RED_ETCHU_FUNDOSHI);
                output.accept(ModItems.WHITE_ETCHU_FUNDOSHI);

                output.accept(ModItems.RED_ROKUSHAKU_FUNDOSHI);
                output.accept(ModItems.WHITE_ROKUSHAKU_FUNDOSHI);

                output.accept(ModItems.BLUE_TRUNKS);
                output.accept(ModItems.GREEN_TRUNKS);
                output.accept(ModItems.RED_TRUNKS);
                output.accept(ModItems.YELLOW_TRUNKS);

                output.accept(ModItems.BLACK_BRIEF);
                output.accept(ModItems.BLUE_BRIEF);
                output.accept(ModItems.WHITE_BRIEF);

                output.accept(ModItems.BLUE_RACING_BRIEF);
                output.accept(ModItems.BLACK_RACING_BRIEF);
                output.accept(ModItems.LIFE_SAVER_RACING_BRIEF);
            })
            .build();

    public static void init() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SWIMCRAFT_TAB_KEY, SWIMCRAFT_TAB);
        Swimcraft.LOGGER.info("Registering ModCreativeTabs for " + Swimcraft.MOD_ID);
    }
}

