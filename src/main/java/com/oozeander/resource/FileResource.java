package com.oozeander.resource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.oozeander.model.File;
import com.oozeander.service.FileService;

@RestController
@RequestMapping("/api/files")
@CrossOrigin
public class FileResource {
	@Autowired
	private FileService fileService;
	@Value("${upload.dir}")
	private String uploadDir;

	@GetMapping(value = "/{fileName:.+}")
	public void getFile(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
		Path filePath = fileService.get(fileName, response);
		if (filePath != null) {
			try {
				Files.copy(filePath, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (Exception exception) {
				System.out.println("ERROR, cause : " + exception.getLocalizedMessage());
			}
		}
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<File> saveFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		File file = fileService.save(multipartFile);
		if (file != null) {
			return ResponseEntity
					.created(ServletUriComponentsBuilder.fromPath(Paths.get(uploadDir).toString() + "\\{fileName}")
							.buildAndExpand(multipartFile.getOriginalFilename()).toUri())
					.body(file);
		}
		return ResponseEntity.badRequest().build();
	}
}