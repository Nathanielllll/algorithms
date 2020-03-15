package JDKProxyDemo;

public class StudentImpl implements Student {
    @Override
    public void login() {
        System.out.println("登陆");
    }

    @Override
    public void submit() {
        System.out.println("提交");
    }
}
