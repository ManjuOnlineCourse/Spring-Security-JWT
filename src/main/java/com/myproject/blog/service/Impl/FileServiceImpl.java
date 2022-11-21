package com.myproject.blog.service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.blog.service.FileService;

@Service
public class FileServiceImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) throws IOException {
		
		//file name
		String name = multipartFile.getOriginalFilename();
		
		
		//random name generate file
		String randomId = UUID.randomUUID().toString();
		String fileName1 = randomId.concat(name.substring(name.lastIndexOf(".")));
		
		//full path
		String filePath = path + File.separator + fileName1;
		
		//Create folder if does not
		File file = new File(path);
		
		if(!file.exists())
		{
			file.mkdir();
		}
		
		//file copy
		Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
		//return name;
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path+File.separator+fileName;
		//db logic to return inputStrem
		InputStream inputStream = new FileInputStream(fullPath);
		return inputStream;
	}
	
	

}
