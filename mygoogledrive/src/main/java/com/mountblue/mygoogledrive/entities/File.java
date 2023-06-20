package com.mountblue.mygoogledrive.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="file")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
    private byte[] content;
    private long size;
    private boolean isTrashed;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @PrePersist
    public void prePersist() {
        this.setCreatedAt(LocalDate.now());
        this.setUpdatedAt(LocalDate.now());
        this.isTrashed= false;
    }
    public boolean getTrashed(){
        return this.isTrashed;
    }
}
