package net.superkat.exclamation_point.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.exclamation_point.ExclamationPoint;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TrackTargetGoal.class)
public abstract class ExampleMixin {

//	@Shadow public abstract boolean shouldContinue();

	@Shadow @Final
	protected MobEntity mob;

	public abstract boolean canStart();

	@Shadow
	protected LivingEntity target;
	@Inject(method = "start()V", at = @At("HEAD"))

	public void start(CallbackInfo ci) {
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.speed));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.world));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.getType()));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob));
//		if(this.canStart()) {
		ExclamationPoint.LOGGER.info("SHOULD CONTINUE HAS BEEN ACTIVATED!");
		this.doParticle(this.mob.world);
//		}
	}

	private void doParticle(World world) {
		ExclamationPoint.LOGGER.info("doParticle has been activated!");
        if (world instanceof ServerWorld) {
			ExclamationPoint.LOGGER.info("Particle has been activated!");
			((ServerWorld) world).spawnParticles(ParticleTypes.DAMAGE_INDICATOR, mob.getX(), mob.getY(), mob.getZ(), 1, 0.1, 0, 0.1, 0.2);
            world.addParticle(ParticleTypes.SWEEP_ATTACK, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 0.1, 0, 0.2);
        }
//		((ServerWorld) this.target.getWorld()).spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 5, 0.1, 0.0, 0.1, 0.2);
	}
}
