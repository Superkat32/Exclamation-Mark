package net.superkat.exclamation_point;

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

	public static final Identifier MY_SOUND_ID = new Identifier("exclamation_point:my_sound");
	public static SoundEvent MY_SOUND_EVENT = new SoundEvent(MY_SOUND_ID);
	public static final DefaultParticleType MARK = FabricParticleTypes.simple(true);

	@Override
	public void onInitialize() {
		Registry.register(Registry.SOUND_EVENT, ExclamationPoint.MY_SOUND_ID, MY_SOUND_EVENT);
		Registry.register(Registry.PARTICLE_TYPE, new Identifier(MOD_ID, "mark"), MARK);
	}
}
