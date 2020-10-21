package com.thoriuslight.professionsmod.block;

import java.util.stream.Stream;

import com.thoriuslight.professionsmod.client.gui.CastingBasinScreen;
import com.thoriuslight.professionsmod.init.ModTileEntityTypes;
import com.thoriuslight.professionsmod.item.MoltenMetalItem;
import com.thoriuslight.professionsmod.state.properties.Tool;
import com.thoriuslight.professionsmod.tileentity.CastingBasinTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer.Builder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
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

public class CastingBasinBlock extends Block{
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty FILL = BooleanProperty.create("fill");
			
	private static final VoxelShape SHAPE_N = Stream.of(
			Block.makeCuboidShape(2, 2, 14, 14, 6, 16),
			Block.makeCuboidShape(2, 2, 0, 14, 6, 2),
			Block.makeCuboidShape(14, 2, 0, 16, 6, 16),
			Block.makeCuboidShape(0, 2, 0, 2, 6, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 2, 16)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	private static final VoxelShape SHAPE_NFILL = Stream.of(
			Block.makeCuboidShape(2, 2, 14, 14, 6, 16),
			Block.makeCuboidShape(2, 2, 0, 14, 6, 2),
			Block.makeCuboidShape(14, 2, 0, 16, 6, 16),
			Block.makeCuboidShape(0, 2, 0, 2, 6, 16),
			Block.makeCuboidShape(0, 0, 0, 16, 2, 16),
			Block.makeCuboidShape(2, 2, 2, 14, 5, 14)
			).reduce((v1, v2) -> {return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();
	
	public CastingBasinBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH).with(FILL, Boolean.valueOf(false)));
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return state.get(FILL) ? SHAPE_NFILL : SHAPE_N;
	}
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return ModTileEntityTypes.CASTINGBASIN.get().create();
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if(player.getHeldItemMainhand().getItem() == Items.CLAY_BALL && !state.get(FILL)) {
			if (worldIn.isRemote) {
				return ActionResultType.SUCCESS;
	        } else {
	            worldIn.playSound((PlayerEntity)null, pos, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
	            BlockState blockstate1 = state.with(FILL, Boolean.valueOf(true));
	            Block.nudgeEntitiesWithNewState(state, blockstate1, worldIn, pos);
	            worldIn.setBlockState(pos, blockstate1, 2);
	            player.getHeldItemMainhand().shrink(1);
	        	return ActionResultType.SUCCESS;
	        }
		} else if(player.getHeldItemMainhand().getItem() instanceof MoltenMetalItem && state.get(FILL)){
			if (worldIn.isRemote) {
				return ActionResultType.SUCCESS;
	        } else {
		    	TileEntity tileentity = worldIn.getTileEntity(pos);
		    	if(tileentity instanceof CastingBasinTileEntity) {
		    		if(((CastingBasinTileEntity)tileentity).addMetal(((MoltenMetalItem)player.getHeldItemMainhand().getItem()).getMetal())) {
		    			worldIn.playSound((PlayerEntity)null, pos, SoundEvents.ITEM_BUCKET_EMPTY_LAVA, SoundCategory.BLOCKS, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
		    			player.getHeldItemMainhand().shrink(1);
			            if (!player.inventory.addItemStackToInventory(new ItemStack(Items.FLOWER_POT))) {
			            	player.dropItem(new ItemStack(Items.FLOWER_POT), false);
			            }
		    			return ActionResultType.SUCCESS;
		    		}else {
		    			return ActionResultType.PASS;
		    		}
		    	}
	        }
		}else if(player.getHeldItemMainhand().isEmpty()) {
			if (worldIn.isRemote) {
				Minecraft.getInstance().displayGuiScreen(new CastingBasinScreen(pos));
	        }
        	return ActionResultType.SUCCESS;
		}
		return ActionResultType.PASS;
	}
	
	public void dropItem(World worldIn, BlockPos pos, ItemStack itemstack) {
		if (!itemstack.isEmpty()) {
			BlockState state = worldIn.getBlockState(pos).with(FILL, Boolean.valueOf(false));
    		worldIn.setBlockState(pos, state, 2);
    		worldIn.playEvent(1010, pos, 0);
    		double d0 = (double)(worldIn.rand.nextFloat() * 0.2F) + (double)0.15F;
    		double d1 = (double)(worldIn.rand.nextFloat() * 0.7F) + (double)0.060000002F;
    		double d2 = (double)(worldIn.rand.nextFloat() * 0.2F) + (double)0.15F;
    		ItemStack itemstack1 = itemstack.copy();
    		ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX() + d0, (double)pos.getY() + d1, (double)pos.getZ() + d2, itemstack1);
    		itementity.setDefaultPickupDelay();
    		worldIn.addEntity(itementity);
		}      
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite()).with(FILL, Boolean.valueOf(false));
	}
	@Override
	public BlockState rotate(BlockState state, Rotation rot) {
		return state.with(FACING, rot.rotate(state.get(FACING)));
	}
	@Override
	public BlockState mirror(BlockState state, Mirror mirrorIn) {
		return state.rotate(mirrorIn.toRotation(state.get(FACING)));
	}
	@Override
	protected void fillStateContainer(Builder<Block, BlockState> builder) {
		builder.add(FACING, FILL);
	}
	public static void setTool(Tool tool, World world, BlockPos pos) {
		if (!world.isRemote) {
            TileEntity tileentity = world.getTileEntity(pos);
            if(tileentity instanceof CastingBasinTileEntity) {
            	((CastingBasinTileEntity)tileentity).setTool(tool);;
            }
		}
	}
}
