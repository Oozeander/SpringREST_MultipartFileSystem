package com.oozeander;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
	public static void main(String[] args) throws IOException {
		Path filePath = Paths.get("C:\\TMP\\SpringREST_MultipartFileSystem").resolve("meuf.jpg");
		String mediaType = Files.probeContentType(filePath);
		System.out.println("File exists ? " + Files.exists(filePath) + ", File readable ? " + Files.isReadable(filePath)
				+ ", File writable ? " + Files.isWritable(filePath) + ", File executable ? "
				+ Files.isExecutable(filePath) + ", MediaType : " + mediaType);
		Files.isWritable(filePath);
		Files.isExecutable(filePath);
	}
}