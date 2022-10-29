package net.superkat.exclamation_point;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class MarkParticle extends SpriteBillboardParticle {
    private final SpriteProvider spriteProvider;

    //The "motion" of the particle effect
    MarkParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.field_28787 = true;
        this.velocityMultiplier = 0.86F;
        this.velocityX *= 0.009999999776482582;
        this.velocityY *= 0.009999999776482582;
        this.velocityZ *= 0.009999999776482582;
        this.velocityY += 0.1;
        this.scale *= 4.5F;
        this.maxAge = 16;
        this.spriteProvider = spriteProvider;
        this.setSpriteForAge(spriteProvider);
    }

    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        super.tick();
        this.setSpriteForAge(this.spriteProvider);
    }

    @Environment(EnvType.CLIENT)
    public record Factory(SpriteProvider sprites) implements ParticleFactory<DefaultParticleType> {
        public Particle createParticle(DefaultParticleType type, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new MarkParticle(world, x, y, z, sprites);
        }
    }
}
