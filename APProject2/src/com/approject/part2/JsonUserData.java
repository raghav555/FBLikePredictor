package com.approject.part2;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.approject.part2.Constants;
import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.DefaultJsonMapper;
import com.restfb.FacebookClient;
import com.restfb.JsonMapper;
import com.restfb.Parameter;
import com.restfb.exception.FacebookException;
import com.restfb.json.JsonArray;
import com.restfb.json.JsonObject;
import com.restfb.json.JsonString;
import com.restfb.types.Insight;
import com.restfb.types.Post;
import com.restfb.types.Url;
import com.restfb.types.User;
import com.sun.javafx.css.StyleCache.Key;
 
public class JsonUserData {
 
   
	public static <T> void main(String[] args) {
		Map<String, Integer> m = new HashMap<String, Integer>();
        FacebookClient facebookClient = new DefaultFacebookClient(
                Constants.MY_ACCESS_TOKEN);
 
        String[] fbpostids = new String[10];
        int counter=0;
        int noofposts=0;
        
        JsonObject userData = facebookClient.fetchObject("me",
                JsonObject.class, Parameter.with("fields", "name, first_name"));
 
        System.out.println("userData=" + userData);
 
        System.out.println("FirstName=" + userData.getString("first_name"));
        System.out.println("Name= " + userData.getString("name"));
        
        JsonObject photosConnection = facebookClient.fetchObject("me/photos", JsonObject.class);
        /*
        String firstPhotoUrl = photosConnection.getJsonArray("data").getJsonObject(0).getString("source");
        String first1PhotoUrl = photosConnection.getJsonArray("data").getJsonObject(1).getString("source");
        System.out.println(photosConnection.getJsonArray("data"));
        System.out.println(firstPhotoUrl);
        System.out.println(first1PhotoUrl);
        System.out.println(photosConnection.getJsonArray("data").length());
        */
        Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed = facebookClient.fetchConnection("me/feed", Post.class);
        
        
        JsonObject posts = facebookClient.fetchObject("me/posts", JsonObject.class);
        System.out.println(posts.getJsonArray("data"));
        String f=posts.getJsonArray("data").getJsonObject(0).getString("id");
        System.out.println(f);
        System.out.println(posts.getJsonArray("data").length());
        int i;
        for(i=0;i<posts.getJsonArray("data").length();i++)
        {
        	String g=posts.getJsonArray("data").getJsonObject(i).getString("id");
        	fbpostids[i]=g;
        	i++;
        }
        for(i=0;i<posts.getJsonArray("data").length();i++)
        {
        	System.out.println(fbpostids[i]);
        	if(fbpostids[i]!=null || fbpostids[i]!="null")
        	{
        		noofposts++;
        	}
        }
        System.out.println(noofposts);
        String[] h1 = new String[5];
        for(i=0;i<posts.getJsonArray("data").length();i++)
        {
        	JsonObject likersposts = facebookClient.fetchObject(fbpostids[i]+"/likes", JsonObject.class);
        	System.out.println(likersposts);
        	//System.out.println(likersposts.getJsonArray("data").getJsonObject(i).getString("name"));
        	List<String> list = new ArrayList<String>();
            JsonArray array = likersposts.getJsonArray("data");
            for(i = 0 ; i < array.length() ; i++){
               list.add(array.getJsonObject(i).getString("name"));
            }
            
            for(i=0;i< list.size();i++)
            {
            	if (m.containsKey(list.get(i))) {
                    m.put(list.get(i), m.get(list.get(i)) + 1);
                } else {
                    m.put(list.get(i), 1);
                }
            	//System.out.println(list.get(i));
            }
            Iterator iterator = m.keySet().iterator();
            
            while (iterator.hasNext()) {
               String key = iterator.next().toString();
               String value = m.get(key).toString();
              
               System.out.println(key + " " + value);
            }
            Map<String, Double> prob = new HashMap<String, Double>();
            for (String k : prob.keySet()) {
                prob.put(k, (prob.get(k)/noofposts)*1.0);
            }
            while (iterator.hasNext()) {
                String key = iterator.next().toString();
                String value = prob.get(key).toString();
                System.out.println(key + " " + value);
             }
            
            int likespredicted=0;
            double threshold=0.7;
            for (String k : prob.keySet()) {
                if(prob.get(k)>threshold)
                	likespredicted++;
            }
            System.out.println(likespredicted);
            
        }
        
       
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //System.out.println("Count of my friends: " + myFriends.getData().size());
        //System.out.println("First item in my feed: " + myFeed.getData().get(0));
        /*
        for(i=0;i<myFriends.getData().size();i++)
        {
        	System.out.println(myFriends.getData().get(i));
        }
        */
        // Connections support paging and are iterable
        /*
        System.out.println("Starts Here");
        for (List<Post> myFeedConnectionPage : myFeed)
          for (Post post : myFeedConnectionPage)
            System.out.println("Post: " + post);
        */
        //Connection<Insight> insights = facebookClient.fetchConnection("40796308305/insights", Insight.class);
        /*
        for (Insight insight : insights.getData())
          System.out.println(insight.getName());
        
        Connection<User> myFriends1 = facebookClient.fetchConnection("me/friends", User.class);
        Connection<Post> myFeed1 = facebookClient.fetchConnection("me/feed", Post.class);
       
        
        System.out.println("Count of my friends: " + myFriends1.getData().size());
        System.out.println("First item in my feed: " + myFeed1.getData().get(0));
        */
        // Connections support paging and are iterable
        /*
        for (List<Post> myFeedConnectionPage : myFeed1)
          for (Post post : myFeedConnectionPage)
            System.out.println("Post: " + post);
        */
     }
        
        

        
       
    }
