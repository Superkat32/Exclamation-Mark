package net.superkat.camera_raindrops.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.superkat.camera_raindrops.CameraRaindrops;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public abstract class ExampleMixin {

	private static final Identifier BONK = new Identifier("camera_raindrops:textures/camera_raindrops.png");
	private static final Identifier TEST = new Identifier("textures/misc/powder_snow_outline.png");
	private MinecraftClient client;

	@Shadow protected abstract void renderOverlay(Identifier texture, float opacity);

	@Shadow protected abstract void renderSpyglassOverlay(float scale);

	boolean sendMixinMessage = true;

	@Inject(at = @At("HEAD"), method = "render")
	public void renderOverlay(CallbackInfo info) {
		if (!this.client.options.getPerspective().isFirstPerson()) {
//				this.renderOverlay(BONK, 1.0F);
				this.renderOverlay(BONK, 1.0F);
				if (sendMixinMessage) {
					CameraRaindrops.LOGGER.info("This line is printed by an example mod mixin!");
					sendMixinMessage = false;
				}
		}
	}
}
