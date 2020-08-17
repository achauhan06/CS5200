package edu.northeastern.cs5200.models;

public enum Type {
    YouTube(0), Heading(1), Html(2), Image(3), Widget(4);

    private final Integer id;

    private Type(Integer id){
        this.id = id;
    }

    public static String getType(Integer id) {
        for (Type es : Type.values()) {
            if (es.id.equals(id)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }

    public static String getType(String name) {
        for (Type es : Type.values()) {
            if (es.name().equals(name)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }
}
