package crud.webApp.service;

import crud.webApp.storage.ImageStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class GoogleStorageService implements ImageStorageService{

        private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/property_images/";

        @Override
        public String storeImage(MultipartFile file) throws IOException {
            if (file.isEmpty()) {
                throw new IOException("Failed to store empty file " + file.getOriginalFilename());
            }

            File dest = new File(UPLOAD_DIRECTORY);
            if (!dest.exists()) {
                if(!dest.mkdirs()) {
                    throw new IOException("Failed to create upload directory: " + UPLOAD_DIRECTORY);
                }

            }

            // Generate unique filename
            String originalName = file.getOriginalFilename();
            String extension = originalName != null ? originalName.substring(originalName.lastIndexOf(".")) : ".jpg";
            String uniqueName = UUID.randomUUID() + extension;

            // Full path for the new file
            String localPath = UPLOAD_DIRECTORY + uniqueName;
            file.transferTo(new File(localPath));

            // Return placeholder for manual Google Drive step
            return "MANUAL_UPLOAD_NEEDED: Upload file '" + uniqueName + "' from:\n" +
                    UPLOAD_DIRECTORY + "\n" +
                    "to your Google Drive folder.\n" +
                    "Then use this URL: https://drive.google.com/uc?export=view&id=YOUR_FILE_ID_HERE\n" +
                    "(Alternative thumbnail version: https://drive.google.com/thumbnail?id=YOUR_FILE_ID&sz=w800)";

        }
    }