package skywolf46.PacketEntityManager.Util;

import java.lang.reflect.Field;

public class ReflectUtil {
    public static <T> T getField(Field f){
        try {
            f.setAccessible(true);
            return (T) f.get(null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static Field getField(Class c,String name){
        try {
            Field f = c.getDeclaredField(name);
            f.setAccessible(true);
            return f;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public static void setField(Object obj, String field_name, Object value) {
        try {
            Field field = obj.getClass().getDeclaredField(field_name);
            field.setAccessible(true);
            field.set(obj, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
