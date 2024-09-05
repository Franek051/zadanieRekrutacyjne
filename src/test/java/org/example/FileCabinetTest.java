package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class FileCabinetTest {

    private FileCabinet fileCabinet;

    @BeforeEach
    public void setUp() {
        Folder folder1 = new MyFolder("folder1", "small");
        Folder folder2 = new MyFolder("folder2", "medium");
        Folder folder3 = new MyFolder("folder3", "large");

        List<Folder> multiFolders = new ArrayList<>();
        multiFolders.add(folder1);
        multiFolders.add(folder2);

        Folder multiFolder = new MyMultiFolder(multiFolders);

        List<Folder> allFolders = new ArrayList<>();
        allFolders.add(multiFolder);
        allFolders.add(folder3);

        fileCabinet = new FileCabinet(allFolders);
    }

    @Test
    public void findFolderByName_Found() {
        Optional<Folder> foundFolder = fileCabinet.findFolderByName("folder1");
        assertTrue(foundFolder.isPresent());
        assertEquals("folder1", foundFolder.get().getName());
    }

    @Test
    public void findFolderByName_NotFound() {
        Optional<Folder> foundFolder = fileCabinet.findFolderByName("folder4");
        assertFalse(foundFolder.isPresent());
    }

    @Test
    public void findFoldersBySize_Small() {
        List<Folder> smallFolder = fileCabinet.findFoldersBySize("small");
        assertEquals(1, smallFolder.size());
        assertEquals("folder1", smallFolder.get(0).getName());
    }

    @Test
    public void findFoldersBySize_Medium() {
        List<Folder> mediumFolder = fileCabinet.findFoldersBySize("medium");
        assertEquals(1, mediumFolder.size());
        assertEquals("folder2", mediumFolder.get(0).getName());
    }

    @Test
    public void findFoldersBySize_Large() {
        List<Folder> largeFolder = fileCabinet.findFoldersBySize("large");
        assertEquals(1, largeFolder.size());
        assertEquals("folder3", largeFolder.get(0).getName());
    }

    @Test
    public void count() {
        assertEquals(3, fileCabinet.count());
    }
}