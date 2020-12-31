package com.oozeander.service;

import java.io.IOException;
import java.nio.file.Path;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.oozeander.model.File;

public interface FileService {
	Path get(String fileName, HttpServletResponse response) throws IOException;

	File save(MultipartFile multipartFile) throws IOException;
}