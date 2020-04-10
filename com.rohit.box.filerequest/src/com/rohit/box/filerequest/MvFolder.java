package com.rohit.box.filerequest;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.Metadata;
/*
 * Moves new files from Upload to a new folder
 * Also checks if the sub folder exists
 */
public class MvFolder {

	
    final static String BOX_CONFIG = String.join("", System.getProperty("user.dir"), File.separator, "config.json");
    	//	"config.json";
    static BoxConfig boxConfig;
    final static int MAX_CACHE_ENTRIES = 100;
    static IAccessTokenCache accessTokenCache;
    //Update your folder ids and metadata details below
    final static String UPLOAD_FOLDER_ID= "109216598615";
    final static String MASTER_FOLDER_ID= "109213305517";
    final static String MD_TEMPLATE_NAME= "sastudent";   
    final static String MD_FIELD1= "/studentid1";
    final static String MD_FIELD2= "/studentname";
    final static String MD_FIELD3= "/emailaddresss";



	  static {
	        System.out.println("Searching for file..");
	        Path configPath = Paths.get(BOX_CONFIG);
	        System.out.println(configPath.toString());
	        try (BufferedReader reader = Files.newBufferedReader(configPath, Charset.forName("UTF-8"))) {
	            boxConfig = BoxConfig.readFrom(reader);
	            accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);
	        } catch (java.io.IOException e1) {
	            e1.printStackTrace();
	        }
	    }
	  
	  public static BoxAPIConnection adminClient() {
	        BoxDeveloperEditionAPIConnection adminClient = BoxDeveloperEditionAPIConnection
	                .getAppEnterpriseConnection(boxConfig, accessTokenCache);
	        return adminClient;
	    }

	  public static BoxAPIConnection userClient(String userId) {
	        BoxDeveloperEditionAPIConnection userClient = BoxDeveloperEditionAPIConnection.getAppUserConnection(userId,
	                boxConfig);

	        return userClient;
	    }
	  
	  
    public static void main(String[] args) throws Exception {

 
        BoxAPIConnection api = adminClient();
        BoxFolder uploadFolder = new BoxFolder(api, UPLOAD_FOLDER_ID);       
        System.out.println("Listing all Items in folder: " +  uploadFolder.getInfo().getName());
        processFolder(api, uploadFolder);

    }
    
    

    
    private static void processFolder(BoxAPIConnection api, BoxFolder folder) {
        for (BoxItem.Info itemInfo : folder) {
            if (itemInfo instanceof BoxFolder.Info) {
                //BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                System.out.println("Ignoring Sub Folder : " + itemInfo.getName());
               // 	processFolder(api, childFolder); uncomment this if processing child folders
                }
            else {
                System.out.println("Item Found: " + itemInfo.getName());
                BoxFile file = new BoxFile(api, itemInfo.getID());

                Metadata metadata = file.getMetadata(MD_TEMPLATE_NAME); 
                String id = metadata.getString(MD_FIELD1);
                String name = metadata.getString(MD_FIELD2);
                String emailAddress = metadata.getString(MD_FIELD3);
               
           		System.out.println("Processing Id/Name/Email : " + id + "/" + name + "/" + emailAddress);
                
            	BoxFolder masterFolder = new BoxFolder(api, MASTER_FOLDER_ID);
            	//Assumes folder name to be created is ID -- Name. Change code below for anything else.
            	BoxFolder.Info childFolderInfo = ProcessChildFolder(api, masterFolder, id+" - "+name);
            	// BoxFolder.Info childFolderInfo = ProcessChildFolder(api, masterFolder, id);
               	
                System.out.println("Processing child Folder with Name: " + childFolderInfo.getName() 
                		+ " and ID: " + childFolderInfo.getID());    
                BoxFolder destinationFolder = new BoxFolder(api, childFolderInfo.getID());
                file.move(destinationFolder); 
                System.out.println("File Moved to Destination Folder.");	
        	}
          }
        }

    
    
    private static BoxFolder.Info ProcessChildFolder(BoxAPIConnection api, BoxFolder masterFolder, String folderName) {
    	BoxFolder.Info childFolderInfo1 = null;
    	try {
	//		System.out.println("Checking existing or creating Folder with id : " + folderName);
			childFolderInfo1 = masterFolder.createFolder(folderName);
    	}
    	catch (com.box.sdk.BoxAPIResponseException boxe)
    	{
    		if (boxe.getMessage().contains("item_name_in_use")) {
    			System.out.println("Folder Exists");
       			for (BoxItem.Info itemInfo : masterFolder) {
    			    if ((itemInfo instanceof BoxFolder.Info) && (itemInfo.getName().equals(folderName)))
    			        return (BoxFolder.Info)itemInfo;
    			}
    		} else boxe.printStackTrace();
    	} 
    		

    	return childFolderInfo1;
    }
    	
}
    	
