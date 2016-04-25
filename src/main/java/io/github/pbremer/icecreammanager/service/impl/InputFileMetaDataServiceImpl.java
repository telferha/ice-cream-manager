package io.github.pbremer.icecreammanager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.Status;
import io.github.pbremer.icecreammanager.repository.InputFileMetaDataRepository;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

/**
 * Service to abstract the {@link InputFileMetaDataRepository}
 * 
 * @author Patrick Bremer
 */
@Service
public class InputFileMetaDataServiceImpl extends
        AbstractBaseService<InputFileMetaData, InputFileMetaData.FileType>
        implements InputFileMetaDataService {

    private InputFileMetaDataRepository repository;

    @Autowired
    public InputFileMetaDataServiceImpl(
            InputFileMetaDataRepository repository) {
	super(repository);
	this.repository = repository;
    }

    @Override
    public List<InputFileMetaData>
            getInputFileByStatusOrderByLastModifiedDateDesc(Status status) {
	return repository.findByStatusOrderByLastModifiedDateDesc(status);
    }

    @Override
    public InputFileMetaData getMostRecentInputFileByStatus(Status status) {
	return getInputFileByStatusOrderByLastModifiedDateDesc(status).get(0);
    }

    @Override
    public int batchUpdateInputFileStatusByFileType(
            Iterable<InputFileMetaData.FileType> fileTypes,
            InputFileMetaData.Status status) {

	List<InputFileMetaData> updatedFiles =
	        new ArrayList<InputFileMetaData>();

	for (InputFileMetaData inputFile : repository.findAll(fileTypes)) {
	    inputFile.setStatus(status);
	    updatedFiles.add(inputFile);
	}

	repository.save(updatedFiles);
	return updatedFiles.size();

    }

    @Override
    public void afterPropertiesSet() throws Exception {
	super.afterPropertiesSet();
	Assert.notNull(repository);
    }

}
