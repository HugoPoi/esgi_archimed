package utils;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class FileInfo {

    public File path;
    public String filename;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date created;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date accessed;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    public Date modified;


    public FileInfo(File file) throws IOException{

        path = file;
        filename = file.getName();
        BasicFileAttributes attributes = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
        created = new Date(attributes.creationTime().to(TimeUnit.MILLISECONDS));
        accessed = new Date(attributes.lastAccessTime().to(TimeUnit.MILLISECONDS));
        modified = new Date(attributes.lastModifiedTime().to(TimeUnit.MILLISECONDS));


    }
}
