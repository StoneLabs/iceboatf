package net.stone_labs.iceboatf.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.vehicle.BoatEntity;
import net.stone_labs.iceboatf.IceBoatF;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BoatEntity.class)
public abstract class BoatEntityMixin
{
    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(CallbackInfo info)
    {
        //noinspection ConstantConditions
        BoatEntity _this = (BoatEntity) (Object) this;

        if (IceBoatF.disableIceBoats && _this.method_7548() >= 0.9)
            //noinspection ConstantConditions
            if (_this.getServer().getTicks() % 3 == 0)
                _this.damage(DamageSource.GENERIC, 0);
    }
}
