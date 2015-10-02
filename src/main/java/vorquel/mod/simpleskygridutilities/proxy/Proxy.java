package vorquel.mod.simpleskygridutilities.proxy;

import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.relauncher.Side;
import org.apache.logging.log4j.LogManager;
import vorquel.mod.simpleskygridutilities.*;
import vorquel.mod.simpleskygridutilities.network.MessageClipboard;
import vorquel.mod.simpleskygridutilities.network.MessageIdentify;

public class Proxy {

    public void preInit() {
        Log.setLogger(LogManager.getLogger(Ref.MOD_ID));
        SimpleSkyGridUtilities.network = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
        SimpleSkyGridUtilities.network.registerMessage(MessageClipboard.MessageHandlerClipboard.class, MessageClipboard.class, 0, Side.CLIENT);
        SimpleSkyGridUtilities.network.registerMessage(MessageIdentify.MessageHandlerIdentify.class, MessageIdentify.class, 1, Side.SERVER);
        Ref.preInit();
    }

    public void init() {}

    public void postInit() {}

    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandModsLoaded());
        event.registerServerCommand(new CommandEntityList());
    }
}
