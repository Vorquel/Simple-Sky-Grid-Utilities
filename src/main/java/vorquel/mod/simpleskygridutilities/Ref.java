package vorquel.mod.simpleskygridutilities;

import cpw.mods.fml.common.registry.GameRegistry;
import vorquel.mod.simpleskygridutilities.item.Identifier;
import vorquel.mod.simpleskygridutilities.item.Placer;

public class Ref {

    public static final String MOD_ID = "SimpleSkyGridUtilities";
    public static final Identifier itemIdentifier = new Identifier();
    public static final Placer itemPlacer = new Placer();

    public static void preInit() {
        GameRegistry.registerItem(itemIdentifier, "identifier");
        GameRegistry.registerItem(itemPlacer, "placer");
    }
}
