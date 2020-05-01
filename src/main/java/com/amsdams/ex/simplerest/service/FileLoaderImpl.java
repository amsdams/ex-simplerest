package com.amsdams.ex.simplerest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileLoaderImpl implements FileLoader {

	@Override
	public byte[] loadFile(String dataFileName) {
		Path path = Paths.get(dataFileName);
		byte[] data = null;
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}

	@Override
	public String createFile(byte[] data) {
		Path path = Paths.get(UUID.randomUUID().toString());
		try {
			Files.write(path, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path.toString();
	}

}
