package com.hakimen.kawaiidishes.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

import java.util.Objects;

public class KawaiiDyeableComponent {

    public static KawaiiDyeable DEFAULT = new KawaiiDyeable(-1,-1,-1,-1);

    public static class KawaiiDyeable {
        final int base;
        final int overlay;
        final int secondaryBase;
        final int secondaryOverlay;
        final boolean hasOverlay;
        final boolean hasSecondaryOverlay;

        public KawaiiDyeable(int base, int overlay, int secondaryBase, int secondaryOverlay) {
            this.base = base;
            this.overlay = overlay;
            this.secondaryBase = secondaryBase;
            this.secondaryOverlay = secondaryOverlay;
            this.hasOverlay = false;
            this.hasSecondaryOverlay = false;
        }

        public KawaiiDyeable(int base, int overlay, int secondaryBase, int secondaryOverlay, boolean hasOverlay, boolean hasSecondaryOverlay) {
            this.base = base;
            this.overlay = overlay;
            this.secondaryBase = secondaryBase;
            this.secondaryOverlay = secondaryOverlay;
            this.hasOverlay = hasOverlay;
            this.hasSecondaryOverlay = hasSecondaryOverlay;
        }

        public int getBase() {
            return base;
        }

        public int getOverlay() {
            return overlay;
        }

        public int getSecondaryBase() {
            return secondaryBase;
        }

        public int getSecondaryOverlay() {
            return secondaryOverlay;
        }


        public boolean isHasOverlay() {
            return hasOverlay;
        }

        public boolean isHasSecondaryOverlay() {
            return hasSecondaryOverlay;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            KawaiiDyeable that = (KawaiiDyeable) o;
            return base == that.base && overlay == that.overlay && secondaryBase == that.secondaryBase && secondaryOverlay == that.secondaryOverlay && hasOverlay == that.hasOverlay && hasSecondaryOverlay == that.hasSecondaryOverlay;
        }

        @Override
        public int hashCode() {
            return Objects.hash(base, overlay, secondaryBase, secondaryOverlay, hasOverlay,hasSecondaryOverlay);
        }
    }

    public static class KawaiiDyeableBuilder {
        int base;
        int overlay;
        int secondaryBase;
        int secondaryOverlay;
        boolean hasOverlay = false;
        boolean hasSecondaryOverlay = false;

        public KawaiiDyeableBuilder() {
        }

        public KawaiiDyeableBuilder(KawaiiDyeable dyeable) {
            this.base = dyeable.base;
            this.overlay = dyeable.overlay;
            this.secondaryBase = dyeable.secondaryBase;
            this.secondaryOverlay = dyeable.secondaryOverlay;
            this.hasOverlay = dyeable.hasOverlay;
            this.hasSecondaryOverlay = dyeable.hasSecondaryOverlay;
        }

        public int getBase() {
            return base;
        }

        public KawaiiDyeableBuilder setBase(int base) {
            this.base = base;
            return this;
        }

        public int getOverlay() {
            return overlay;
        }

        public KawaiiDyeableBuilder setOverlay(int overlay) {
            this.overlay = overlay;
            return this;
        }

        public int getSecondaryBase() {
            return secondaryBase;
        }

        public KawaiiDyeableBuilder setSecondaryBase(int secondaryBase) {
            this.secondaryBase = secondaryBase;
            return this;
        }

        public int getSecondaryOverlay() {
            return secondaryOverlay;
        }

        public KawaiiDyeableBuilder setSecondaryOverlay(int secondaryOverlay) {
            this.secondaryOverlay = secondaryOverlay;
            return this;
        }

        public boolean isHasOverlay() {
            return hasOverlay;
        }

        public KawaiiDyeableBuilder setHasOverlay(boolean hasOverlay) {
            this.hasOverlay = hasOverlay;
            return this;
        }

        public boolean isHasSecondaryOverlay() {
            return hasSecondaryOverlay;
        }

        public KawaiiDyeableBuilder setHasSecondaryOverlay(boolean hasSecondaryOverlay) {
            this.hasSecondaryOverlay = hasSecondaryOverlay;
            return this;
        }

        public KawaiiDyeable build() {
            return new KawaiiDyeable(base, overlay, secondaryBase, secondaryOverlay, hasOverlay,hasSecondaryOverlay);
        }
    }

    public static Codec<KawaiiDyeable> CODEC = RecordCodecBuilder.create(
            kawaiiDyeableInstance -> kawaiiDyeableInstance.group(
                    Codec.INT.fieldOf("base").forGetter(KawaiiDyeable::getBase),
                    Codec.INT.fieldOf("overlay").forGetter(KawaiiDyeable::getOverlay),
                    Codec.INT.fieldOf("secondary_base").forGetter(KawaiiDyeable::getSecondaryBase),
                    Codec.INT.fieldOf("secondary_overlay").forGetter(KawaiiDyeable::getSecondaryOverlay),
                    Codec.BOOL.fieldOf("has_overlay").forGetter(KawaiiDyeable::isHasOverlay),
                    Codec.BOOL.fieldOf("has_secondary_overlay").forGetter(KawaiiDyeable::isHasSecondaryOverlay)
            ).apply(kawaiiDyeableInstance, KawaiiDyeable::new)
    );

    public static StreamCodec<RegistryFriendlyByteBuf, KawaiiDyeable> STREAM_CODEC = new StreamCodec<RegistryFriendlyByteBuf, KawaiiDyeable>() {
        @Override
        public KawaiiDyeable decode(RegistryFriendlyByteBuf pBuffer) {
            KawaiiDyeableBuilder builder = new KawaiiDyeableBuilder();
            builder.base = pBuffer.readInt();
            builder.overlay = pBuffer.readInt();
            builder.secondaryBase = pBuffer.readInt();
            builder.secondaryOverlay = pBuffer.readInt();
            builder.hasOverlay = pBuffer.readBoolean();
            builder.hasSecondaryOverlay = pBuffer.readBoolean();

            return builder.build();
        }

        @Override
        public void encode(RegistryFriendlyByteBuf pBuffer, KawaiiDyeable pValue) {
            pBuffer.writeInt(pValue.base);
            pBuffer.writeInt(pValue.overlay);
            pBuffer.writeInt(pValue.secondaryBase);
            pBuffer.writeInt(pValue.secondaryOverlay);
            pBuffer.writeBoolean(pValue.hasOverlay);
            pBuffer.writeBoolean(pValue.hasSecondaryOverlay);
        }
    };
}
