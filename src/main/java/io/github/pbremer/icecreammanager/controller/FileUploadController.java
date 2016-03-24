package io.github.pbremer.icecreammanager.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.pbremer.icecreammanager.entity.InputFileMetaData;
import io.github.pbremer.icecreammanager.service.InputFileMetaDataService;

@Controller
@PropertySource("classpath:ice-cream-manager.properties")
public class FileUploadController implements InitializingBean {

    private static final Logger log =
            LoggerFactory.getLogger(FileUploadController.class);

    private DateFormat dateFormat;

    @Autowired
    private InputFileMetaDataService inputFileMetaDataSerice;

    @Value("${dateFormat}")
    private String dateFormatString;

    @RequestMapping(path = { "/upload" }, method = { RequestMethod.POST })
    public String upload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) {

	log.debug("Got file: {}", file.getOriginalFilename());

	if (file.getOriginalFilename().contains("/")) {
	    redirectAttributes.addFlashAttribute("errorMessage",
	            "Folder seperators not allowed");
	    return "redirect:/upload";
	}

	if (!file.isEmpty()) {
	    try {
		InputFileMetaData metaData = parseFileMetaData(file);
	    } catch (IOException ioe) {
		log.error(String.format(
		        "Exception getting byte array from file: %s",
		        file.getOriginalFilename()), ioe);

		redirectAttributes.addFlashAttribute("errorMessage",
		        String.format(
		                "There was an issue uploading %s, please try again",
		                file.getOriginalFilename()));
		return "redirect:/upload";
	    } catch (ParseException pe) {
		log.error(
		        String.format(
		                "Exception parsing date from file: %s using format: %s",
		                file.getOriginalFilename(), dateFormatString),
		        pe);

		redirectAttributes.addFlashAttribute("errorMessage",
		        String.format("Date format is not in correct format",
		                file.getOriginalFilename()));
		return "redirect:/upload";
	    }
	} else {
	    log.debug("File {} is empty", file.getOriginalFilename());
	    redirectAttributes.addFlashAttribute("errorMessage", String
	            .format("File %s is empty", file.getOriginalFilename()));
	    return "redirect:/upload";
	}

	return null;
    }

    private InputFileMetaData parseFileMetaData(MultipartFile file)
            throws IOException, ParseException {
	InputFileMetaData metaData = new InputFileMetaData();
	LineNumberReader reader = new LineNumberReader(
	        new InputStreamReader(file.getInputStream()));
	metaData.setFileSize(file.getSize());
	metaData.setFileName(file.getOriginalFilename());
	metaData.setContents(file.getBytes());
	String headerRow = reader.readLine();
	metaData.setSequenceNumber(Integer
	        .valueOf(StringUtils.trimToEmpty(headerRow.substring(0, 5))));
	metaData.setDay(dateFormat
	        .parse(StringUtils.trimToEmpty(headerRow.substring(0, 5))));
	reader.skip(Long.MAX_VALUE);
	metaData.setAmmountOfData(
	        (reader.getLineNumber() == 0 ? 0 : reader.getLineNumber() - 1));
	if (metaData.getAmmountOfData() != 0) {
	    metaData.setParsedAmmountOfData(reader.read());
	}
	metaData.setFileType(determineFileType(file));

	return metaData;
    }

    private InputFileMetaData.FileType determineFileType(MultipartFile file) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * @see
     * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
	Assert.notNull(dateFormatString, "You must specify the date format");
	log.info("Date format pattern: {}", dateFormatString);
	dateFormat = new SimpleDateFormat(dateFormatString);
	Assert.notNull(inputFileMetaDataSerice);
    }
}
