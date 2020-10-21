package com.thoriuslight.professionsmod.client.gui.screen.inventory;

import com.thoriuslight.professionsmod.inventory.container.SmithCraftingContainer;

import net.minecraft.client.gui.recipebook.IRecipeShownListener;
import net.minecraft.client.gui.recipebook.RecipeBookGui;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class SmithCraftingScreen extends ContainerScreen<SmithCraftingContainer> implements IRecipeShownListener{
	private static final ResourceLocation CRAFTING_TABLE_GUI_TEXTURES = new ResourceLocation("textures/gui/container/crafting_table.png");
	private static final ResourceLocation RECIPE_BUTTON_TEXTURE = new ResourceLocation("textures/gui/recipe_button.png");
	private final RecipeBookGui recipeBookGui = new RecipeBookGui();
	private boolean widthTooNarrow;

	public SmithCraftingScreen(SmithCraftingContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);
	}
	
	@Override
	protected void init() {
		super.init();
		this.widthTooNarrow = this.width < 379;
		this.recipeBookGui.init(this.width, this.height, this.minecraft, this.widthTooNarrow, this.container);
		this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
		      this.children.add(this.recipeBookGui);
		      this.setFocusedDefault(this.recipeBookGui);
		      this.addButton(new ImageButton(this.guiLeft + 5, this.height / 2 - 49, 20, 18, 0, 0, 19, RECIPE_BUTTON_TEXTURE, (p_214076_1_) -> {
		         this.recipeBookGui.initSearchBar(this.widthTooNarrow);
		         this.recipeBookGui.toggleVisibility();
		         this.guiLeft = this.recipeBookGui.updateScreenPosition(this.widthTooNarrow, this.width, this.xSize);
		         ((ImageButton)p_214076_1_).setPosition(this.guiLeft + 5, this.height / 2 - 49);
		      }));
		   }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void recipesUpdated() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public RecipeBookGui getRecipeGui() {
		// TODO Auto-generated method stub
		return null;
	}

}
