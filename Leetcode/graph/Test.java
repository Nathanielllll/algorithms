package graph;

public interface Test {
    public static final int a = 0;
    public abstract void getInstance();

    default void getInstance1(int num){
        System.out.println(num);
    }
}
