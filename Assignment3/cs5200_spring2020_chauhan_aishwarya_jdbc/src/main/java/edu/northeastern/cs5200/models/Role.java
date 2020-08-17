package edu.northeastern.cs5200.models;

public enum Role {
    owner(0), admin(1), writer(2), editor(3), reviewer(4);
    private final Integer id;

    private Role(Integer id){
        this.id = id;
    }

    public static String getRole(Integer id) {
        for (Role es : Role.values()) {
            if (es.id.equals(id)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }

    public static String getRole(String name) {
        for (Role es : Role.values()) {
            if (es.name().equals(name)) {
                return es.name();
            }
        }
        throw new IllegalArgumentException();
    }

}

