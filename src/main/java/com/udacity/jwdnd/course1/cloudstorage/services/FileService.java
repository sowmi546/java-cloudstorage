package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class FileService {
    private FilesMapper filesMapper;

    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    /**
     * here fileService will have the logic to get the file, insert the file and delete the file
     */

    public List<Files> getFiles(int userid){
        return filesMapper.getFiles(userid);
    }
    public Files getFileByName(String fileName) { return filesMapper.getFileByName(fileName);}

    public int insertFile(Files files){
        return filesMapper.insertFiles(files);
    }

    public int deleteFile(String fileName){
        return filesMapper.deleteFile(fileName);
    }


}
