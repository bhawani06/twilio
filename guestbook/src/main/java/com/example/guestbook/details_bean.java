package com.twilio;
import java.io.*;
public class details_bean implements java.io.Serializable {
private static String phNo;
private static String delayTime;
private static String output;
public static void setPhone(String ph)
{
phNo=ph;
}

public static String getPhone()
{
return phNo;
}

public static void setDelay(String delay)
{
delayTime=delay;
}

public static String getDelay()
{
return delayTime;
}

public static void setOutput(String buz)
{
output=buz;
}

public static String getOutput()
{
return output;
}
}