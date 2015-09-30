package vorquel.mod.simpleskygridutilities.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import vorquel.mod.simpleskygrid.config.SimpleSkyGridConfigReader;
import vorquel.mod.simpleskygrid.config.prototype.IPrototype;
import vorquel.mod.simpleskygrid.config.prototype.PFactory;
import vorquel.mod.simpleskygrid.world.generated.GeneratedBlock;
import vorquel.mod.simpleskygrid.world.generated.IGeneratedObject;
import vorquel.mod.simpleskygridutilities.Log;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.StringReader;

public class Placer extends Item {

    public Placer() {
        setUnlocalizedName("placer");
        setCreativeTab(CreativeTabs.tabMisc);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        itemIcon = iconRegister.registerIcon("SimpleSkyGridUtilities:placer");
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        if(world.isRemote)
            return true;
        if(player.isSneaking()) {
            //todo: make this server friendly
            writeGeneratedObject(stack);
            return true;
        }
        switch(side) {
            case 0: --y; break;
            case 1: ++y; break;
            case 2: --z; break;
            case 3: ++z; break;
            case 4: --x; break;
            case 5: ++x; break;
            default:
                player.addChatComponentMessage(new ChatComponentText("Unknown side shift clicked on."));
                Log.warn("Unexpected side of block found on right clicking block with Placer");
                break;
        }
        placeGeneratedObject(stack, world, x, y, z);
        return true;
    }

    private void writeGeneratedObject(ItemStack stack) {
        if(!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
        try {
            String data = (String) Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
            data = data == null ? "" : data;
            stack.getTagCompound().setString("json", data);
        } catch(UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
        if(transferable instanceof StringSelection) {
            try {
                String data = (String) transferable.getTransferData(transferable.getTransferDataFlavors()[0]);
                data = data == null ? "" : data;
                stack.getTagCompound().setString("json", data);
            } catch (UnsupportedFlavorException | IOException ignored) {}
        }
    }

    private void placeGeneratedObject(ItemStack stack, World world, int x, int y, int z) {
        if(!stack.hasTagCompound()) return;
        NBTTagCompound tag = stack.getTagCompound();
        if(!tag.hasKey("json")) return;
        String json = tag.getString("json");
        IPrototype<IGeneratedObject> prototype = PFactory.readGeneratedObject(new SimpleSkyGridConfigReader("Clipboard", new StringReader(json)));
        if(!prototype.isComplete()) return;
        IGeneratedObject generatedObject = prototype.getObject();
        if(generatedObject == null) return;
        generatedObject.provideObject(world.rand, world, x, y, z);
    }
}
