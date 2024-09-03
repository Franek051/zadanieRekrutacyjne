package org.example;

import java.util.List;

public class MyMultiFolder implements MultiFolder {

    private List<Folder> folders;

    public MyMultiFolder(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public String getName() {
        return null;
    }
    @Override
    public String getSize() {
        return null;
    }
    @Override
    public List<Folder> getFolders() {
        return folders;
    }
}
