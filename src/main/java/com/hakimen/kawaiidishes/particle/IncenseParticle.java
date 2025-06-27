package com.hakimen.kawaiidishes.particle;

import com.hakimen.kawaiidishes.aromas.DecorativeAroma;
import com.hakimen.kawaiidishes.aromas.PotionAroma;
import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.custom.types.Aroma;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import org.joml.Math;

public class IncenseParticle extends SpriteBillboardParticle {

    private final SpriteProvider sprites;

    public IncenseParticle(ClientWorld clientLevel, double xCoord, double yCoord, double zCoord, SpriteProvider sprites, double vx, double vy, double vz, int color) {
        super(clientLevel, xCoord, yCoord, zCoord);

        this.velocityX = vx;
        this.velocityY = vy;
        this.velocityZ = vz;
        this.scale *= 0.85f;

        this.gravityStrength = -0.01f;
        this.maxAge = 60;

        this.sprites = sprites;
        this.setSpriteForAge(sprites);

        float[] rgb = ColorUtils.getColorsFromHex(color);
        this.red = rgb[0];
        this.green = rgb[1];
        this.blue = rgb[2];
    }


    @Override
    public void tick() {
        super.tick();
        this.setSpriteForAge(this.sprites);
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Environment(EnvType.CLIENT)
    public static class Provider implements ParticleFactory<DefaultParticleType> {
        private final FabricSpriteProvider sprites;

        public Provider(FabricSpriteProvider spriteSet) {
            this.sprites = spriteSet;
        }



        @Nullable
        @Override
        public Particle createParticle(DefaultParticleType simpleParticleType, ClientWorld clientLevel, double x, double y, double z, double dx, double dy, double dz) {
            BlockEntity entity = clientLevel.getBlockEntity(new BlockPos((int)Math.round(x - 0.5f),(int)Math.round(y - 0.45f),(int)Math.round(z - 0.5f)));

            if (entity != null && entity instanceof IncenseBlockEntity incenseBlockEntity) {

                int color = 0xffffff;

                Aroma aroma = incenseBlockEntity.getAromaFromId();
                ItemStack stack = incenseBlockEntity.getInventory().getResource().toStack((int) incenseBlockEntity.getInventory().amount);

                if (aroma instanceof DecorativeAroma) {
                    color = stack.getItem() instanceof DyeItem dyeItem ? dyeItem.getColor().getFireworkColor() : 0;
                } else if (aroma instanceof PotionAroma) {
                    color = PotionUtil.getColor(stack);
                } else {
                    color = aroma.getColor();
                }

                float[] hslColor = ColorUtils.rgbToHsl(color);

                hslColor[2] = Math.clamp(0, 1, hslColor[2] + (clientLevel.random.nextFloat() / 8) * clientLevel.random.nextBetweenExclusive(-1, 2));

                return new IncenseParticle(clientLevel, x, y, z, this.sprites, dx, dy, dz, ColorUtils.hslToRgb(hslColor));
            }
            return new IncenseParticle(clientLevel, x, y, z, this.sprites, dx, dy, dz, 0xffffff);
        }
    }
}
