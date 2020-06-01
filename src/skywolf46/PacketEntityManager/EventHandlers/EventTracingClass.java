package skywolf46.PacketEntityManager.EventHandlers;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;

public class EventTracingClass {
    private HashMap<Class, List<Integer>> classItem = new HashMap<>();
    private Method method;

    public EventTracingClass(Method toTrace) {
        this.method = toTrace;
        Parameter[] param = toTrace.getParameters();
        for (int i = 0; i < param.length; i++) {
            Parameter p = param[i];
            Class c = p.getType();
        }
    }
}
