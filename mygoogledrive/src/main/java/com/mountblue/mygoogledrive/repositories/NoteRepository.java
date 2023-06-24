package com.mountblue.mygoogledrive.repositories;

import com.mountblue.mygoogledrive.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {


}
