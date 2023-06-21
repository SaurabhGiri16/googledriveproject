package com.mountblue.mygoogledrive.repositories;

import com.mountblue.mygoogledrive.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.*;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByIsTrashedFalse();

    List<File> findByIsTrashedTrue();

    List<File> findByFileTypeIn(List<String> fileTypes);

    List<File> findByFileNameContaining(String substring);

    @Query("SELECT e FROM File e WHERE e.updatedAt >= :specificDate")
    List<File> getDataOnOrAfterUpdatedAt(LocalDate specificDate);
}
