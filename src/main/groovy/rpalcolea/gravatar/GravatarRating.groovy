package rpalcolea.gravatar

import groovy.transform.CompileStatic

@CompileStatic
public enum GravatarRating {

    GENERAL_AUDIENCES("g"), PARENTAL_GUIDANCE_SUGGESTED("pg"), RESTRICTED("r"), XPLICIT("x")

    private String code

    private static final Map<String, GravatarRating> lookup = new HashMap<String, GravatarRating>();

    static {
        for (GravatarRating d : values()) {
            lookup.put(d.code, d)
        }
    }

    private GravatarRating(String code) {
        this.code = code
    }

    public String getCode() {
        code
    }

    public static GravatarRating get(String code) {
        lookup.get(code)
    }
}
