package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import javax.servlet.http.HttpServletResponse;
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

import com.twilio.sdk.resource.list.CallList;
public class RedirectServlet extends HttpServlet {
public String phNo="";
public String delay="";
public String number="";
public static final String ACCOUNT_SID = "AC9815f99e2beb89c1b085cb7ef2df4025";
public static final String AUTH_TOKEN = "ebca500507597c64e3b893edb4c52d23";

public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {


phNo=request.getParameter("phoneNumber");
delay=request.getParameter("delay");
number=request.getParameter("number");

		
		int num = -1;
		try {
			num = Integer.parseInt(number);
		}

		catch (Exception e) {
			System.out.println("Sorry : Not a valid number");
		}
		//String result = FizzBuzz.fizzBuzz(num);
		String output ="";
int i = 1;

for(i=1;i<=num;i++) {
	if (i % 3 == 0 && i % 5 == 0) {
		output=output+" FizzBuzz ";
	}

	else if (i % 3 == 0) {
		output=output+" Fizz ";
	}

	else if (i % 5 == 0) {
		output=output+" Buzz ";
	}

	else {
		output=output+" "+Integer.toString(i);
	}

	
}
		String res=output.toString();
	//String ada="123Fizz";
		details_bean.setOutput(output);
//response.setContentType("text/plain");
  //    response.getWriter().println("Hello, this is a testing servlesdfsdfsdfsdt kjjjjjjjjjjjjjjjjjjj service. \n\n"+ res);
	
		try {
		Thread.sleep(Integer.parseInt(delay) * 1000);         
		//1000 milliseconds = 1 second.
		} catch(InterruptedException ex) {
		Thread.currentThread().interrupt();
		}
		makeCall();
		output=" "+output;
		TwiMLResponse twiml = new TwiMLResponse();
        Say say = new Say("Hello Monkey"+output);
		Say sf=new Say(output);
		//Say say1=new Say("a"+res.toString());	
//Gather gather = new Gather();
//		gather.setAction("/phasefour");
	//	gather.setFinishOnKey("#");
	//	gather.setMethod("POST");			
        try {
			twiml.append(say);
		//	twiml.append(gather);
        } catch (TwiMLException e) {
            e.printStackTrace();
        } 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());

	
 }
 
 public void makeCall()
 {
 try{
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
		callParams.put("To", phNo); 		
        callParams.put("From", "(334) 422-8836"); 
		String url = "https://red-bruin-123409.appspot.com/redirect?phoneNumber="+phNo+"&number="+number+"&delay="+delay+"";
		//"https://red-bruin-123409.appspot.com/redirect?phoneNumber="+phNo+"&number="+number+"&delay="+delay+"
        //callParams.put("Url", "https://red-bruin-123409.appspot.com/redirect?phoneNumber=4807389486&number=4&delay=14");
        callParams.put("Url",url);
		//callParams.put("Url","https://red-bruin-123409.appspot.com/redirect");
		Call call = callFactory.create(callParams);
       }
		catch(Exception e){
		System.out.println("S");}
		
		}
		
		
		
 }
 
 
