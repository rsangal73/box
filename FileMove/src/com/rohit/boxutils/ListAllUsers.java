package com.rohit.boxutils;

	import java.io.BufferedReader;
	import java.io.File;
	import java.nio.charset.Charset;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;

	import com.box.sdk.BoxAPIConnection;
	import com.box.sdk.BoxConfig;
	import com.box.sdk.BoxDeveloperEditionAPIConnection;
	import com.box.sdk.BoxUser;
	import com.box.sdk.IAccessTokenCache;
	import com.box.sdk.InMemoryLRUAccessTokenCache;
	import com.google.common.collect.Lists;

	public class ListAllUsers {

    	final static String BOX_CONFIG = String.join("", System.getProperty("user.dir"), File.separator, "config.json");
	    static BoxConfig boxConfig;
	    final static int MAX_CACHE_ENTRIES = 100;
	    static IAccessTokenCache accessTokenCache;

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
	        	    	
	         Iterable<BoxUser.Info> users = BoxUser.getAllEnterpriseUsers(api);
	         
	         int size = Lists.newArrayList(users).size();
	         System.out.println("Listing " + size + " users ");
	         
	        for(BoxUser.Info o : users){
	        	if(o.getLogin().startsWith("AppUser_"))
	        		
	 	           System.out.println("App User Name: " + o.getName()  + "  User Id: " + o.getID());
	        	else   		
	        		System.out.println("Managed User Name: " + o.getName()  + "  User Id: " + o.getID() + "  Email: " + o.getLogin());
	        }

	    }
	    
	    
	    
	    
	
}




