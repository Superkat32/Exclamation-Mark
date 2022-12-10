package net.superkat.exclamation_point.mixin;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.ai.goal.TrackTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.superkat.exclamation_point.ExclamationPoint;
import net.superkat.exclamation_point.ExclamationPointConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.client.MinecraftClient.getInstance;

@Mixin(TrackTargetGoal.class)
public abstract class ExampleMixin {

//	@Shadow public abstract boolean shouldContinue();

	@Shadow @Final
	protected MobEntity mob;

	//New-ish method(still didn't work)

/*
	public boolean isHasTarget() {
		return hasTarget;
	}

	public void setHasTarget(boolean hasTarget) {
		this.hasTarget = hasTarget;
	}

	public boolean hasTarget = false;
	public int timer = 0;
	public int stopTime;
	public int startTime;

	@Inject(method = "stop", at = @At("TAIL"))
	public void stop(CallbackInfo ci) {
		ExclamationPoint.LOGGER.info("stop() has been activated!");
		stopTime = timer;
		ExclamationPoint.LOGGER.info(String.valueOf(stopTime));
		setHasTarget(false);
//		ExclamationPoint.LOGGER.info("(ExampleMixin; stop) " + String.valueOf(hasTarget));
	}

//	@Inject(method = "start", at = @At("TAIL"))
//	public void start(CallbackInfo ci) {
//			startTime = timer;
//	}

	public void tick() {
		timer++;
		if (stopTime + 5 == timer) {
			ExclamationPoint.LOGGER.info(String.valueOf(stopTime));
			ExclamationPoint.LOGGER.info("stop() passed check 1");
			if (!(stopTime == startTime)) {
				ExclamationPoint.LOGGER.info("stop() passed check 2");
				ExclamationPoint.LOGGER.info("Question mark should be shown");
			}
		}
	}

*/

	//Old method(includes old start and old stop)

/*

	public abstract boolean canStart();

	@Shadow
	protected LivingEntity target;
	@Inject(method = "start()V", at = @At("HEAD"))

	public void start(CallbackInfo ci) {
		if(ExclamationPointConfig.modEnabled) {
			ExclamationPoint.LOGGER.info("Start has been activated!");
			//Logs the monster's info that I might need later
			ExclamationPoint.LOGGER.info("Quick overview = " + this.mob);
			ExclamationPoint.LOGGER.info("Mob = " + this.mob.getType());
	//		ExclamationPoint.LOGGER.info("Mob's speed = " + String.valueOf(this.mob.speed));
			ExclamationPoint.LOGGER.info("Mob's world = " + this.mob.world);
	//		if(this.canStart()) {
			//Prevents the particle working on the Vex because it causes the particle to spam for some reason
			if(!(this.mob instanceof VexEntity && this.mob instanceof ZombieEntity && this.mob instanceof DrownedEntity)) {
				//targetAcquired boolean basically means if the particle/sound should play the exclamation mark or the question mark
				//if targetAcquired is true, then show exclamation mark, if false, show question mark
				this.doParticle(this.mob.world, true);
				this.playSound(true);
			} else if(this.mob instanceof VexEntity) {
				ExclamationPoint.LOGGER.info("Not proceeding with start method - Entity is a vex");
			} else if(this.mob instanceof ZombieEntity || this.mob instanceof DrownedEntity) {
				ExclamationPoint.LOGGER.info("Not proceeding with TrackTargetGoal start method.");
			} else {
				ExclamationPoint.LOGGER.info("Not proceeding with start method - None of the if/else statements were met");
			}
		}
//		}
	}

	@Inject(method = "stop()V", at = @At("HEAD"))

	public void stop(CallbackInfo ci) {
		if(ExclamationPointConfig.modEnabled) {
			ExclamationPoint.LOGGER.info("Stop has been activated!");
			if(!(this.mob instanceof VexEntity && this.mob instanceof ZombieEntity && this.mob instanceof DrownedEntity)) {
				//targetAcquired boolean basically means if the particle/sound should play the exclamation mark or the question mark
				//if targetAcquired is true, then show exclamation mark, if false, show question mark
				this.doParticle(this.mob.world, false);
				this.playSound(false);
			} else if(this.mob instanceof VexEntity) {
				ExclamationPoint.LOGGER.info("Not proceeding with stop method - Entity is a vex");
			} else if(this.mob instanceof ZombieEntity || this.mob instanceof DrownedEntity) {
				ExclamationPoint.LOGGER.info("Not proceeding with TrackTargetGoal stop method.");
			} else {
				ExclamationPoint.LOGGER.info("Not proceeding with stop method - None of the if/else statements were met");
			}
		}
	}

 */

//
	public void doParticle(World world, boolean targetAcquired) {
		ExclamationPoint.LOGGER.info("doParticle has been activated!");
        if (world instanceof ServerWorld) {
			if(targetAcquired) {
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
					ExclamationPoint.LOGGER.info("Particle has been activated! (Target Acquired)");
					((ServerWorld) world).spawnParticles(ExclamationPoint.MARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
				}
//			} else if(!targetAcquired) {
//                if(ExclamationPointConfig.showQuestionMark) {
//					ExclamationPoint.LOGGER.info("Particle has been activated! (!Target Acquired)");
//					((ServerWorld) world).spawnParticles(ExclamationPoint.QMARK, mob.getX(), mob.getEyeY() + 1, mob.getZ(), 1, 0.0, 0, 0.0, 0.1);
//                }
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
//		} else if(!targetAcquired) {
//            if(ExclamationPointConfig.playQuestionMarkSound) {
//                //grabs the float from the config, then uses it as the volume
//                float markVolumeF = ExclamationPointConfig.questionMarkSlider;
//                getInstance().getSoundManager().play(PositionedSoundInstance.master(ExclamationPoint.QUESTION_MARK_SOUND_EVENT, 1.0F, markVolumeF));
//            }
        } else {
            ExclamationPoint.LOGGER.info("Not proceeding with doParticle - targetAcquired wasn't true or false");
        }
	}
}
