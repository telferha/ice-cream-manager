package io.github.pbremer.icecreammanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;

@Repository
public interface InputFileMetaDataRepository
        extends JpaRepository<InputFileMetaData, InputFileMetaData.FileType> {

}
