package pl.mpas.jvm;

public class StackOverflow {

    static void callMe() {
        callMe();
    }
    public static void main(String[] args) {
        callMe();
    }
}
