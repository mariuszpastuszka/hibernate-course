package pl.mpas.jvm;

import java.util.HashSet;
import java.util.Set;

public class OutOfMemory {

    private static void callMe() {
        Set<Object> set = new HashSet<>();
        while (true) {

            set.add(new int[100_0000]);
        }

    }
    public static void main(String[] args) {
        callMe();
    }
}
