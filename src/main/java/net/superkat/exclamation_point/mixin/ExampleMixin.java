package net.superkat.exclamation_point.mixin;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.exclamation_point.ExclamationPoint;
import net.superkat.exclamation_point.ExclamationPointConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.minecraft.client.MinecraftClient.getInstance;

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
		ExclamationPoint.LOGGER.info("Start has been activated!");
		//Logs the monster's info that I might need later
		ExclamationPoint.LOGGER.info("Mob = " + String.valueOf(this.mob.getType()));
		ExclamationPoint.LOGGER.info("Mob's speed = " + String.valueOf(this.mob.speed));
		ExclamationPoint.LOGGER.info("Mob's target = " + String.valueOf(this.mob.distanceTo(target)));
		ExclamationPoint.LOGGER.info("Mob's world = " + String.valueOf(this.mob.world));
		ExclamationPoint.LOGGER.info("Extra info = " + String.valueOf(this.mob));
//		if(this.canStart()) {
		//Prevents the particle working on the Vex because it causes the particle to spam for some reason
		if(!(this.mob instanceof VexEntity)) {
			//targetAcquired boolean basically means if the particle/sound should play the exclamation mark or the question mark
			//if targetAcquired is true, then show exclamation mark, if false, show question mark
			this.doParticle(this.mob.world, true);
			this.playSound(true);
		} else {
//			if(mob.distanceTo(target) > 16) {
//				ExclamationPoint.LOGGER.info("is greater than 16");
//			}
			ExclamationPoint.LOGGER.info("Not proceeding with method. Entity is a vex");
		}
//		}
	}

	private void doParticle(World world, boolean targetAcquired) {
		ExclamationPoint.LOGGER.info("doParticle has been activated!");
        if (world instanceof ServerWorld) {
			if(targetAcquired) {
				ExclamationPoint.LOGGER.info("Particle has been activated! (Target Acquired)");
	//			((ServerWorld) world).spawnParticles(ParticleEffect particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed)
				//ParticleEffect - The type of particle you wish to display
				//double x, double y, double z - The position that the particle will be displayed
				//int count - The amount of particles you want to display
				//double deltaX, double deltaY, double deltaZ - The random movement of the particle
				//Changing deltaX will make the particle sometimes random move in the X direction the amount of the number inputted. Same for Y and Z
				//double speed - The speed that the particle will travel at. I believe it can be affected by deltaX, deltaY, and deltaZ
				//FIXME - Shulkers are a little bit glitchly
				//FIXME - Endermen particle is a little bit too low
				//FIXME - Vex particles do not work whatsoever
				if(ExclamationPointConfig.showMark) {
					((ServerWorld) world).spawnParticles(ExclamationPoint.MARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
				}
			}
        }
	}

	public void playSound(boolean targetAcquired) {
		if (targetAcquired) {
			//plays the sound
			if(ExclamationPointConfig.playMarkSound) {
				//grabs the float from the config, then uses it as the volume
				float markVolumeF = ExclamationPointConfig.markSlider;
				getInstance().getSoundManager().play(PositionedSoundInstance.master(ExclamationPoint.MARK_SOUND_EVENT, 1.0F, markVolumeF));
			}
		}
	}

}
