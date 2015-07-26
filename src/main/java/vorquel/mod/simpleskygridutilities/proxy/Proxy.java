package vorquel.mod.simpleskygridutilities.proxy;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import vorquel.mod.simpleskygridutilities.CommandModsLoaded;
import vorquel.mod.simpleskygridutilities.Ref;
import vorquel.mod.simpleskygridutilities.SimpleSkyGridUtilities;
import vorquel.mod.simpleskygridutilities.network.MessageClipboard;
import vorquel.mod.simpleskygridutilities.network.MessageHandlerClipboard;
import vorquel.mod.simpleskygridutilities.network.MessageHandlerIdentify;
import vorquel.mod.simpleskygridutilities.network.MessageIdentify;

public class Proxy {

    public void preInit() {
        SimpleSkyGridUtilities.network = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
        SimpleSkyGridUtilities.network.registerMessage(MessageHandlerClipboard.class, MessageClipboard.class, 0, Side.CLIENT);
        SimpleSkyGridUtilities.network.registerMessage(MessageHandlerIdentify.class, MessageIdentify.class, 1, Side.SERVER);
        Ref.preInit();
    }

    public void init() {}

    public void postInit() {}

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandModsLoaded());
    }
}
