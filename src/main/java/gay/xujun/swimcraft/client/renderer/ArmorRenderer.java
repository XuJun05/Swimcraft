package gay.xujun.swimcraft.client.renderer;

import gay.xujun.swimcraft.item.ArmorItem;
import com.geckolib.renderer.GeoArmorRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

import java.util.List;
import net.minecraft.world.entity.EquipmentSlot;

public class ArmorRenderer extends GeoArmorRenderer<ArmorItem, HumanoidRenderState> {
    public ArmorRenderer(ArmorItem item) {
        super(item);
    }

    @Override
    public List<ArmorSegment> getSegmentsForSlot(HumanoidRenderState state, EquipmentSlot slot) {
        if (slot == EquipmentSlot.LEGS) {
            return List.of(
                    ArmorSegment.LEFT_LEG,
                    ArmorSegment.RIGHT_LEG,
                    ArmorSegment.CHEST
            );
        }
        return super.getSegmentsForSlot(state, slot);
    }
}

