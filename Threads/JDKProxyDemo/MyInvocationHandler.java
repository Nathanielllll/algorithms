package JDKProxyDemo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocationHandler implements InvocationHandler {

    private Object object;

    public MyInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("权限管理");
        method.invoke(object, args);
        System.out.println("日志管理");
        return null;
    }

    public static void main(String[] args) {
        Student si = new StudentImpl();

        MyInvocationHandler handler = new MyInvocationHandler(si);

        Student student = (Student) Proxy.newProxyInstance(
                StudentImpl.class.getClassLoader(),
                StudentImpl.class.getInterfaces(),
                handler);

        student.login();
        student.submit();
    }
}
