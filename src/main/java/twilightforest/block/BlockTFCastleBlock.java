package twilightforest.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import twilightforest.TwilightForestMod;
import twilightforest.block.state.StateProps;
import twilightforest.block.state.enums.CastleBrickVariant;
import twilightforest.item.ItemTFMazebreakerPick;
import twilightforest.item.TFItems;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;



/**
 * 
 * Castle block makes a castle
 * 
 * @author Ben
 *
 */
public class BlockTFCastleBlock extends Block {

    public BlockTFCastleBlock()
    {
        super(Material.ROCK);
        this.setHardness(100F);
        this.setResistance(15F);
        this.setSoundType(SoundType.STONE);
		this.setCreativeTab(TFItems.creativeTab);
		this.setDefaultState(blockState.getBaseState().withProperty(StateProps.CASTLEBRICK_VARIANT, CastleBrickVariant.NORMAL));
    }

	@Override
	public BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, StateProps.CASTLEBRICK_VARIANT);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(StateProps.CASTLEBRICK_VARIANT).ordinal();
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(StateProps.CASTLEBRICK_VARIANT, CastleBrickVariant.values()[meta]);
	}

	@Override
	public void harvestBlock(World world, EntityPlayer entityplayer, BlockPos pos, IBlockState state, TileEntity te, ItemStack stack)
	{
		// damage the player's pickaxe
    	ItemStack cei = entityplayer.getHeldItemMainhand();
        if(cei != null && cei.getItem() instanceof ItemTool && !(cei.getItem() instanceof ItemTFMazebreakerPick)) 
        {
            cei.damageItem(16, entityplayer);
        }
    	
		super.harvestBlock(world, entityplayer, pos, state, te, stack);
    }
    
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List<ItemStack> par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }
    
    @Override
	public int damageDropped(IBlockState state) {
    	return getMetaFromState(state);
	}

}