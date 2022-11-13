package net.superkat.exclamation_point;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExclamationPoint implements ModInitializer {
	public static final String MOD_ID = "exclamation_point";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//Sound registry stuff

	public static final Identifier MARK_SOUND_ID = new Identifier("exclamation_point:mark_sound");
	public static SoundEvent MARK_SOUND_EVENT = new SoundEvent(MARK_SOUND_ID);
	public static final Identifier QUESTION_MARK_SOUND_ID = new Identifier("exclamation_point:qmark_sound");
	public static SoundEvent QUESTION_MARK_SOUND_EVENT = new SoundEvent(QUESTION_MARK_SOUND_ID);

	//Particle registry stuff
	public static final DefaultParticleType MARK = FabricParticleTypes.simple(true);
	public static final DefaultParticleType QMARK = FabricParticleTypes.simple(true);

	@Override
	public void onInitialize() {
		//Loads config
		MidnightConfig.init("exclamation_point", ExclamationPointConfig.class);

		//Registers sounds
		Registry.register(Registry.SOUND_EVENT, ExclamationPoint.MARK_SOUND_ID, MARK_SOUND_EVENT);
		Registry.register(Registry.SOUND_EVENT, ExclamationPoint.QUESTION_MARK_SOUND_ID, QUESTION_MARK_SOUND_EVENT);
		//Registers particles
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "mark"), MARK);
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "qmark"), QMARK);
	}
}
