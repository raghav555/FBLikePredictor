package com.approject.part2;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;

public class Constants {
 
   public static final String REDIRECT_URI
                 = "http://localhost:8080/facebookfriends/FriendsListServlet";
 
    
   public static final String MY_ACCESS_TOKEN = "CAACEdEose0cBADTX2fY9dq7LyazNl9KUcCL6PqQZCTpftt17utXM1bSmxZCrAMp21Y9yDjo4bD252naVs53UvteR8DJokYdiybDxkUSPuPn0nnrRf3ubiaKrtdfJpXZCLXsyxzFGAG2AXs8wkZB6TNh8so7UZCTmP2bTvP8gNmDcloBxGEq5ZBRh4WoTynYdmLRa04Cx6NhymYh4lHGdQD";
 
   // Facebook App
   public static final String MY_APP_ID = "329654297204895";
   public static final String MY_APP_SECRET = "8ac1dc31b6ad86941187128fafd57d7f";
 

   FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

//FacebookClient publicOnlyFacebookClient = new DefaultFacebookClient();

// Get added security by using your app secret:

//FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN, MY_APP_SECRET);
   
   
   
   
   
}