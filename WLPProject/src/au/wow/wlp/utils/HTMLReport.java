package au.wow.wlp.utils;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebElement;

import bsh.commands.dir;

public class HTMLReport {

	public static BufferedWriter writer;
	public static File reportPath;
	public static String moduledesc;
	public static String dtmoduledesc;
	public static int strStepNo=0;
	public static int inSumPass =0;
	public static int inSumFail=0;
	public static int inSumStep=0;
	public static int inSumStep1=0;
	
	public static String stockTakeArttestname;
	
	
	public static int inPassCnt;
	public static int inFailCnt;
	public static Date dateex;
	public static Date testcasesttime;
	public static String store,user,pwd,eng1,database1,host1;
	public static String stStartSuite;
	
public static void Report_Header(String ReportType, File reportPath, String module, String dat) throws IOException{
	switch (ReportType){
	case "Summary":
		HR_Header_Summary(reportPath, ReportType, module, dat);
		break;
	case "testcase":
		HR_Header_TestCase(reportPath, module, dat);
		break;
	} 
}

public static void Report_AddStep_Equal(String module, String actual,String expected,String message) throws IOException{
	if (actual.equalsIgnoreCase(expected)) {
		Report_AddStep("testcase", "Equal", actual, expected, "Pass");

	} else {
		Report_AddStep("testcase", "Equal", actual, expected, "Fail");
	}
}

public static void Report_AddStep_Contains(String module, String actual,String expected,String message) throws IOException{
	if (actual.contains(expected)) {
		Report_AddStep("testcase", "Contains", actual, expected, "Pass");

	} else {
		Report_AddStep("testcase", "Contains", actual, expected, "Fail");
	}
}


public static void Report_AddStep(String ReportType, String desc, String actual, String expected, String status) throws IOException{
switch(ReportType){
	case "summary":
			  HR_Steps_Summary(reportPath);
			  break;
	case "testcase":
			  HR_Steps_TestCase(reportPath, actual, expected, desc, status);
			  break;
}
}




public static void Report_Footer(String ReportType) throws IOException, ParseException{
	switch (ReportType){
		case "summary":
				HR_Footer_Summary(reportPath);
				break;
		case "testcase":
				HR_Footer_TestCase(reportPath);
				break;
	}
}


public static void HR_Steps_TestCase (File f, String act, String exp, String ShortDesc, String status) throws IOException
{
	
	File file = new File(f +"\\"+ moduledesc +".html");
	writer = new BufferedWriter(new FileWriter(file,true));
	strStepNo = Func_LeftPad(strStepNo, 1);
	if(strStepNo % 2 == 0){
		writer.write("<tr bgcolor = DCF0F0>");			//writer.write("<tr bgcolor = aliceblue>")
	}else{
		writer.write("<tr bgcolor = #CCFFFF>");					//writer.write("<tr bgcolor = Azure>")	
	}

	writer.write("<td width=10%>");
	writer.write("<p align=center><font face=Verdana size=2>"+  strStepNo + "</td>");

	writer.write("<td width=60%>");
	writer.write("<p align=left><font face=Verdana size=2>" + ShortDesc + "</td>");
	
	writer.write("<td width=10%>");
	writer.write("<p align=left><font face=Verdana size=2>"+ act +"</td>");
	
	writer.write("<td width=10%>");
	writer.write("<p align=left><font face=Verdana size=2>"+ exp +"</td>");
	writer.write("<td height=23 width=10%>");
	
	if(status == "Fail"){ 
			inFailCnt = inFailCnt +1;
            writer.write("<p align = center><b><a href='"+ captureScreenShot() +"'><font face= Verdana  size= 2 color= #FF0000>" + status + "</font></b></td>");
	}else{
			inPassCnt = inPassCnt +1;
			writer.write("<p align = center><b><font face= Verdana size= 2 color= #008000>" + status + "</font></b></td>");
	}
	writer.write("</tr>");
	writer.close();
}

public static void HR_Steps_Summary (File f) throws IOException{
	
	File file = new File(f +"\\Summary.html");
	writer = new BufferedWriter(new FileWriter(file,true));
	
	inSumStep1 = Func_LeftPad(inSumStep1, 1);
	if(inSumStep1 % 2 == 0){
		writer.write("<tr bgcolor = DCF0F0>");			//writer.write("<tr bgcolor = aliceblue>")
	}else{
		writer.write("<tr bgcolor = #CCFFFF>");					//writer.write("<tr bgcolor = Azure>")	
	}
	writer.write("<td width=10%>");
	writer.write("<p align=center><font face=Verdana size=2>"+  inSumStep1 + "</td>");
	writer.write("<td width=20%>");
//'	writer.write("<p align=left>&nbsp;<a href='"+ ptMdlPath & TestCaseNo &".html'><font color= #000080 size=2 face= Verdana>"& TestCaseNo &"</font></a></td>")
	//f +"\\"+ Heading +".html"
	writer.write("<p align=left>&nbsp;<a href='" + "./" + moduledesc + ".html'><font color= #000080 size=2 face= Verdana>"+ moduledesc +"</font></a></td>");
//'ptTimeStamp	'..\2-7-2014_5.22.24 AM\Modules\SEARCH.html
	writer.write("<td width=60%>");
	writer.write("<p align=left><font face=Verdana size=2>" + dtmoduledesc + "</td>");
	writer.write("<td height=23 width=10%>");

	if(inFailCnt != 0){
		inSumFail = inSumFail +1;
		writer.write("<p align = center><b><font face= Verdana  size= 2 color= #FF0000>" + "FAIL" + "</font></b></td>");
	}else if(inPassCnt != 0){
		inSumPass = inSumPass +1;
		writer.write("<p align = center><b><font face= Verdana size= 2 color= #008000>" + "PASS" + "</font></b></td>");
	}

	writer.write("</tr>");
	writer.close();
}

public static void HR_Header_Summary (File f, String reportType, String storeNum, String DateTime) throws IOException
{
	
	if(!f.exists())
		f.mkdirs();
	
	 for (File file: f.listFiles()) {
	        if (!file.isDirectory()) file.delete();
	    }
	 
	 File fscreen = new File(f + "\\Screenshots");
	 if(!fscreen.exists())
		 fscreen.mkdirs();
		
		 for (File file: fscreen.listFiles()) {
		        if (!file.isDirectory()) file.delete();
		    }
	 
	File file = new File(f +"\\Summary.html");
	if(!file.exists())
	    file.createNewFile();
	writer = new BufferedWriter(new FileWriter(file));
	//Set fleHtml =f.CreateTextFile(ReportPath + "Summary-Reports.html", True);
	writer.write("<html>");
	writer.write("<head>");
	writer.write("<meta http-equiv= Content-Language content= en-us>");
	writer.write("<meta http-equiv= Content-Type content= text/html; charset=windows-1252>");
	writer.write("<title>Test Suite- " + DateTime  + "</title>");
	writer.write("</head>");
	writer.write("<body>");
	writer.write("<blockquote>");
	//writer.write("<p align=center><img src='" & Environment("LogoFile") &  "' alt= PRODUCT_LOGO></p>");
	writer.write("<table align=center border=1 bordercolor=#000000 id=table1 width=80% height=35>");
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 4>");
	writer.write("<p align=center><font color=#000080 size=4 face= Verdana> CLR Automation Summary Report </font></p>");  
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("</table>");
	writer.write("<table align=center border=1 bordercolor=#000000 id=table2 width=80% height=35>");
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 4 >");
	writer.write("<p align=center><b><font color=#000080 size=2 face=Verdana>"+ "&nbsp;"+ "Date & Time :&nbsp;&nbsp;" +  DateTime  + "&nbsp;");
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("<tr bgcolor= #1560BD>");
	writer.write("<td width= 10%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size= 2 >" + "S.No" + "</b></td>");
	writer.write("<td width= 20%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size=2>" + "Module Name" + "</b></td>");
	writer.write("<td width=60%>");
	writer.write("<p align=center><b><font color = #FFFFFF face= Verdana size= 2>" + "Short Description" + "</b></td>");
	writer.write("<td width=10%>");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana size= 2>" + "Status" +"</b></td>");
	writer.write("</tr>");
	//
	/*writer.write("</body>");
	writer.write("</html>");*/
	writer.close();
}


public static void HR_Header_TestCase (File f, String Heading, String DateTime) throws IOException{
	strStepNo = 0;
	inPassCnt = 0;
	inFailCnt = 0;
	if(!f.exists())
		f.mkdirs();
	File file = new File(f +"\\"+ Heading +".html");
	if(!file.exists())
	    file.createNewFile();
	writer = new BufferedWriter(new FileWriter(file));
	writer.write("<html>");
	writer.write("<head>");
	writer.write("<meta http-equiv= Content-Language content= en-us>");
	writer.write("<meta http-equiv= Content-Type content= text/html; charset=windows-1252>");
	writer.write("<title>Test Suite- " + DateTime  + "</title>");
	writer.write("</head>");
	writer.write("<body>");
	writer.write("<blockquote>");
	//'writer.write("<p align=center><img src='" & Environment("LogoFile") &  "' alt= PRODUCT_LOGO></p>")
	writer.write("<table align=center border=1 bordercolor=#000000 id=table1 width=80% height=35>");
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 4>");
	writer.write("<p align=center><font color=#000080 size=4 face= Verdana>" + Heading + "</font></p>");  
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("</table>");
	writer.write("<table align=center border=1 bordercolor=#000000 id=table2 width=80% height=35>");
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 4 >");
	writer.write("<p align=center><b><font color=#000080 size=2 face=Verdana>"+ "&nbsp;"+ "Date & Time :&nbsp;&nbsp;" +  DateTime  + "&nbsp;");
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("<tr bgcolor= #1560BD>");
	writer.write("<td width= 10%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size= 2 >" + "S.No" + "</b></td>");
	/*writer.write("<td width= 20%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size=2>" + "Test Case #" + "</b></td>");*/
	writer.write("<td width=60%>");
	writer.write("<p align=center><b><font color = #FFFFFF face= Verdana size= 2>" + "Short Description" + "</b></td>");
	writer.write("<td width= 10%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size=2>" + "" + "</b></td>");
	writer.write("<td width= 10%");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana  size=2>" + "" + "</b></td>");
	writer.write("<td width=10%>");
	writer.write("<p align= center><b><font color = #FFFFFF face= Verdana size= 2>" + "Status" +"</b></td>");
	writer.write("</tr>");
	writer.write("</blockquote>");
	writer.write("</body>");
	writer.write("</html>");
	writer.close();
}

public static int Func_LeftPad (int strText, int intLen)						// Padding a given character to String
{

	int a =  strText+ intLen;
	return a;
}

public static void HR_Footer_TestCase(File f) throws IOException, ParseException{

	File file = new File(f +"\\"+ moduledesc +".html");
		writer = new BufferedWriter(new FileWriter(file,true));

	int strTotalSteps= strStepNo;
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 5 >");
	Date dateendtime = new Date();
	writer.write("<p align=center><b><font color=#800080 size=2 face= Verdana> " + "Test case Execution Time : "  + TimeDiff(testcasesttime, dateendtime) + "&nbsp;");
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("<tr bgcolor =aliceblue>");
	writer.write("<td colspan= 5 align=right>");
	writer.write("<table width=350 border=0 cellspacing =1 cellpadding=1>");
	writer.write("<tr><td width=30% height=15 align=right><b><font color= #000080 face= Verdana size=1>" + "Total # of steps" +"</td></font><td width=35% height=15><b><font color= #000080 face= Verdana size=1>: &nbsp;&nbsp;"+ Func_LeftPad(strTotalSteps,0) +"</font></td></b></tr>");
	writer.write("<tr><td width=30% height=15 align=right><b><font color= green face= Verdana size=1>"+ "# of steps Passed"+"</font></td><td width=35% height=15><b><font color= green face= Verdana size=1>: &nbsp;&nbsp;"+ Func_LeftPad(inPassCnt ,0) +"</font></td></b></tr>");
	writer.write("<tr><td width=30% height=15 align=right><b><font color= #FF3333 face= Verdana size=1>" + "# of steps Failed"+"</font></td><td width=35% height=15><b><font color= ff3333 face= Verdana size=1>: &nbsp;&nbsp;"+ Func_LeftPad(inFailCnt ,0) +"</font></b></td></tr>");
	writer.write("</table>");
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("</Body>");
	writer.write("<table align=center border=0 width=80% height=31>");
	  
	writer.write("<td width=40% align=right><a href='./Summary.html'><font color= #000080 size=2 face= Verdana><b>" + "«Summary Report" + "</b></a></font></td></tr>");
	
	writer.write("</table>");
	writer.write("</html>");
	writer.close();
}



public static void HR_Footer_Summary (File f) throws IOException, ParseException{
	int innerLoop;					    
	int strTotalTestCase;
	File file = new File(f +"\\Summary.html");
	writer = new BufferedWriter(new FileWriter(file,true));
	
	//sumedDate = Now()	
	writer.write("<tr bgcolor = aliceblue>");
	writer.write("<td COLSPAN = 4 >");
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date2 = new Date();
	writer.write("<p align=center><b><font color=#800080 size=2 face= Verdana> " + "Total Execution Time : "  + TimeDiff(dateex, date2) + "&nbsp;");
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("<tr bgcolor =aliceblue>");
	writer.write("<td colspan= 4 align=right>");
	writer.write("<table width=350 border=0 cellspacing =1 cellpadding=1>"); 
	writer.write("<tr><td width=30% height=15 align=right><b><font color= #000080 face= Verdana size=1>" + "Total # of Test Cases" +"</td></font><td width=35% height=15><b><font color= #000080 face= Verdana size=1>: &nbsp;&nbsp;"+  inSumStep1 +"</font></td></b></tr>");
	writer.write("<tr><td width=30% height=15 align=right><b><font color= green face= Verdana size=1>"+ "# of Test Cases Passed" +"</font></td><td width=35% height=15><b><font color= green face= Verdana size=1>: &nbsp;&nbsp;"+ Func_LeftPad(inSumPass, 0) +"</font></td></b></tr>");
	writer.write("<tr><td width=30% height=15 align=right><b><font color= #FF3333 face= Verdana size=1>" + "# of Test Cases Failed" +"</font></td><td width=35% height=15><b><font color= ff3333 face= Verdana size=1>: &nbsp;&nbsp;"+ Func_LeftPad(inSumFail, 0) +"</font></b></td></tr>");
	writer.write("</table>");
	writer.write("</td>");
	/*writer.write("<table align=center id=table3 width=80% height=25>");
	writer.write("<tr>");
	writer.write("<td COLSPAN = 4>");
//	writer.write("<p align=center><font color=#000080 size=2 face= "& chr(34)&"Copperplate Gothic Bold"&chr(34) & ">&nbsp;" & Chr(169) & "Tata Consultancy Limited,Chennai" & "</font> </p>");  
	writer.write("</td>");
	writer.write("</tr>");
	writer.write("</table>");*/
	writer.write("</tr>");
	writer.write("</blockquote>");
	writer.write("</Body>");
	writer.write("</HTML>");
	writer.close();	
	}

public static String TimeDiff(Date date2 , Date date22) throws ParseException{

//	String dateStart = "01/14/2012 09:29:58";
//	String dateStop = "01/15/2012 10:31:48";
//
//	//HH converts hour in 24 hours format (0-23), day calculation
//	SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
//
//	Date d1 = null;
//	Date d2 = null;
//
//	
//		d1 = format.parse(date2);
//		d2 = format.parse(date22);

		//in milliseconds
		long diff = date22.getTime() - date2.getTime();

		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");
		return diffHours + " hours, " + diffMinutes + " minutes, " +  diffSeconds + " seconds.";
}


public static String captureScreenShot() throws IOException {
	String time = null;
	  try{
		  
	       // Thread.sleep(10000);
	        long id = Thread.currentThread().getId();
	        BufferedImage image = new Robot().createScreenCapture(new Rectangle(
	            Toolkit.getDefaultToolkit().getScreenSize()));
	        time= GetTimeStampValue();
	        ImageIO.write(image, "png", new File(reportPath+"\\Screenshots\\"+time+".png"));
	    }
	    catch( Exception e ) {
	        e.printStackTrace();
	    }
	  	return "./Screenshots/"+time+".png";
	  }

public static  String GetTimeStampValue()throws IOException{
  Calendar cal = Calendar.getInstance();       
  Date time=cal.getTime();
  String timestamp=time.toString();
  System.out.println(timestamp);
  String systime=timestamp.replace(":", "-");
  systime=systime.replace(" ", "");
  System.out.println(systime);
  return systime;
}

}