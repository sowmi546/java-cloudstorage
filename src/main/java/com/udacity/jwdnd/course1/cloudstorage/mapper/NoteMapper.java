package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
    List<Notes> getNotes(int userid);

    @Select("SELECT * FROM NOTES WHERE noteid=#{noteid}")
    Notes getNotesByNoteId(int noteid);


    @Insert("INSERT INTO NOTES (notetitle,notedescription,userid) VALUES(#{notetitle},#{notedescription},#{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
        //int insertNote(int noteid, String notetitle, String notedescription, int userid);
    int insertNote(Notes notes);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription} WHERE noteid=#{noteid}")
    int updateNote(int noteid, String notetitle, String notedescription);

    @Delete("DELETE NOTES where noteid=#{noteid}")
    int deleteNote(int nodeid);

}
