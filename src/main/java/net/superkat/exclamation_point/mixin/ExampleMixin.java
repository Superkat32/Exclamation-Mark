package net.superkat.exclamation_point.mixin;

import net.minecraft.entity.ai.goal.AttackGoal;
import net.superkat.exclamation_point.ExclamationPoint;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AttackGoal.class)
public abstract class ExampleMixin {

	@Shadow public abstract boolean shouldContinue();

	boolean showMark = false;

	public void canStart(CallbackInfo info) {
		if(this.shouldContinue()) {
			ExclamationPoint.LOGGER.info("SHOULD CONTINUE HAS BEEN ACTIVATED!");
			showMark = true;
		}
	}
}
