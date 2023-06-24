package com.mountblue.mygoogledrive.services;

import com.mountblue.mygoogledrive.entities.Note;
import com.mountblue.mygoogledrive.repositories.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private NoteRepository noteRepository;
    public void addNewNote(Note newNote) {
        noteRepository.save(newNote);
    }
}
