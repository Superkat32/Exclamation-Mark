package net.superkat.exclamation_point.mixin;

import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.superkat.exclamation_point.ExclamationPoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ActiveTargetGoal.class)
public abstract class ActiveTargetGoalMixin extends ExampleMixin {

    @Inject(method = "start", at = @At("TAIL"))
    public void start(CallbackInfo ci) {
        ExclamationPoint.LOGGER.info("start() has been activated!");
        ExclamationPoint.LOGGER.info("Exclamation mark should be shown");
        this.doParticle(this.mob.world, true);
        this.playSound(true);
//        startTime = timer;
//        ExclamationPoint.LOGGER.info("(ActivateTargetGoalMixin; start) " + String.valueOf(hasTarget));
//        setHasTarget(true);
//        this.doParticle(this.mob.world, true);
    }
}
