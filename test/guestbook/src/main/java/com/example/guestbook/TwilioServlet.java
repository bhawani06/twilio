package com.twilio;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 import com.twilio.sdk.verbs.Gather;
  import com.twilio.sdk.verbs.Pause;
import java.io.IOException;
import java.io.*; 
import com.twilio.sdk.verbs.TwiMLResponse;
import com.twilio.sdk.verbs.TwiMLException;
import com.twilio.sdk.verbs.Say;
 
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.factory.CallFactory;
import java.lang.Thread.*;
 import java.lang.*;
import com.twilio.sdk.verbs.Dial;
import com.twilio.sdk.verbs.Client;
import com.twilio.sdk.verbs.Number;
import java.util.Map;
import java.util.HashMap;

import com.twilio.sdk.resource.list.CallList;
public class TwilioServlet extends HttpServlet {
public  String phNo;
public  String delayTime;
public static final String ACCOUNT_SID = "AC9815f99e2beb89c1b085cb7ef2df4025";
public static final String AUTH_TOKEN = "ebca500507597c64e3b893edb4c52d23";
//details_bean obj2=new details_bean();
public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		delayTime = request.getParameter("delay");
		
		//delayTime1=delayTime;//to pass to db
		/*try {
		Thread.sleep(Integer.parseInt(delayTime) * 1000);         
		//1000 milliseconds = 1 second.
		} catch(Exception ex) {
		System.out.println("DD");
		}*/
		doPost(request,response);
        TwiMLResponse twiml = new TwiMLResponse();		
        String fromNumber = request.getParameter("From");
		Say say = new Say("Hello User . Please enter a number followed by pound key to play PhoneBuzz");
        Gather gather = new Gather();
		gather.setAction("/handle-key");
		gather.setFinishOnKey("#");
		gather.setMethod("POST");		
        try {
            twiml.append(say);
			twiml.append(gather);
			
        } catch (TwiMLException e) {
            e.printStackTrace();
        } 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());
	
	//	response.setContentType("text/html");
	//	response.getWriter().println("Enjoy your phone buzz.<hr><br> \n\n");
		
    }
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
  		
		try{
		
		TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);
        Account mainAccount = client.getAccount();
        CallFactory callFactory = mainAccount.getCallFactory();
        Map<String, String> callParams = new HashMap<String, String>();
		phNo = request.getParameter("guestbookName");
		delayTime=request.getParameter("delay");
		details_bean.setPhone(phNo.toString());
		details_bean.setDelay(delayTime.toString());
        try {
		Thread.sleep(Integer.parseInt(delayTime) * 1000);         
		//1000 milliseconds = 1 second.
		} catch(Exception ex) {
		}
		callParams.put("To", phNo); 		
        callParams.put("From", "(334) 422-8836"); 
        callParams.put("Url", "https://red-bruin-123409.appspot.com/twiml");
        Call call = callFactory.create(callParams);
       }
		catch(Exception e){
		System.out.println("S");}
		
		}
		
}
