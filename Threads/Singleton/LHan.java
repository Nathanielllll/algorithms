package Singleton;

public class LHan {

    private static LHan instance;

    private LHan() {
    }

    public static LHan getInstance(){
        if (instance == null) {
            instance = new LHan();
        }
        return instance;
    }
}
