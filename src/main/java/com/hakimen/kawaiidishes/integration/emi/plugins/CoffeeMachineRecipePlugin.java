package com.hakimen.kawaiidishes.integration.emi.plugins;

import com.hakimen.kawaiidishes.KawaiiDishes;
import com.hakimen.kawaiidishes.integration.emi.EMIPlugin;
import com.hakimen.kawaiidishes.recipes.CoffeeMachineRecipe;
import com.hakimen.kawaiidishes.registry.ItemRegister;
import com.hakimen.kawaiidishes.utils.FluidStack;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.SlotWidget;
import dev.emi.emi.api.widget.TankWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;

import static com.hakimen.kawaiidishes.integration.emi.EMIPlugin.COFFEE_MACHINE_TEXTURE;

public class CoffeeMachineRecipePlugin implements EmiRecipe {


    private final Identifier id;
    private final List<EmiIngredient> input;

    private final EmiStack output;
    private final EmiStack onOutput;
    private final EmiStack waterNeeded;

    public CoffeeMachineRecipePlugin(CoffeeMachineRecipe recipe) {


        this.id = recipe.getId();
        List<EmiIngredient> ingredients = new ArrayList<>();

        for (ItemStack recipeItem : recipe.getRecipeItems().get(0).getMatchingStacks()) {
            ingredients.add(EmiIngredient.of(Ingredient.ofStacks(recipeItem)));
        }
        this.input = ingredients;

        this.onOutput = EmiStack.of(recipe.getItemOnOutput());
        this.waterNeeded = EmiStack.of(Fluids.WATER, FluidStack.convertMbToDroplets(recipe.getWaterNeeded()));
        this.output = EmiStack.of(recipe.getOutput());
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EMIPlugin.COFFEE_MACHINE_CATEGORY;
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
        widgets.addTexture(COFFEE_MACHINE_TEXTURE,0,0,168,80,0,0,168,80, 168,80);


        widgets.add(new TankWidget(waterNeeded,44,13,14,54, (int)FluidStack.convertMbToDroplets(4000))
                .drawBack(false));

        for (int i = 0; i < input.size(); i++) {
            widgets.addSlot(input.get(i), 75,13 + (18 * i));
        }

        widgets.addSlot(onOutput, 129, 13);
        widgets.addSlot(output, 129, 49);
    }
}
