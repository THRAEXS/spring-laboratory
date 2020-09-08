package org.hbis.constant;

/**
 * @author 鬼王
 * @date 2020/09/03 10:27
 */
public enum DateTimeFormat {

    DATE_TIME("yyyy-MM-dd HH:mm:ss"),
    DATE("yyyy-MM-dd"),
    TIME("HH:mm:ss");

    private final String value;

    DateTimeFormat(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "DateTimeFormat{" +
                "name='" + name() + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

}
