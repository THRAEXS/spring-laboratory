package org.hbis.util;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 鬼王
 * @date 2020/09/06 12:20
 */
public abstract class Joiner {

    private Joiner() { }

    public static String join(CharSequence delimiter, List<String> items) {
        return join(delimiter, items.stream());
    }

    public static String join(CharSequence delimiter, String... items) {
        return join(delimiter, Stream.of(items));
    }

    public static String join(CharSequence delimiter, Stream<String> items) {
        return items.collect(Collectors.joining(delimiter));
    }

    public static String path(String... items) {
        return join(File.separator, Stream.of(items));
    }

}
