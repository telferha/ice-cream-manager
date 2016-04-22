package io.github.pbremer.icecreammanager.controller;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.entity.InputFileMetaData.FileType;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

@Controller
public class FileUploadController implements InitializingBean {

    private static final Logger log =
            LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private InputFileMetaDataService inputFileMetaDataSerice;

    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @RequestMapping(path = "/upload", method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public @ResponseBody String upload(@RequestParam("file") MultipartFile file
    /* RedirectAttributes redirectAttributes */) throws Exception {

	log.info("Got file: {}", file.getOriginalFilename());

	if (!jobExplorer.findRunningJobExecutions("processInputFiles")
	        .isEmpty()) {
	    log.info("There is a batch job already running");
	    // redirectAttributes.addFlashAttribute("errorMessage",
	    // "System is already processing a file. Please wait");
	    // return "redirect:/upload";
	    return "System is already processing a file. Please wait";
	}

	if (file.isEmpty()) {
	    log.info("File {} is empty", file.getOriginalFilename());
	    // redirectAttributes.addFlashAttribute("errorMessage", String
	    // .format("File %s is empty", file.getOriginalFilename()));
	    // return "redirect:/upload";
	    return String.format("File %s is empty",
	            file.getOriginalFilename());
	}

	if (file.getOriginalFilename().contains("/")) {
	    // redirectAttributes.addFlashAttribute("errorMessage",
	    // "Folder seperators not allowed");
	    // return "redirect:/upload";
	    return "Folder seperators not allowed";
	}

	for (FileType type : EnumSet.allOf(FileType.class)) {
	    log.info("Checking: {}", type.getFileName());
	    if (type.getFileName()
	            .equalsIgnoreCase(file.getOriginalFilename())) {
		InputFileMetaData data = inputFileMetaDataSerice.getOne(type);
		try {
		    data.setContents(
		            IOUtils.toByteArray(file.getInputStream()));
		    inputFileMetaDataSerice.save(data);

		    File localFile = new File(FileUtils.getTempDirectory(),
		            type.getFileName());
		    FileUtils.writeByteArrayToFile(localFile,
		            data.getContents(), false);
		    JobExecution jobExecution = launcher.run(job,
		            new JobParametersBuilder()
		                    .addLong("start",
		                            System.currentTimeMillis())
		                    .addString("input.file.name",
		                            "file://" + localFile
		                                    .getAbsolutePath())
		                    .addString("input.file.countablerow.regex",
		                            type.getRegexCount())
		                    .toJobParameters());
		    return jobExecution.getExitStatus().getExitDescription()
		            .toString();
		} catch (IOException | JobExecutionAlreadyRunningException
		        | JobRestartException
		        | JobInstanceAlreadyCompleteException
		        | JobParametersInvalidException e) {
		    log.error("Error processing file", e);
		    throw e;
		}
	    }
	}

	// redirectAttributes.addFlashAttribute("errorMessage", String.format(
	// "File %s is not a valid name", file.getOriginalFilename()));
	// return "redirect:/upload";
	return String.format("File %s is not a valid name",
	        file.getOriginalFilename());
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(inputFileMetaDataSerice);
    }
}
