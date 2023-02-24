package com.hakimen.kawaiidishes.mixin.clientside;

import com.hakimen.kawaiidishes.integration.cc.OverlayUtils;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import dan200.computercraft.ComputerCraft;
import dan200.computercraft.api.turtle.TurtleSide;
import dan200.computercraft.client.render.TileEntityTurtleRenderer;
import dan200.computercraft.shared.computer.core.ComputerFamily;
import dan200.computercraft.shared.turtle.blocks.TileTurtle;
import dan200.computercraft.shared.util.Holiday;
import dan200.computercraft.shared.util.HolidayUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.*;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@Pseudo
@Mixin(value = TileEntityTurtleRenderer.class, remap = false)
public abstract class CCTurtleMixin {


    @Shadow @Final BlockEntityRenderDispatcher renderer;

    @Shadow protected abstract void renderModel(@NotNull PoseStack transform, @NotNull VertexConsumer renderer, int lightmapCoord, int overlayLight, ResourceLocation model, int[] tints);


    @Shadow protected abstract void renderUpgrade(@NotNull PoseStack transform, @NotNull VertexConsumer renderer, int lightmapCoord, int overlayLight, TileTurtle turtle, TurtleSide side, float f);

    @Shadow @Final private static ResourceLocation ELF_OVERLAY_MODEL;

    private static ResourceLocation[] getTurtleOverlayModel(String label, ResourceLocation overlay, boolean christmas)
    {
        if( overlay != null ) return new ResourceLocation[]{overlay};
        if( christmas ) return new ResourceLocation[]{ELF_OVERLAY_MODEL};
        if( label != null){
            var toReturn = new ArrayList<ResourceLocation>();
            label = label.toLowerCase();
            for (String key: OverlayUtils.overlays.keySet()) {
                if(label.contains(key)){
                    toReturn.add(OverlayUtils.overlays.get(key));
                }
            }
            return toReturn.toArray(new ResourceLocation[toReturn.size()]);
        }
        return null;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static ResourceLocation getTurtleModel(ComputerFamily family, boolean coloured)
    {
        switch( family )
        {
            case NORMAL:
            default:
                return coloured ? COLOUR_TURTLE_MODEL : NORMAL_TURTLE_MODEL;
            case ADVANCED:
                return coloured ? COLOUR_TURTLE_MODEL : ADVANCED_TURTLE_MODEL;
        }
    }


    private static final ModelResourceLocation NORMAL_TURTLE_MODEL = new ModelResourceLocation( "computercraft:turtle_normal", "inventory" );
    private static final ModelResourceLocation ADVANCED_TURTLE_MODEL = new ModelResourceLocation( "computercraft:turtle_advanced", "inventory" );
    private static final ResourceLocation COLOUR_TURTLE_MODEL = new ResourceLocation( ComputerCraft.MOD_ID, "block/turtle_colour" );




    /**
     * @author
     * @reason
     */
    @Overwrite
    public void render(@Nonnull TileTurtle turtle, float partialTicks, @Nonnull PoseStack transform, @Nonnull MultiBufferSource buffers, int lightmapCoord, int overlayLight )
    {
        // Render the label
        String label = turtle.createProxy().getLabel();
        HitResult hit = renderer.cameraHitResult;
        if( label != null && hit.getType() == HitResult.Type.BLOCK && turtle.getBlockPos().equals( ((BlockHitResult) hit).getBlockPos() ) )
        {
            Minecraft mc = Minecraft.getInstance();
            Font font = renderer.font;

            transform.pushPose();
            transform.translate( 0.5, 1.2, 0.5 );
            transform.mulPose( mc.getEntityRenderDispatcher().cameraOrientation() );
            transform.scale( -0.025f, -0.025f, 0.025f );

            Matrix4f matrix = transform.last().pose();
            int opacity = (int) (mc.options.getBackgroundOpacity( 0.25f ) * 255) << 24;
            float width = -font.width( label ) / 2.0f;
            font.drawInBatch( label, width, (float) 0, 0x20ffffff, false, matrix, buffers, true, opacity, lightmapCoord );
            font.drawInBatch( label, width, (float) 0, 0xffffffff, false, matrix, buffers, false, 0, lightmapCoord );

            transform.popPose();
        }

        transform.pushPose();

        // Setup the transform.
        Vec3 offset = turtle.getRenderOffset( partialTicks );
        float yaw = turtle.getRenderYaw( partialTicks );
        transform.translate( offset.x, offset.y, offset.z );

        transform.translate( 0.5f, 0.5f, 0.5f );
        transform.mulPose( Vector3f.YP.rotationDegrees( 180.0f - yaw ) );
        if( label != null && (label.equals( "Dinnerbone" ) || label.equals( "Grumm" )) )
        {
            // Flip the model
            transform.scale( 1.0f, -1.0f, 1.0f );
        }
        transform.translate( -0.5f, -0.5f, -0.5f );

        // Render the turtle
        int colour = turtle.getColour();
        ComputerFamily family = turtle.getFamily();
        ResourceLocation overlay = turtle.getOverlay();

        VertexConsumer buffer = buffers.getBuffer( Sheets.translucentCullBlockSheet() );
        renderModel(transform, buffer, lightmapCoord, overlayLight, getTurtleModel(family, colour != -1), colour == -1 ? null : new int[]{colour});

        // Render the overlay
        ResourceLocation[] overlayModels = getTurtleOverlayModel(label,overlay, HolidayUtil.getCurrentHoliday() == Holiday.CHRISTMAS );
        if(overlayModels != null){
            for (ResourceLocation overlayModel:overlayModels) {
                renderModel( transform, buffer, lightmapCoord, overlayLight, overlayModel, null );
            }
        }

        // Render the upgrades
        renderUpgrade( transform, buffer, lightmapCoord, overlayLight, turtle, TurtleSide.LEFT, partialTicks );
        renderUpgrade( transform, buffer, lightmapCoord, overlayLight, turtle, TurtleSide.RIGHT, partialTicks );

        transform.popPose();
    }
}
