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
public class PhaseFourServlet extends HttpServlet {
public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
String output=details_bean.getOutput();
TwiMLResponse twiml = new TwiMLResponse();
        Say say = new Say(output);
		
	try {
			twiml.append(say);
        } catch (TwiMLException e) {
            e.printStackTrace();
        } 
        response.setContentType("application/xml");
        response.getWriter().print(twiml.toXML());

}

}