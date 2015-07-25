package vorquel.mod.simpleskygridutilities;

import com.google.gson.stream.JsonWriter;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.nbt.*;

import java.io.IOException;
import java.util.List;

public class NBT2JSON {

    public static void writeCompound(JsonWriter jw, NBTTagCompound nbt) throws IOException {
        jw.beginObject();
        for(Object key : nbt.func_150296_c()) {
            String label = (String) key;
            NBTBase tag = nbt.getTag(label);
            String prefix = tagPrefix(tag);
            jw.name(prefix + label);
            writeTag(jw, tag);
        }
        jw.endObject();
    }

    public static void writeByte(JsonWriter jw, NBTTagByte tag) throws IOException {
        jw.value(tag.func_150290_f());
    }

    public static void writeShort(JsonWriter jw, NBTTagShort tag) throws IOException {
        jw.value(tag.func_150289_e());
    }

    public static void writeInt(JsonWriter jw, NBTTagInt tag) throws IOException {
        jw.value(tag.func_150287_d());
    }

    public static void writeLong(JsonWriter jw, NBTTagLong tag) throws IOException {
        jw.value(tag.func_150291_c());
    }

    public static void writeFloat(JsonWriter jw, NBTTagFloat tag) throws IOException {
        jw.value(tag.func_150288_h());
    }

    public static void writeDouble(JsonWriter jw, NBTTagDouble tag) throws IOException {
        jw.value(tag.func_150286_g());
    }

    public static void writeString(JsonWriter jw, NBTTagString tag) throws IOException {
        jw.value(tag.func_150285_a_());
    }

    public static void writeByteArray(JsonWriter jw, NBTTagByteArray tag) throws IOException {
        jw.beginArray();
        for(byte b : tag.func_150292_c())
            jw.value(b);
        jw.endArray();
    }

    public static void writeIntArray(JsonWriter jw, NBTTagIntArray tag) throws IOException {
        jw.beginArray();
        for(int i : tag.func_150302_c())
            jw.value(i);
        jw.endArray();
    }

    public static void writeList(JsonWriter jw, NBTTagList tag) throws IOException {
        jw.beginArray();
        //noinspection unchecked
        for(NBTBase b : (List<NBTBase>) ReflectionHelper.getPrivateValue(NBTTagList.class, tag, "tagList", "field_74747_a"))
            writeTag(jw, b);
        jw.endArray();
    }

    public static void writeTag(JsonWriter jw, NBTBase tag) throws IOException {
        switch(tag.getId()) {
            case 1:  writeByte(     jw,      (NBTTagByte) tag); break;
            case 2:  writeShort(    jw,     (NBTTagShort) tag); break;
            case 3:  writeInt(      jw,       (NBTTagInt) tag); break;
            case 4:  writeLong(     jw,      (NBTTagLong) tag); break;
            case 5:  writeFloat(    jw,     (NBTTagFloat) tag); break;
            case 6:  writeDouble(   jw,    (NBTTagDouble) tag); break;
            case 7:  writeByteArray(jw, (NBTTagByteArray) tag); break;
            case 8:  writeString(   jw,    (NBTTagString) tag); break;
            case 9:  writeList(     jw,      (NBTTagList) tag); break;
            case 10: writeCompound( jw,  (NBTTagCompound) tag); break;
            case 11: writeIntArray( jw,  (NBTTagIntArray) tag); break;
            default: Log.warn("Unrecognised tag type in NBT data");
        }
    }

    public static String tagPrefix(NBTBase tag) {
        switch(tag.getId()) {
            case 1:  return "b_";
            case 2:  return "s_";
            case 3:  return "i_";
            case 4:  return "l_";
            case 5:  return "f_";
            case 6:  return "d_";
            case 7:  return "B_";
            case 8:  return "S_";
            case 9:  return "L_" + listPrefix((NBTTagList) tag);
            case 10: return "C_";
            case 11: return "I_";
            default: return null;
        }
    }

    public static String listPrefix(NBTTagList tag) {
        if(tag.tagCount() == 0)
            return "e_";
        else
            //noinspection unchecked
            return tagPrefix(((List<NBTBase>) ReflectionHelper.getPrivateValue(NBTTagList.class, tag, "tagList", "field_74747_a")).get(0));
    }
}
