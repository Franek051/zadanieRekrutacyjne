package org.example;

import java.util.List;

public class MyFolder implements Folder{
    private String name;
    private String size;

    public MyFolder(String name, String size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getSize() {
        return size;
    }
}
