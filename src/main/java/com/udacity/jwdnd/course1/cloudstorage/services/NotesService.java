package com.udacity.jwdnd.course1.cloudstorage.services;


import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    private NoteMapper noteMapper;

    public NotesService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    /**
     * we need to write the NoteService for creating, reading, updating and deleting the notes
     * public
     */
    public int createNote(String notetitle, String notedescription, int userid) {
        return noteMapper.insertNote(new Notes(0, notetitle, notedescription, userid));

    }

    public List<Notes> getNotes(int userid) {
        return noteMapper.getNotes(userid);

    }

    public Notes getNotesByNoteId(int noteid) {
        return noteMapper.getNotesByNoteId(noteid);
    }

    public int updateNotes(int noteid, String notetitle, String notedescription) {
        return noteMapper.updateNote(noteid, notetitle, notedescription);
    }

    public int deleteNote(int noteid) {
        return noteMapper.deleteNote(noteid);

    }

}

