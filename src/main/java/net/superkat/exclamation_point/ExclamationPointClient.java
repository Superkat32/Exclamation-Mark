package net.superkat.exclamation_point;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

import static net.superkat.exclamation_point.ExclamationPoint.MOD_ID;
import static net.superkat.exclamation_point.MarkParticle.Factory;

public class ExclamationPointClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //Registers the particle effect's texture
        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register((atlasTexture, registry) -> {
            registry.register(new Identifier(MOD_ID, "particle/mark"));
        });

        //Registers the particle effect
        ParticleFactoryRegistry.getInstance().register(ExclamationPoint.MARK, Factory::new);

    }
}
