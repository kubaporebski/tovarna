package pl.kpp.tovarna.tools;

import java.util.List;
import java.util.Random;

public final class ListUtils {

    private final static Random rnd = new Random();

    public static <T> T getRandom(List<T> list) {
        var position = rnd.nextInt(list.size());
        return list.get(position);
    }

}
