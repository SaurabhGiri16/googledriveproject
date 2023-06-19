package com.mountblue.mygoogledrive.services;

import com.mountblue.mygoogledrive.entities.File;
import com.mountblue.mygoogledrive.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public File createFile(File file) {
        return fileRepository.save(file);
    }

    public List<File> getAllFiles() {
        return fileRepository.findByIsTrashedFalse();
    }

    public File findFileByFileId(long id) {
        return fileRepository.findById(id).get();
    }

    public boolean trashed(long id){
        File file = fileRepository.findById(id).get();
        file.setTrashed(true);
        file.setUpdatedAt(LocalDate.now());
        fileRepository.save(file);
        return true;
    }

    public boolean restore(long id){
        File file = fileRepository.findById(id).get();
        file.setTrashed(false);
        file.setUpdatedAt(LocalDate.now());
        fileRepository.save(file);
        return true;
    }
    public List<File> getTrashedFiles() {
        return fileRepository.findByIsTrashedTrue();
    }

    public boolean delete(long id){
        fileRepository.deleteById(id);
        return true;
    }

    public void renameFile(long id, String name){
        File file = fileRepository.findById(id).get();
        file.setFileName(name);
        file.setUpdatedAt(LocalDate.now());
        fileRepository.save(file);
    }
}
