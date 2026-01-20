package crud.webApp.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

//interface to upload images
public interface ImageStorageService {

        String storeImage(MultipartFile file) throws IOException;

}
