package vorquel.mod.simpleskygridutilities;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.EntityList;

import java.util.Set;

public class CommandEntityList extends CommandBase {

    @Override
    public String getCommandName() {
        return "ssguEntityList";
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender commandSender) {
        return true;
    }

    @Override
    public String getCommandUsage(ICommandSender p_71518_1_) {
        return null;
    }

    @Override
    public void processCommand(ICommandSender commandSender, String[] p_71515_2_) {
        for(String name : (Set<String>)EntityList.stringToClassMapping.keySet()) {
            func_152373_a(commandSender, this, name);
        }
    }
}
