package net.superkat.exclamation_point.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
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
		//Logs the monster's info that I might need later
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.speed));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.world));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob.getType()));
		ExclamationPoint.LOGGER.info(String.valueOf(this.mob));
//		if(this.canStart()) {
		ExclamationPoint.LOGGER.info("Start has been activated!");
		if(!(this.mob instanceof VexEntity)) {
			this.doParticle(this.mob.world);
		} else {
			ExclamationPoint.LOGGER.info("Not proceeding with method. Entity is a vex");
		}
		//Calls the method "doParticle", which will help the game display the particle.
//		}
	}

	private void doParticle(World world) {
		ExclamationPoint.LOGGER.info("doParticle has been activated!");
        if (world instanceof ServerWorld) {
			ExclamationPoint.LOGGER.info("Particle has been activated!");
//			((ServerWorld) world).spawnParticles(ParticleEffect particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed)
			//ParticleEffect - The type of particle you wish to display
			//double x, double y, double z - The position that the particle will be displayed
			//int count - The amount of particles you want to display
			//double deltaX, double deltaY, double deltaZ - The random movement of the particle.
			//Changing deltaX will make the particle sometimes random move in the X direction the amount of the number inputted. Same for Y and Z.
			//double speed - The speed that the particle will travel at. I believe it can be affected by deltaX, deltaY, and deltaZ
			//FIXME - Shulkers are a little bit glitchly
			//FIXME - Endermen particle is a little bit too low
			//FIXME - Vex particles do not work whatsoever
			((ServerWorld) world).spawnParticles(ExclamationPoint.MARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
//            world.addParticle(ParticleTypes.ELECTRIC_SPARK, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 0.1, 0, 0.2);
        }
//		((ServerWorld) this.target.getWorld()).spawnParticles(ParticleTypes.ELECTRIC_SPARK, this.mob.getX(), this.mob.getY(), this.mob.getZ(), 5, 0.1, 0.0, 0.1, 0.2);
	}
}
