package com.example.version4.service.Impl;

import com.example.version4.config.StorageProperties;
import com.example.version4.exception.StorageException;
import com.example.version4.service.StorageService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileSystemStorageServiceImpl implements StorageService {
    private final Path rootLocation;

    @Override
    public String getStoragedFilename(MultipartFile file, String id){
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        return "p" + id + "." + ext;
    }

    public FileSystemStorageServiceImpl(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());

    }

    @Override
    public void store(MultipartFile file, String storedFilename) {
        try{
            if(file.isEmpty()){
                throw new StorageException("Failed to store empty file");

            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storedFilename))
                    .normalize().toAbsolutePath();
            if(destinationFile.getParent()==this.rootLocation.toAbsolutePath()){
                throw new StorageException("Cant store file outside current directory");
            }
            try(InputStream inputStream = file.getInputStream()){
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            }
        }
        catch (Exception e){
            throw new StorageException("Failed to store file: " , e);
        }
    }
    @Override
    public Resource loadAsResource(String filename){
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()){
                return resource;
            }
            throw new StorageException("Could not load file" + filename);
        }
        catch (Exception e){
            throw new StorageException("Could not load file: "+ filename);
        }
    }
    @Override
    public Path load(String filename){
        return rootLocation.resolve(filename);
    }
    @Override
    public void delete(String storedFilename) throws IOException {
        Path destinationFile = rootLocation.resolve(Paths.get(storedFilename)).normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }
    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation);
        }
        catch (Exception e){
            throw  new StorageException("Could not initialize storage", e);
        }
    }
}
