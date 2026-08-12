package gay.xujun.swimcraft.item;

import gay.xujun.swimcraft.client.renderer.ArmorRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import com.geckolib.animatable.GeoItem;
import com.geckolib.animatable.client.GeoRenderProvider;
import com.geckolib.animatable.instance.AnimatableInstanceCache;
import com.geckolib.animatable.manager.AnimatableManager;
import com.geckolib.renderer.GeoArmorRenderer;
import com.geckolib.util.GeckoLibUtil;
import com.google.common.base.Suppliers;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ArmorItem extends Item implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ArmorItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }

    @Override
    public void createGeoRenderer(Consumer<GeoRenderProvider> consumer) {
        consumer.accept(new GeoRenderProvider() {
            private final Supplier<GeoArmorRenderer<?, ?>> renderer =
                    Suppliers.memoize(() -> new ArmorRenderer(ArmorItem.this));
            @Override
            public @Nullable GeoArmorRenderer<?, ?> getGeoArmorRenderer(ItemStack itemStack, EquipmentSlot equipmentSlot) {
                return this.renderer.get();
            }
        });
    }

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.server.level.ServerLevel level, net.minecraft.world.entity.Entity entity, EquipmentSlot slot) {
        if (entity instanceof net.minecraft.world.entity.player.Player player && slot == EquipmentSlot.LEGS) {
            
            boolean isFundoshi = stack.is(ModItems.ETCHU_FUNDOSHI) || stack.is(ModItems.RED_ETCHU_FUNDOSHI) || stack.is(ModItems.WHITE_ETCHU_FUNDOSHI) ||
                                 stack.is(ModItems.ROKUSHAKU_FUNDOSHI) || stack.is(ModItems.RED_ROKUSHAKU_FUNDOSHI) || stack.is(ModItems.WHITE_ROKUSHAKU_FUNDOSHI);
            boolean isRacingBrief = stack.is(ModItems.BLUE_RACING_BRIEF) || stack.is(ModItems.BLACK_RACING_BRIEF) || stack.is(ModItems.LIFE_SAVER_RACING_BRIEF);
            
            applyEffect(player, net.minecraft.world.effect.MobEffects.WATER_BREATHING, 0);

            if (isFundoshi || isRacingBrief) {
                applyEffect(player, net.minecraft.world.effect.MobEffects.DOLPHINS_GRACE, 2);
            } else {
                applyEffect(player, net.minecraft.world.effect.MobEffects.DOLPHINS_GRACE, 1);
            }

            if (stack.is(ModItems.LIFE_SAVER_RACING_BRIEF)) {
                applyEffect(player, net.minecraft.world.effect.MobEffects.REGENERATION, 0);
            }

            if (stack.is(ModItems.BRIEF) || stack.is(ModItems.BLACK_BRIEF) || stack.is(ModItems.BLUE_BRIEF) || stack.is(ModItems.WHITE_BRIEF)) {
                applyEffect(player, net.minecraft.world.effect.MobEffects.SPEED, 1);
            }
            
            if (isFundoshi) {
                applyEffect(player, net.minecraft.world.effect.MobEffects.STRENGTH, 1);
            }
            
            if (stack.is(ModItems.BOXER) || stack.is(ModItems.DARK_BOXER) || stack.is(ModItems.LIME_BOXER) || stack.is(ModItems.PURPLE_BOXER) || stack.is(ModItems.WHITE_BOXER)) {
                applyEffect(player, net.minecraft.world.effect.MobEffects.JUMP_BOOST, 1);
            }
        }
        super.inventoryTick(stack, level, entity, slot);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, net.minecraft.world.item.component.TooltipDisplay display, java.util.function.Consumer<net.minecraft.network.chat.Component> tooltipComponents, net.minecraft.world.item.TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, display, tooltipComponents, tooltipFlag);
        
        boolean isFundoshi = stack.is(ModItems.ETCHU_FUNDOSHI) || stack.is(ModItems.RED_ETCHU_FUNDOSHI) || stack.is(ModItems.WHITE_ETCHU_FUNDOSHI) ||
                             stack.is(ModItems.ROKUSHAKU_FUNDOSHI) || stack.is(ModItems.RED_ROKUSHAKU_FUNDOSHI) || stack.is(ModItems.WHITE_ROKUSHAKU_FUNDOSHI);
        boolean isRacingBrief = stack.is(ModItems.BLUE_RACING_BRIEF) || stack.is(ModItems.BLACK_RACING_BRIEF) || stack.is(ModItems.LIFE_SAVER_RACING_BRIEF);
        
        if (isFundoshi) {
            tooltipComponents.accept(net.minecraft.network.chat.Component.translatable("tooltip.swimcraft.fundoshi_desc").withStyle(net.minecraft.ChatFormatting.GOLD));
        }
        
        tooltipComponents.accept(net.minecraft.network.chat.Component.translatable("tooltip.swimcraft.effects").withStyle(net.minecraft.ChatFormatting.GRAY));
        tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.water_breathing")).withStyle(net.minecraft.ChatFormatting.BLUE));
                             
        if (isFundoshi || isRacingBrief) {
            tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.dolphins_grace")).append(" III").withStyle(net.minecraft.ChatFormatting.BLUE));
            if (isFundoshi) {
                tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.strength")).append(" II").withStyle(net.minecraft.ChatFormatting.BLUE));
            }
        } else {
            tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.dolphins_grace")).append(" II").withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        
        if (stack.is(ModItems.LIFE_SAVER_RACING_BRIEF)) {
            tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.regeneration")).withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        
        if (stack.is(ModItems.BRIEF) || stack.is(ModItems.BLACK_BRIEF) || stack.is(ModItems.BLUE_BRIEF) || stack.is(ModItems.WHITE_BRIEF)) {
            tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.speed")).append(" II").withStyle(net.minecraft.ChatFormatting.BLUE));
        }
        
        if (stack.is(ModItems.BOXER) || stack.is(ModItems.DARK_BOXER) || stack.is(ModItems.LIME_BOXER) || stack.is(ModItems.PURPLE_BOXER) || stack.is(ModItems.WHITE_BOXER)) {
            tooltipComponents.accept(net.minecraft.network.chat.Component.literal(" - ").append(net.minecraft.network.chat.Component.translatable("effect.minecraft.jump_boost")).append(" II").withStyle(net.minecraft.ChatFormatting.BLUE));
        }
    }

    private void applyEffect(net.minecraft.world.entity.player.Player player, net.minecraft.core.Holder<net.minecraft.world.effect.MobEffect> effect, int amplifier) {
        net.minecraft.world.effect.MobEffectInstance current = player.getEffect(effect);
        if (current == null || current.getDuration() <= 80) {
            player.addEffect(new net.minecraft.world.effect.MobEffectInstance(effect, 240, amplifier, true, true, true));
        }
    }
}


