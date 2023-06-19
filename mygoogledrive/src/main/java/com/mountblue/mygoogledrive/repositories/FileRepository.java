package com.mountblue.mygoogledrive.repositories;

import com.mountblue.mygoogledrive.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByIsTrashedFalse();

    List<File> findByIsTrashedTrue();
}
