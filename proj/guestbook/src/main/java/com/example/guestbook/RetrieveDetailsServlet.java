package com.twilio;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import com.twilio.sdk.verbs.Gather;
  import com.twilio.sdk.verbs.Pause;
import java.io.IOException;
 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
 
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;

 import java.lang.*;
import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.Client;
import com.twilio.sdk.verbs.Number;
import java.util.Map;
import java.util.HashMap;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import com.twilio.sdk.verbs.Gather;
import java.io.*;
 import java.lang.*;
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
import java.util.Map;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.HashMap;
 import java.io.PrintWriter;
import java.io.File;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;
import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.CompositeFilter;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.twilio.sdk.resource.list.CallList;
import com.google.appengine.api.datastore.QueryResultList;
import com.twilio.sdk.resource.list.CallList;
public class RetrieveDetailsServlet extends HttpServlet {
public  String phNo;
public static final String ACCOUNT_SID = "AC9815f99e2beb89c1b085cb7ef2df4025";
public static final String AUTH_TOKEN = "ebca500507597c64e3b893edb4c52d23";

 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
 String summary ="";
 String out="<table border='3'><tr><th>Phone Number</th><th> Delay(seconds) </th><th> PhoneBuzz Number</th></tr>";
 String fin="</table>";
 phNo = request.getParameter("phone");
		//doPost(request,response);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		 try {
					Query q = new Query("PhonebuzzRetrieve1");
					int pageSize = 5;
					FetchOptions fetchOptions = FetchOptions.Builder.withLimit(pageSize);
					PreparedQuery pq = datastore.prepare(q);
					QueryResultList<Entity> results = pq.asQueryResultList(fetchOptions);
					for (Entity etity : results) {
					
					if((etity.getProperty("phoneNum").toString()).equals(phNo.toString())){
					  summary=summary+"<tr><td><a href='redirect?phoneNumber="+etity.getProperty("phoneNum").toString()+ "&number="+etity.getProperty("number").toString()+"&delay="+etity.getProperty("delay").toString()+"'>"+etity.getProperty("phoneNum").toString()+"</a></td><td>"+etity.getProperty("delay").toString()+"</td><td>"+etity.getProperty("number").toString()+"</td></tr>";
					 } 
					}
  
        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getWriter().println("Entry not found.");
        }
		if(summary=="")
		summary="No records found !! Please start making new calls";
		String t=out+summary+fin;
		/*
		TwiMLResponse twiml = new TwiMLResponse();
		Say say1 = new Say("PADMA");
		Say say2=new Say(summary);
		 try {
		  twiml.append(new Say("Welcome"));
		  twiml.append(say1);
		  twiml.append(say2);
		  }
		  catch (TwiMLException e) {
            e.printStackTrace();
        }*/
		  response.setContentType("text/html");
      response.getWriter().println("These are your previous phonebuzz logs. Click on any to play again.<hr><br> \n\n"+ t);
		//response.setContentType("application/xml");
        //response.getWriter().print(twiml.toXML());
		/*
        
		doPost(request,response);
       
        TwiMLResponse twiml = new TwiMLResponse();
		Say say1 = new Say("PADMA");
		//Say say = new Say("Hello Monkey");
		//String fromNumber = request.getParameter("phone");
		Say sayEnterNumber = new Say("Please enter a number to play phonebuzz");
       // Gather gather = new Gather();
			//gather.setAction("/handle-key");
		//gather.setFinishOnKey("#");
		//gather.setMethod("POST");
		  try {
		  twiml.append(new Say("Welcome"));
		  twiml.append(say1);
		  }
		  catch (TwiMLException e) {
            e.printStackTrace();
        }
		 
		response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
	*/
	
        }
			public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
  	
	  //return;
 
		/*try{
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
		phNo = request.getParameter("phone");
	    callParams.put("To", phNo); 		
        callParams.put("From", "(334) 422-8836"); 
        callParams.put("Url", "https://red-bruin-123409.appspot.com/retrieve");
        Call call = callFactory.create(callParams);
       }
		catch(Exception e){
		System.out.println("S");}*/
		
		
		
		}
		}