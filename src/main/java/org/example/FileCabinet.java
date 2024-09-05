package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {

    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = folders;
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        for (Folder folder : folders) {
            if (folder instanceof MultiFolder) {
                Optional<Folder> found = findFolderByName(((MultiFolder)folder).getFolders(), name);
                if (found.isPresent()) {
                    return found;
                }
            } else if (folder.getName().equals(name)) {
                return Optional.of(folder);
            }
        }
        return Optional.empty();
    }

    private Optional<Folder> findFolderByName(List<Folder> folders, String name) {
        for (Folder folder : folders) {
            if (folder instanceof MultiFolder) {
                Optional<Folder> found = findFolderByName(((MultiFolder)folder).getFolders(), name);
                if (found.isPresent()) {
                    return found;
                }
            } else if (folder.getName().equals(name)) {
                return Optional.of(folder);
            }
        }
        return Optional.empty();

    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        List<Folder> result = new ArrayList<>();
        findFoldersBySize(folders, size, result);
        return result;
    }

    private void findFoldersBySize(List<Folder> folders, String size, List<Folder> result) {
        for (Folder folder : folders) {
            if (folder instanceof MultiFolder) {
                findFoldersBySize(((MultiFolder)folder).getFolders(), size, result);
            } else if (folder.getSize().equals(size)) {
                result.add(folder);
            }
        }
    }

    private int countFolders(List<Folder> folders) {
        int count = 0;
        for (Folder folder : folders) {
            if (folder instanceof MultiFolder) {
                count += countFolders(((MultiFolder) folder).getFolders());
            } else {
                count++;
            }
        }
        return count;
    }

    @Override
    public int count() {
        return countFolders(folders);
    }

}
