package com.hakimen.kawaiidishes.particle;

import com.hakimen.kawaiidishes.block_entities.IncenseBlockEntity;
import com.hakimen.kawaiidishes.utils.ColorUtils;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.ColorResolver;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import org.jline.utils.Colors;
import org.joml.Math;
import org.joml.Vector3f;
import org.lwjgl.system.MathUtil;

import java.awt.*;
import java.awt.color.ColorSpace;

public class IncenseParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    public IncenseParticle(ClientLevel clientLevel, double xCoord, double yCoord, double zCoord, SpriteSet sprites, double vx, double vy, double vz, int color) {
        super(clientLevel, xCoord, yCoord, zCoord);

        this.xd = vx;
        this.yd = vy;
        this.zd = vz;
        this.quadSize *= 0.85f;

        this.gravity = -0.01f;
        this.lifetime = 60;

        this.sprites = sprites;
        this.setSpriteFromAge(sprites);

        float[] rgb = ColorUtils.getColorsFromHex(color);
        this.rCol = rgb[0];
        this.gCol = rgb[1];
        this.bCol = rgb[2];
    }


    @Override
    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet spriteSet) {
            this.sprites = spriteSet;
        }


        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double x, double y, double z, double dx, double dy, double dz) {
            BlockEntity entity = clientLevel.getBlockEntity(new BlockPos((int)Math.round(x - 0.5f),(int)Math.round(y - 0.45f),(int)Math.round(z - 0.5f)));

            if (entity != null && entity instanceof IncenseBlockEntity incenseBlockEntity) {

                int color = 0xffffff;


                ItemStack stack = incenseBlockEntity.getInventory().getStackInSlot(0);
                switch (incenseBlockEntity.getAroma()) {
                    case DecorativeAroma -> {
                        color = DyeColor.getColor(stack).getFireworkColor();
                    }
                    case PotionAroma -> {
                        color = PotionUtils.getColor(stack);
                    }
                    default -> {
                        color = incenseBlockEntity.getAroma().color;
                    }

                }


                float[] hslColor = ColorUtils.rgbToHsl(color);

                hslColor[2] = Math.clamp(0, 1, hslColor[2] + (clientLevel.random.nextFloat() / 8) * clientLevel.random.nextInt(-1, 2));

                return new IncenseParticle(clientLevel, x, y, z, this.sprites, dx, dy, dz, ColorUtils.hslToRgb(hslColor));
            }
            return new IncenseParticle(clientLevel, x, y, z, this.sprites, dx, dy, dz, 0xffffff);
        }
    }
}
