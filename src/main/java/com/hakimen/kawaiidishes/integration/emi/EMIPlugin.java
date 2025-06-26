package com.hakimen.kawaiidishes.integration.emi;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.emi.plugins.BlenderRecipePlugin;
import com.hakimen.kawaiidishes.integration.emi.plugins.CoffeeMachineRecipePlugin;
import com.hakimen.kawaiidishes.integration.emi.plugins.GenericOverlayableRecipePlugin;
import com.hakimen.kawaiidishes.integration.emi.plugins.IceCreamMakerRecipePlugin;
import com.hakimen.kawaiidishes.item.IDyeableItem;
import com.hakimen.kawaiidishes.item.IFourColorDyeableItem;
import com.hakimen.kawaiidishes.item.DecorationItem;
import com.hakimen.kawaiidishes.item.armor.EarsArmorItem;
import com.hakimen.kawaiidishes.item.armor.MaidDressArmorItem;
import com.hakimen.kawaiidishes.item.armor.TailArmorItem;
import com.hakimen.kawaiidishes.item.armor.ThighHighsArmorItem;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.registry.ItemTabRegister;
import com.hakimen.kawaiidishes.registry.ThighHighsDecorationRegister;
import com.hakimen.kawaiidishes.utils.HeadBandsWithEarsUtils;
import com.hakimen.kawaiidishes.utils.MaidDressesWithTailUtils;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import java.util.List;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeManager;

public class EMIPlugin implements EmiPlugin {
    public static final ResourceLocation COFFEE_MACHINE_TEXTURE = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/coffee_machine_gui.png");
    public static final ResourceLocation COFFEE_MACHINE_PROCESSING_ICON = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/icons/coffee_machine.png");
    public static final EmiStack COFFEE_MACHINE_WORKSTATION = EmiStack.of(ItemRegister.COFFEE_MACHINE.get());
    public static final EmiRecipeCategory COFFEE_MACHINE_CATEGORY = new EmiRecipeCategory(new ResourceLocation(KawaiiDishes.MODID, "coffee_machine"), COFFEE_MACHINE_WORKSTATION, new EmiTexture(COFFEE_MACHINE_PROCESSING_ICON, 0, 0, 16, 16, 16, 16, 16, 16));


    public static final ResourceLocation BLENDER_TEXTURE = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/blender_gui.png");
    public static final ResourceLocation BLENDER_PROCESSING_ICON = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/icons/blender.png");
    public static final EmiStack BLENDER_WORKSTATION = EmiStack.of(ItemRegister.BLENDER.get());
    public static final EmiRecipeCategory BLENDER_CATEGORY = new EmiRecipeCategory(new ResourceLocation(KawaiiDishes.MODID, "blender"), BLENDER_WORKSTATION, new EmiTexture(BLENDER_PROCESSING_ICON, 0, 0, 16, 16, 16, 16, 16, 16));


    public static final ResourceLocation ICE_CREAM_MAKER_TEXTURE = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/ice_cream_maker_gui.png");
    public static final ResourceLocation ICE_CREAM_MAKER_PROCESSING_ICON = new ResourceLocation(KawaiiDishes.MODID, "textures/integration/emi/icons/ice_cream_maker.png");
    public static final EmiStack ICE_CREAM_MAKER_WORKSTATION = EmiStack.of(ItemRegister.ICE_CREAM_MAKER.get());
    public static final EmiRecipeCategory ICE_CREAM_MAKER_CATEGORY = new EmiRecipeCategory(new ResourceLocation(KawaiiDishes.MODID, "ice_cream_maker"), ICE_CREAM_MAKER_WORKSTATION, new EmiTexture(ICE_CREAM_MAKER_PROCESSING_ICON, 0, 0, 16, 16, 16, 16, 16, 16));


    @Override
    public void register(EmiRegistry registry) {

        registry.addCategory(COFFEE_MACHINE_CATEGORY);
        registry.addWorkstation(COFFEE_MACHINE_CATEGORY, COFFEE_MACHINE_WORKSTATION);

        registry.addCategory(BLENDER_CATEGORY);
        registry.addWorkstation(BLENDER_CATEGORY, BLENDER_WORKSTATION);

        registry.addCategory(ICE_CREAM_MAKER_CATEGORY);
        registry.addWorkstation(ICE_CREAM_MAKER_CATEGORY, BLENDER_WORKSTATION);


        RecipeManager manager = registry.getRecipeManager();
        for (CoffeeMachineRecipe recipe : manager.getAllRecipesFor(CoffeeMachineRecipe.Type.INSTANCE)) {
            registry.addRecipe(new CoffeeMachineRecipePlugin(recipe));
        }

        for (BlenderRecipe recipe : manager.getAllRecipesFor(BlenderRecipe.Type.INSTANCE)) {
            registry.addRecipe(new BlenderRecipePlugin(recipe));
        }

        for (IceCreamMakerRecipe recipe : manager.getAllRecipesFor(IceCreamMakerRecipe.Type.INSTANCE)) {
            registry.addRecipe(new IceCreamMakerRecipePlugin(recipe));
        }

        for (ItemStack stack : ItemTabRegister.CLOTHING_TAB.get().getDisplayItems()) {
            if (stack.getItem() instanceof IDyeableItem) {
                if (stack.getItem() instanceof TailArmorItem item) {
                    addDoubleOverlayable(registry, ItemRegister.MAID_DRESS.get().getDefaultInstance(),
                            item.getDefaultInstance(),
                            MaidDressesWithTailUtils.getTailedDressItems().get(item.getTailType()).get().getDefaultInstance()
                    );
                } else if (stack.getItem() instanceof EarsArmorItem item) {
                    addDoubleOverlayable(registry, ItemRegister.MAID_DRESS.get().getDefaultInstance(),
                            item.getDefaultInstance(),
                            HeadBandsWithEarsUtils.getEaredHeadBandsItems().get(item.getEarsType()).get().getDefaultInstance()
                    );
                }
                else if (stack.getItem() instanceof MaidDressArmorItem) {
                    addItemWithOverlay(registry, stack, Ingredient.of(ItemRegister.APRON.get()), stack);
                } else if(!(stack.getItem() instanceof ThighHighsArmorItem)){
                    addItemWithOverlay(registry, stack, stack);
                }
             } else if (stack.getItem() instanceof DecorationItem item) {
                addThighHighItemWithOverlay(registry, ItemRegister.THIGH_HIGHS.get().getDefaultInstance(),
                        ThighHighsDecorationRegister.DECORATIONS.getRegistry()
                                .getId(
                                        ThighHighsDecorationRegister.DECORATIONS.getRegistry().get(
                                                item.getThighHighDecorationLocation()
                                        )
                                ),
                        Ingredient.of(stack),
                        ItemRegister.THIGH_HIGHS.get().getDefaultInstance()
                );
            }
        }
    }

    public void addItemWithOverlay(EmiRegistry registry, ItemStack base, ItemStack target) {
        var stacc = target.copy();
        stacc.getOrCreateTag().putBoolean("HasOverlay", true);
        stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        ListTag list = new ListTag();

        list.add(StringTag.valueOf("\"This is an example!\""));

        CompoundTag compound = new CompoundTag();
        compound.put("Lore", list);

        stacc.getOrCreateTag().put("display", compound);

        registry.addRecipe(new GenericOverlayableRecipePlugin(base, stacc, new ResourceLocation(KawaiiDishes.MODID, ItemRegister.ITEM.getRegistry().getKey(target.getItem()).getPath() + "_overlayed")));
    }

    public void addItemWithOverlay(EmiRegistry registry, ItemStack base, Ingredient overlayItem, ItemStack target) {
        var stacc = target.copy();
        stacc.getOrCreateTag().putBoolean("HasOverlay", true);
        stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        ListTag list = new ListTag();

        list.add(StringTag.valueOf("\"This is an example!\""));

        CompoundTag compound = new CompoundTag();
        compound.put("Lore", list);

        stacc.getOrCreateTag().put("display", compound);

        registry.addRecipe(new GenericOverlayableRecipePlugin(base, overlayItem, stacc, new ResourceLocation(KawaiiDishes.MODID, ItemRegister.ITEM.getRegistry().getKey(target.getItem()).getPath() + "_overlayed")));
    }

    public void addDoubleOverlayable(EmiRegistry registry, ItemStack base, ItemStack overlayItem, ItemStack target) {
        var stacc = target.copy();
        stacc.getOrCreateTag().putBoolean("HasPrimaryOverlay", true);
        stacc.getOrCreateTag().putBoolean("HasSecondaryOverlay", true);
        stacc = IFourColorDyeableItem.dyePrimaryBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));
        stacc = IFourColorDyeableItem.dyeSecondaryBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        ListTag list = new ListTag();

        list.add(StringTag.valueOf("\"This is an example!\""));

        CompoundTag compound = new CompoundTag();
        compound.put("Lore", list);

        stacc.getOrCreateTag().put("display", compound);


        var stacc2 = overlayItem.copy();
        stacc2.getOrCreateTag().putBoolean("HasOverlay", true);
        stacc2 = IDyeableItem.dyeBase(stacc2, List.of(DyeItem.byColor(DyeColor.BLACK)));

        base.getOrCreateTag().putBoolean("HasOverlay", true);

        registry.addRecipe(new GenericOverlayableRecipePlugin(base, Ingredient.of(stacc2), stacc, new ResourceLocation(KawaiiDishes.MODID, ItemRegister.ITEM.getRegistry().getKey(target.getItem()).getPath() + "_overlayed")));
    }

    public void addThighHighItemWithOverlay(EmiRegistry registry, ItemStack base, int id, Ingredient overlayItem, ItemStack target) {
        var stacc = target.copy();
        stacc.getOrCreateTag().putInt("Decoration", id);
        stacc = IDyeableItem.dyeBase(stacc, List.of(DyeItem.byColor(DyeColor.BLACK)));

        ListTag list = new ListTag();

        list.add(StringTag.valueOf("\"This is an example!\""));

        CompoundTag compound = new CompoundTag();
        compound.put("Lore", list);

        stacc.getOrCreateTag().put("display", compound);

        registry.addRecipe(new GenericOverlayableRecipePlugin(base, overlayItem, stacc, new ResourceLocation(KawaiiDishes.MODID, ItemRegister.ITEM.getRegistry().getKey(target.getItem()).getPath() + "_overlayed_" + id)));
    }
}
