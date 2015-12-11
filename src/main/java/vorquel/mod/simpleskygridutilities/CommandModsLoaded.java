package vorquel.mod.simpleskygridutilities;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public class CommandModsLoaded extends CommandBase {

    @Override
    public String getCommandName() {
        return "ssguModsLoaded";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender commandSender) {
        return "commands.ssguModsLoaded.usage";
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] options) {
        for(ModContainer mod : Loader.instance().getActiveModList()) {
            notifyOperators(commandSender, this, "%s (%s)", mod.getName(), mod.getModId());
        }
    }
}
