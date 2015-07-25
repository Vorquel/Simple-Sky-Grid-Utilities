package vorquel.mod.simpleskygridutilities;

import cpw.mods.fml.common.registry.GameRegistry;
import vorquel.mod.simpleskygridutilities.item.Identifier;

public class Ref {

    public static final String MOD_ID = "SimpleSkyGridUtilities";
    public static final Identifier itemIdentifier = new Identifier();

    public static void preInit() {
        GameRegistry.registerItem(itemIdentifier, "identifier");
    }
}
