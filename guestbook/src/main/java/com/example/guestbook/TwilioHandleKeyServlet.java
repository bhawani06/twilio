package com.twilio;
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
import java.util.*;
public class TwilioHandleKeyServlet extends HttpServlet {
public static final String ACCOUNT_SID = "AC9815f99e2beb89c1b085cb7ef2df4025";
public static final String AUTH_TOKEN = "ebca500507597c64e3b893edb4c52d23";
TwilioServlet obj=new TwilioServlet();

 public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
 
 
 
        String digits = request.getParameter("Digits");
        TwiMLResponse twiml = new TwiMLResponse();
		
		int num = -1;
		try {
			num = Integer.parseInt(digits);
		}

		catch (Exception e) {
			System.out.println("Sorry : Not a valid number");
		}
		//String result = FizzBuzz.fizzBuzz(num);

String output = "";
		int i = 1;

		for(i=1;i<=num;i++) {
			if (i % 3 == 0 && i % 5 == 0) {
				output+=" . FizzBuzz";
			}

			else if (i % 3 == 0) {
				output+=" . Fizz";
			}

			else if (i % 5 == 0) {
				output+=" . Buzz";
			}

			else {
				output+=" . "+Integer.toString(i);
			}


			
		}
		String res=output.toString();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Random ran = new Random();
		int x = ran.nextInt(1000) + 5;
		TwilioServlet tsObj = new TwilioServlet();
		details_bean obj2;
		Entity phonebuzzRetrieve1 = new Entity("PhonebuzzRetrieve1",x+"");
		phonebuzzRetrieve1.setProperty("phoneNum", details_bean.getPhone().toString());
		phonebuzzRetrieve1.setProperty("delay", details_bean.getDelay().toString());
		phonebuzzRetrieve1.setProperty("number", num+"");
		datastore.put(phonebuzzRetrieve1);
		
		
		//String e1=details_bean.getPhone()+"hui"+details_bean.getDelay();
		Say say1 = new Say(res);
		
		  try {
		  twiml.append(new Say("Welcome"));
		  twiml.append(say1);
		  }
		  catch (TwiMLException e) {
            e.printStackTrace();
        }
		 
	response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
	
	
        }
		
		}