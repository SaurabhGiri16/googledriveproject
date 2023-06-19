package com.mountblue.mygoogledrive.repositories;

import com.mountblue.mygoogledrive.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    Page<Post> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrContentContainingIgnoreCaseOrTagsNameContainingIgnoreCase(String title);
}
