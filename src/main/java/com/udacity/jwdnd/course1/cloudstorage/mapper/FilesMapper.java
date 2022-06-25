package com.udacity.jwdnd.course1.cloudstorage.mapper;


import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.io.File;
import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE userid=#{userid}")
    List<Files> getFiles(int userid);

    @Select("SELECT * FROM FILES WHERE fileName=#{fileName}")
    Files getFileByName(String fileName);

    @Insert("INSERT INTO FILES(filename, contenttype,filesize, userid, filedata) VALUES(#{filename},#{contenttype},#{filesize},#{userid},#{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId" )
    int insertFiles(Files files);


    @Delete("DELETE FROM FILES WHERE fileName=#{fileName}")
    int deleteFile(String fileName);


}
