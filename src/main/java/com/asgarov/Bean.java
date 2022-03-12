package com.asgarov;

import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;
import java.util.Map;

@XmlRootElement
public class Bean {
    private int id;
    private String className;
    private Map<String, String> arguments;

    public Bean() {
    }

    public Bean(int id, String className, Map<String, String> arguments) {
        this.id = id;
        this.className = className;
        this.arguments = arguments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Map<String, String> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
