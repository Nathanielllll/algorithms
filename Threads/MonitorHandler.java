import javax.management.monitor.Monitor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class MonitorHandler implements InvocationHandler {
    private Object target;

    public MonitorHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("start");
        Object object = method.invoke(target, args);
        System.out.println("end");
        return object;
    }

    public static void main(String[] args) {
        HashMap target = new HashMap();
        MonitorHandler handler = new MonitorHandler(target);
        HashMap proxy = (HashMap) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                handler);
    }


}
