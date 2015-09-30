package vorquel.mod.simpleskygridutilities.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class MessageClipboard implements IMessage {

    public String message;

    public MessageClipboard(String message) {
        this.message = message;
    }

    @SuppressWarnings("unused")
    public MessageClipboard() {}

    @Override
    public void fromBytes(ByteBuf buf) {
        message = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, message);
    }

    public static class MessageHandlerClipboard implements IMessageHandler<MessageClipboard, IMessage> {
        @Override
        public IMessage onMessage(MessageClipboard message, MessageContext ctx) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(message.message), null);
            return null;
        }
    }
}
