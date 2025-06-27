package com.hakimen.kawaiidishes.integration.emi.plugins;

import com.hakimen.kawaiidishes.integration.emi.EMIPlugin;
import com.hakimen.kawaiidishes.recipes.BlenderRecipe;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.utils.FluidStack;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.TankWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import static com.hakimen.kawaiidishes.integration.emi.EMIPlugin.BLENDER_TEXTURE;
import static com.hakimen.kawaiidishes.integration.emi.EMIPlugin.COFFEE_MACHINE_TEXTURE;

public class BlenderRecipePlugin implements EmiRecipe {

    private final Identifier id;
    private final List<EmiIngredient> input;

    private final EmiStack output;
    private final EmiStack onOutput;

    public BlenderRecipePlugin(BlenderRecipe recipe) {

        this.id = recipe.getId();
        List<EmiIngredient> ingredients = new ArrayList<>();
        for (ItemStack recipeItem : recipe.getRecipeItems().get(0).getMatchingStacks()) {
            ingredients.add(EmiIngredient.of(Ingredient.ofStacks(recipeItem)));
        }
        this.input = ingredients;

        this.onOutput = EmiStack.of(recipe.getItemOnOutput());
        this.output = EmiStack.of(recipe.getOutput());
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMIPlugin.BLENDER_CATEGORY;
    }

    @Override
    public @Nullable Identifier getId() {
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
        widgets.addTexture(BLENDER_TEXTURE,0,0,168,80,0,0,168,80, 168,80);


        for (int i = 0; i < input.size(); i++) {
            widgets.addSlot(input.get(i), 48,11 + (18 * i));
        }

        widgets.addSlot(onOutput, 100, 11);
        widgets.addSlot(output, 100, 47);
    }
}
