package com.oozeander.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.oozeander.model.File;
import com.oozeander.service.FileService;

@Service("fileService")
public class FileServiceImpl implements FileService {
	@Value("${upload.dir}")
	private String uploadDir;

	@Override
	public Path get(String fileName, HttpServletResponse response) throws IOException {
		Path filePath = Paths.get(uploadDir, fileName);
		if (Files.exists(filePath)) {
			response.setContentType(Files.probeContentType(filePath));
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setStatus(200);
			return filePath;
		} else
			response.setStatus(404);
			return null;
	}

	@Override
	public File save(MultipartFile multipartFile) throws IOException {
		Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
		if (!multipartFile.isEmpty()) {
			try {
				if (!Files.exists(uploadPath)) {
					Files.createDirectories(uploadPath);
				}
				Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
				if (filePath.toString().contains("..")) {
					return null;
				}
				Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception exception) {
				System.out.println("ERROR, cause : " + exception.getLocalizedMessage());
			}
			return new File(multipartFile.getOriginalFilename(), multipartFile.getContentType(),
					multipartFile.getSize() / 1024 + " Ko",
					uploadPath.resolve(multipartFile.getOriginalFilename()).toString());
		} else {
			return null;
		}
	}
}