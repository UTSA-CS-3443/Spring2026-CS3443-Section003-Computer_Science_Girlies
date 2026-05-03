package edu.utsa.cs3443.project_demo.model;

import java.util.List;

public class Setting {
    private String key;
    private String type;
    private String value;
    private List<String> options;

    public Setting(String key, String type, String value, List<String> options) {
        this.key = key;
        this.type = type;
        this.value = value;
        this.options = options;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setValue(String value) {
        this.value = value;
    }
}