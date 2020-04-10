package com.rohit.boxutils;

import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;

public class ListAllFolders {

	final static String BOX_CONFIG = String.join("", System.getProperty("user.dir"), File.separator, "config.json");
    static BoxConfig boxConfig;
    final static int MAX_CACHE_ENTRIES = 100;
    static IAccessTokenCache accessTokenCache;
    private static final int MAX_DEPTH = 5;

	  static {
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
        	    	
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        System.out.println(" aRoot Folder :" + rootFolder.getID() + " name: " + rootFolder.getInfo().getName());
        System.out.println("Listing all Folders");

        listFolder(rootFolder, 0);
    }
    
    
    private static void listFolder(BoxFolder folder, int depth) {
        for (BoxItem.Info itemInfo : folder) {
            String indent = "";
            for (int i = 0; i < depth; i++) {
                indent += "    ";
            }

            System.out.println(indent + itemInfo.getName());
            if (itemInfo instanceof BoxFolder.Info) {
                BoxFolder childFolder = (BoxFolder) itemInfo.getResource();
                if (depth < MAX_DEPTH) {
                    listFolder(childFolder, depth + 1);
                }
            }
        }
    }
    
}




