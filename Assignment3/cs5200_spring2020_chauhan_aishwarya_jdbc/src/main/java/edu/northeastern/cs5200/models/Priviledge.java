package edu.northeastern.cs5200.models;

public enum Priviledge {

    create(0), read(1), update(2), delete(3);

    private final Integer id;

    private Priviledge(Integer id){
        this.id = id;
    }

    public static String getRole(Integer id) {
        for (Priviledge es : Priviledge.values()) {
            if (es.id.equals(id)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }


    public static String getRole(String name) {
        for (Priviledge es : Priviledge.values()) {
            if (es.name().equals(name)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }
}
