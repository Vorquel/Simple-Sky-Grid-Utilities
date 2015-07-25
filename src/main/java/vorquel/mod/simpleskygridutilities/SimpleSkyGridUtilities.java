package vorquel.mod.simpleskygridutilities;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import vorquel.mod.simpleskygridutilities.proxy.Proxy;

@Mod(modid = Ref.MOD_ID, name = "Simple Sky Grid Utilities", version = "@MOD_VERSION@")
public class SimpleSkyGridUtilities {

    @Mod.Instance(Ref.MOD_ID)
    @SuppressWarnings("unused")
    public static SimpleSkyGridUtilities instance;

    @SidedProxy(clientSide = "vorquel.mod.simpleskygridutilities.proxy.ProxyClient", serverSide = "vorquel.mod.simpleskygridutilities.proxy.Proxy")
    public static Proxy proxy;

    public static SimpleNetworkWrapper network;

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit();
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void serverLoad(FMLServerStartingEvent event) {
        proxy.serverLoad(event);
    }
}
