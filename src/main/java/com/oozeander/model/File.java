package com.oozeander.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({ "fileName", "fileType", "fileSize", "filePath" })
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class File {
	private String fileName, fileType, fileSize, filePath;
}