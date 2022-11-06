package net.superkat.exclamation_point;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.registry.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

public class ParticleTest {
    public static final Map<DefaultParticleType, ParticleFactoryRegistry.PendingParticleFactory<DefaultParticleType>> FACTORIES = new LinkedHashMap<>();

    public static final DefaultParticleType MARK = add(MarkParticle.Factory::new);

    public static void init() {
        ParticleFactoryRegistry registry = ParticleFactoryRegistry.getInstance();
        FACTORIES.forEach(registry::register);
    }

    private static DefaultParticleType add(ParticleFactoryRegistry.PendingParticleFactory<DefaultParticleType> constructor) {
        var particle = Registry.register(Registry.PARTICLE_TYPE, ExclamationPointClient.id("mark"), FabricParticleTypes.simple());
        FACTORIES.put(particle, constructor);
        return particle;
    }



}
