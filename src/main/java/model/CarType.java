package model;

import java.util.HashMap;
import java.util.Map;

public enum CarType {
    STA("STA"),
    KW20("KW20"),
    KW50("KW50");

    private static final Map<String, CarType> lookup = new HashMap<String, CarType>();
    static {
        for (CarType c : CarType.values()) {
            lookup.put(c.getType(), c);
        }
    }

    private String type;

    CarType(String type) {
        this.type = type;
    }

    public String getType() {
        return type.toLowerCase();
    }

    public static CarType get(String type) {
        return lookup.get(type);
    }
}
