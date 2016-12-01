package com.eliezerwohl.herokucaffeine;

/**
 * Created by Elie on 11/30/2016.
 */

public class Site {
    private int id;
    private String site;
    private int enabled;

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
