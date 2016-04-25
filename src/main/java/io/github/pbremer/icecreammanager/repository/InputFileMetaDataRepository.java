package io.github.pbremer.icecreammanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;

/**
 * Interface to persist {@link InputFileMetaData} into the database. There is no
 * implementation because Spring automatically generates and injects the logic.
 * 
 * @author Patrick Bremer
 */
@Repository
public interface InputFileMetaDataRepository
        extends JpaRepository<InputFileMetaData, InputFileMetaData.FileType> {

    public List<InputFileMetaData> findByStatusOrderByLastModifiedDateDesc(
            InputFileMetaData.Status status);

}
