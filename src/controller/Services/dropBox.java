/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Services;

import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.RequestedVisibility;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import com.dropbox.core.v2.sharing.SharedLinkSettings;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 *
 * @author amani
 */
public class dropBox {
    private static final String ACCESS_TOKEN ="U0JkwuJv7QAAAAAAAAAAKyQWQL4Nxxxa19K0ONYNgWBK55CjgNjkBjZOnKF_f9xI";  
 
    DbxRequestConfig config = new DbxRequestConfig("dropbox/happyOlds");
    DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
    
    public String uploadImage(File localFile){          
        SharedLinkMetadata sharedLinkMetadata=null;
        String remoteFileName = localFile.getName();
       
            try (InputStream inputStream = new FileInputStream(localFile)) {

                UploadBuilder metadata = client.files().uploadBuilder("/"+remoteFileName);
                metadata.withMode(WriteMode.OVERWRITE);
                metadata.withClientModified(new Date(localFile.lastModified()));
                metadata.withAutorename(false);
                metadata.uploadAndFinish(inputStream);

                SharedLinkSettings linkSettings = SharedLinkSettings.newBuilder().withRequestedVisibility(RequestedVisibility.PUBLIC).build();
                sharedLinkMetadata = client.sharing().createSharedLinkWithSettings("/"+localFile.getName(), linkSettings);

 
            } catch (DbxException | IOException e) {
                System.out.println(e.getMessage());
            }    
        return sharedLinkMetadata.getPathLower();  
    }
    
    
    public String downloadImage(String path){
       
        String filePath="";     
        try {
   //Creates an empty file in the default temporary-file directory, using the given prefix and suffix to generate its name.
            File tmpFile = File.createTempFile("dropbox-downloaded-file", ".jpeg");
            DbxDownloader<FileMetadata> download = client.files().download(path);
            OutputStream fOut = new FileOutputStream(tmpFile);
            download.download(fOut);
            
            //recuperer le path de l'image
            filePath = tmpFile.getAbsolutePath();
            fOut.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        String ch=filePath;
        
        while(ch.indexOf("\\")!=-1){
            char[] tab = ch.toCharArray();
            tab[ch.indexOf("\\")] = '/';
            ch= String.valueOf(tab);
        }
        
    return ch;
    }
    
}
