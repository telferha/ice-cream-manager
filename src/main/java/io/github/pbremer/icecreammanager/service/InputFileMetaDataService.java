package io.github.pbremer.icecreammanager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.repository.InputFileMetaDataRepository;

/**
 * Service interface to abstract the {@link InputFileMetaDataRepository}
 * 
 * @author Patrick Bremer
 */
@Service
public interface InputFileMetaDataService
        extends BaseService<InputFileMetaData, InputFileMetaData.FileType> {

    public List<InputFileMetaData>
            getInputFileByStatusOrderByLastModifiedDateDesc(
                    InputFileMetaData.Status status);

    public InputFileMetaData
            getMostRecentInputFileByStatus(InputFileMetaData.Status status);

    public int batchUpdateInputFileStatusByFileType(
            Iterable<InputFileMetaData.FileType> fileTypes,
            InputFileMetaData.Status status);
}
