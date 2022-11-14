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

	public boolean hasTarget = false;

	@Shadow @Final
	protected MobEntity mob;

	public abstract boolean canStart();

	@Shadow
	protected LivingEntity target;
	@Inject(method = "start()V", at = @At("HEAD"))

	public void start(CallbackInfo ci) {
		ExclamationPoint.LOGGER.info("Start has been activated!");
		//Logs the monster's info that I might need later
		ExclamationPoint.LOGGER.info("Quick overview = " + String.valueOf(this.mob));
		ExclamationPoint.LOGGER.info("Mob = " + String.valueOf(this.mob.getType()));
		ExclamationPoint.LOGGER.info("Mob's speed = " + String.valueOf(this.mob.speed));
		ExclamationPoint.LOGGER.info("Mob's world = " + String.valueOf(this.mob.world));
//		if(this.canStart()) {
		//Prevents the particle working on the Vex because it causes the particle to spam for some reason
		if(!(this.mob instanceof VexEntity)) {
			//targetAcquired boolean basically means if the particle/sound should play the exclamation mark or the question mark
			//if targetAcquired is true, then show exclamation mark, if false, show question mark
			this.doParticle(this.mob.world, true);
			this.playSound(true);
			hasTarget = true;
		} else if(this.mob instanceof VexEntity) {
			ExclamationPoint.LOGGER.info("Not proceeding with start method - Entity is a vex");
		} else {
            ExclamationPoint.LOGGER.info("Not proceeding with start method - None of the if/else statements were met");
        }
//		}
	}

	@Inject(method = "stop()V", at = @At("HEAD"))

	public void stop(CallbackInfo ci) {
		ExclamationPoint.LOGGER.info("Stop has been activated!");
        if(!(this.mob instanceof VexEntity)) {
            //targetAcquired boolean basically means if the particle/sound should play the exclamation mark or the question mark
            //if targetAcquired is true, then show exclamation mark, if false, show question mark
            this.doParticle(this.mob.world, false);
            this.playSound(false);
			hasTarget = false;
        } else if(this.mob instanceof VexEntity) {
            ExclamationPoint.LOGGER.info("Not proceeding with stop method - Entity is a vex");
        } else {
            ExclamationPoint.LOGGER.info("Not proceeding with stop method - None of the if/else statements were met");
        }
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
				if(ExclamationPointConfig.showExclamationMark) {
					if(hasTarget) {
						((ServerWorld) world).spawnParticles(ExclamationPoint.MARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
					}
				}
			} else if(!targetAcquired) {
                if(ExclamationPointConfig.showQuestionMark) {
					if (!hasTarget) {
						((ServerWorld) world).spawnParticles(ExclamationPoint.QMARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
					}
                }
            } else {
                ExclamationPoint.LOGGER.info("Not proceeding with doParticle - targetAcquired wasn't true or false");
            }
        }
	}

	public void playSound(boolean targetAcquired) {
		//plays the sound
		if (targetAcquired) {
			if(ExclamationPointConfig.playExclamationMarkSound) {
				//grabs the float from the config, then uses it as the volume
				float markVolumeF = ExclamationPointConfig.exclamationMarkVolume;
				getInstance().getSoundManager().play(PositionedSoundInstance.master(ExclamationPoint.MARK_SOUND_EVENT, 1.0F, markVolumeF));
			}
		} else if(!targetAcquired) {
            if(ExclamationPointConfig.playQuestionMarkSound) {
                //grabs the float from the config, then uses it as the volume
                float markVolumeF = ExclamationPointConfig.questionMarkSlider;
                getInstance().getSoundManager().play(PositionedSoundInstance.master(ExclamationPoint.QUESTION_MARK_SOUND_EVENT, 1.0F, markVolumeF));
            }
        } else {
            ExclamationPoint.LOGGER.info("Not proceeding with doParticle - targetAcquired wasn't true or false");
        }
	}

}
