package Singleton;

public class EHan {
    private static EHan instance = new EHan();

    private EHan() {
    }

    public static EHan getInstance(){
        return instance;
    }
}
