package com.thoriuslight.professionsmod.block;

import java.util.Random;
import java.util.stream.Stream;

import com.thoriuslight.professionsmod.init.ItemInit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlassJarBlock extends Block{
	public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 9);
	private static final VoxelShape SHAPE = Stream.of(
			Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 1, 5, 11, 10, 6),
			Block.makeCuboidShape(5, 1, 10, 11, 10, 11),
			Block.makeCuboidShape(5, 1, 6, 6, 10, 10),
			Block.makeCuboidShape(10, 1, 6, 11, 10, 10),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11),
			Block.makeCuboidShape(6, 10, 6, 10, 11, 10)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_FILLED = Stream.of(
			Block.makeCuboidShape(5, 0, 5, 11, 1, 11),
			Block.makeCuboidShape(5, 1, 5, 11, 10, 6),
			Block.makeCuboidShape(5, 1, 10, 11, 10, 11),
			Block.makeCuboidShape(5, 1, 6, 6, 10, 10),
			Block.makeCuboidShape(10, 1, 6, 11, 10, 10),
			Block.makeCuboidShape(5, 11, 5, 11, 12, 11),
			Block.makeCuboidShape(6, 10, 6, 10, 11, 10),
			Block.makeCuboidShape(6, 1, 6, 10, 9, 10)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public GlassJarBlock(Properties properties) {
		super(properties.tickRandomly());
	    this.setDefaultState(this.stateContainer.getBaseState().with(AGE, Integer.valueOf(0)));
	}

	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
	      super.tick(state, worldIn, pos, rand);
	      if (!worldIn.isAreaLoaded(pos, 1)) return;
	      int i = this.getAge(state);
          if (i < this.getMaxAge() && i != 0) {
              worldIn.setBlockState(pos, this.withAge(i + 1), 2);
          }
	}
	public BlockState withAge(int age) {
		return this.getDefaultState().with(AGE, Integer.valueOf(age));
	}
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		ItemStack stack = player.getHeldItemMainhand();
		if(stack.getItem() == ItemInit.SOURDOUGH.get() && this.getAge(state) == 0) {
			if (worldIn.isRemote) {
				return ActionResultType.SUCCESS;
	        } else {
	            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
	            BlockState blockstate1;
	            if(stack.getDamage() == 0)
	            blockstate1 = withAge(1);
	            else
	            blockstate1 = withAge(((25 - stack.getDamage())/3)+1);
	            Block.nudgeEntitiesWithNewState(state, blockstate1, worldIn, pos);
	            worldIn.setBlockState(pos, blockstate1, 2);
	            player.getHeldItemMainhand().shrink(1);
	        	return ActionResultType.SUCCESS;
	        }
		} else if(stack.isEmpty() && this.getAge(state) > 0) {
			if (worldIn.isRemote) {
				return ActionResultType.SUCCESS;
	        } else {
	            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_BARREL_OPEN, SoundCategory.BLOCKS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
            	int age = this.getAge(state);
            	BlockState blockstate1 = withAge(0);
            	worldIn.setBlockState(pos, blockstate1, 2);
            	ItemStack itemstack = new ItemStack(ItemInit.SOURDOUGH.get());
            	if(age == 1)
            	itemstack.setDamage(0);
            	else
            	itemstack.setDamage(25 - ((age-1) * 3));
                if (!player.inventory.addItemStackToInventory(itemstack.copy())) {
                    player.dropItem(itemstack, false);
                }
	        	return ActionResultType.SUCCESS;
	        }
		}
		System.out.println(this.getAge(state));
    	return ActionResultType.PASS;
	}
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		if(this.getAge(state)==0)
		return SHAPE;
		else
		return SHAPE_FILLED;
	}
	@Override
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}
	public int getMaxAge() {
		return 9;
	}
	protected int getAge(BlockState state) {
		return state.get(AGE);
	}
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
	   return true;
	}
	@Override
	public boolean causesSuffocation(BlockState state, IBlockReader worldIn, BlockPos pos) {
	   return false;
	}
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos) {
	   return false;
	}
	@Override
	public boolean canEntitySpawn(BlockState state, IBlockReader worldIn, BlockPos pos, EntityType<?> type) {
	   return false;
	}
	@Override
	public boolean allowsMovement(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
		return false;
	}
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(AGE);
	}
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(AGE, 0);
	}
}
