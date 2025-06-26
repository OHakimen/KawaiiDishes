package com.hakimen.kawaiidishes.integration.emi.plugins;

import com.hakimen.kawaiidishes.integration.emi.EMIPlugin;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.IceCreamMakerRecipe;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import static com.hakimen.kawaiidishes.integration.emi.EMIPlugin.BLENDER_TEXTURE;
import static com.hakimen.kawaiidishes.integration.emi.EMIPlugin.ICE_CREAM_MAKER_TEXTURE;

public class IceCreamMakerRecipePlugin implements EmiRecipe {

    private final ResourceLocation id;
    private final List<EmiIngredient> input;
    private final EmiStack output;
    private final EmiStack onOutput;
    int snowballs;

    public IceCreamMakerRecipePlugin(IceCreamMakerRecipe recipe) {

        this.id = recipe.getId();
        List<EmiIngredient> ingredients = new ArrayList<>();
        for (ItemStack recipeItem : recipe.getRecipeItems().get(0).getItems()) {
            ingredients.add(EmiIngredient.of(Ingredient.of(recipeItem)));
        }
        this.input = ingredients;

        this.snowballs = recipe.getSnowballs();

        this.onOutput = EmiStack.of(recipe.getItemOnOutput());
        this.output = EmiStack.of(recipe.getOutput());
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMIPlugin.ICE_CREAM_MAKER_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return input;
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(output);
    }

    @Override
    public int getDisplayWidth() {
        return 168;
    }

    @Override
    public int getDisplayHeight() {
        return 80;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        widgets.addTexture(ICE_CREAM_MAKER_TEXTURE,0,0,168,80,0,0,168,80, 168,80);

        if(snowballs > 0){
            widgets.addSlot(EmiStack.of(Items.SNOWBALL.getDefaultInstance(), snowballs), 21, 13);
        }
        for (int i = 0; i < input.size(); i++) {
            widgets.addSlot(input.get(i), 57,13 + (18 * i));
        }

        widgets.addSlot(onOutput, 109, 13);
        widgets.addSlot(output, 109, 49);
    }
}
