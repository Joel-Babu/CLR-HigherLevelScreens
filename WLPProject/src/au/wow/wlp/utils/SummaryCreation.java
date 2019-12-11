package au.wow.wlp.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.annotations.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class SummaryCreation extends TestBase {

	
	public static int tcCount = 0;

	@Test
	public static void create() throws IOException, SAXException,
			ParserConfigurationException {
		//ArrayList<String> reportList = new ArrayList<String>();
		/*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		File fXmlFile = new File(TestContext.getStringProperty("xmlLoc"));
		String className = "";
		String val = "";
		String[] nameList = null;
		Document document = builder.parse(fXmlFile);
		Element rootElement = document.getDocumentElement();
		for (int j = 0; j < 10; j++) {
			try {
				NodeList nodeList = rootElement.getElementsByTagName("classes")
						.item(j).getChildNodes();
				

				// get the immediate child (1st generation)
				for (int i = 0; i < nodeList.getLength(); i++)
					switch (nodeList.item(i).getNodeType()) {
					case Node.ELEMENT_NODE:

						Element element = (Element) nodeList.item(i);
						// System.out.println("element name: " +
						// element.getNodeName());
						// check the element name
						if (element.getNodeName().equalsIgnoreCase("class")) {

							className = element.getAttribute("name");
							nameList = className.split("\\.");
							for (int k = 0; k < nameList.length; k++) {
								val = nameList[k];
							}
							System.out.println(val);
							reportList.add(val);
						}
						break;

					}
			} catch (Exception e) {
				System.out.println("Exception");
				break;
			}
		}*/
		File file = new File(reportPath +"\\"+"Summary.html");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write("<html>");
		writer.write("<head>");
		writer.write("<meta http-equiv= Content-Language content= en-us>");
		writer.write("<meta http-equiv= Content-Type content= text/html; charset=windows-1252>");
		writer.write("<title>Test Suite- " + "Head" + "</title>");
		writer.write("</head>");
		writer.write("<body>");
		writer.write("<blockquote>");
		writer.write("<table align=center border= 1 bordercolor=#000000 id=table1 width=80% height=35>");
		
		writer.write("<tr bgcolor = #FDFEFE>");
		writer.write("<td COLSPAN = 4>");
		writer.write("<p align=center><b><font color=#000080 size=4 face='Trebuchet MS'>"
				+ "&nbsp;"
				+ "CLR Automation Report &nbsp;&nbsp;"
				+ ""
				+ "&nbsp;");
		writer.write("</td>");
		writer.write("</tr>");
		writer.write("</table>");

		writer.write("<table align=center border= 1 bordercolor=#000000 id=table2 width=80% height=35>");
		writer.write("<tr bgcolor = #FDFEFE>");
		writer.write("<td COLSPAN = 6>");
		writer.write("<p align=center><b><font color=#000080 size=2.5 face='Trebuchet MS'>"
				+ "&nbsp;"
				+ "Start Time :&nbsp;&nbsp;"
				+ TestBase.startTime
				+ " End Time :&nbsp;&nbsp;" + TestBase.endTime + "&nbsp;");
		writer.write("</td>");
		writer.write("</tr>");
		writer.write("</td>");
		writer.write("</tr>");
		/*writer.write("</table>");

		writer.write("<table align=center border= 1 bordercolor=#000000 id=table2 width=80% height=35>");
		*//*writer.write("<tr bgcolor = #FDFEFE>");
		writer.write("<td COLSPAN = 6>");
		writer.write("<p align=center><b><font color=#000080 size=2 face='Trebuchet MS'>"
				+ "&nbsp;"
				+ "Total Scripts :&nbsp;&nbsp;"+54
				+ "&nbsp;");
		writer.write("</td>");
		writer.write("</tr>");*/
		writer.write("<tr bgcolor= #006600>");
		writer.write("<td width= 3%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5 >"
				+ "S.No" + "</b></td>");
		writer.write("<td width= 10%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5>"
				+ "Module Name" + "</b></td>");
		writer.write("<td width= 3%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5>"
				+ "Manual TC Count" + "</b></td>");
		writer.write("<td width= 3%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5>"
				+ "Total Scripts" + "</b></td>");
		writer.write("<td width= 3%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5>"
				+ "PASS Count" + "</b></td>");
		writer.write("<td width= 3%");
		writer.write("<p align= center><b><font color = #FFFFFF face= 'Trebuchet MS' size=2.5>"
				+ "FAIL Count" + "</b></td>");
		writer.write("</tr>");

		int ct = 1;
		int scriptCt = 0;
		int scriptPassed = 0;
		int scriptFailed = 0;
		for (String listName : classList) {
			try{

			File f = new File(reportPath+"//" + listName + ".html");
			if (f.exists()) {
				if (null != sceCtMap.get(listName + "_totalCt")) {
					scriptCt = scriptCt + sceCtMap.get(listName + "_totalCt");
				} else {
					sceCtMap.put(listName + "_totalCt", 0);
				}
				if (null != sceCtMap.get(listName + "_passCt")) {
					scriptPassed = scriptPassed
							+ sceCtMap.get(listName + "_passCt");
				} else {
					sceCtMap.put(listName + "_passCt", 0);

				}
				if (null != sceCtMap.get(listName + "_failCt")) {
					scriptFailed = scriptFailed
							+ sceCtMap.get(listName + "_failCt");
				} else {
					sceCtMap.put(listName + "_failCt", 0);
				}
				//writer.write("<table align=center border= 1 bordercolor=#000000 id=table2 width=80% height=35>");// added now
				writer.write("<tr bgcolor = #FDFEFE>");// added now
				writer.write("<td width=3%>");
				writer.write("<p align=center><font face='Trebuchet MS' size=2 color= #000080><b>"
						+ ct + "</b></td>");
				ct++;
				writer.write("<td width=10%>");
				writer.write("<p align=left>&nbsp;<a href='"
						+ "./"
						+ listName
						+ ".html' style='text-decoration: none'><font color= #000080 size=2 face= 'Trebuchet MS'><b>"
						+ listName + "</b></font></a></td>");
				writer.write("<td width=3%>");
				writer.write("<p align=center><font face='Trebuchet MS' size=2 color= #000080><b>"
						+ tcCt.get(listName) + "</b></td>");

				writer.write("<td width=3%>");
				writer.write("<p align=center><font face='Trebuchet MS' size=2 color= #000080><b>"
						+ sceCtMap.get(listName + "_totalCt") + "</b></td>");

				writer.write("<td width=3%>");
				writer.write("<p align=center><font face='Trebuchet MS' size=2 color= #000080><b>"
						+ sceCtMap.get(listName + "_passCt") + "</b></td>");

				writer.write("<td width=3%>");
				writer.write("<p align=center><font face='Trebuchet MS' size=2 color= #000080><b>"
						+ sceCtMap.get(listName + "_failCt") + "</b></td>");
				writer.write("</tr>");

				tcCount=tcCount+tcCt.get(listName);
			}
			}	catch(Exception e){
				
			}

		}
		long exeHr = 0;
		long exeMin = 0;
		long exeSec = 0;
		if (null != TestBase.startTime && null != TestBase.endTime) {
			
			

			// Custom date format
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  

			Date d1 = null;
			Date d2 = null;
			try {
			    d1 = format1.parse(TestBase.startTime);
			    d2 = format1.parse(TestBase.endTime);
			} catch (ParseException e) {
			    e.printStackTrace();
			}    

			// Get msec from each, and subtract.
			long diff = d2.getTime() - d1.getTime();
			exeSec = (diff / 1000)%60;         
			exeMin = (diff / (60 * 1000))%60;         
			exeHr = diff / (60 * 60 * 1000)%24; 			
			
			
			
		
		}
		writer.write("<tr bgcolor = #FDFEFE>");
		writer.write("<td COLSPAN = 6>");
		writer.write("<p align=center><b><font color=#000080 size=2 face='Trebuchet MS'>"
				+ "&nbsp;"
				+ "Total Execution Time :&nbsp;"
				+ exeHr
				+ " hour, "
				+ exeMin
				+ " minutes, "
				+ exeSec
				+ " seconds."
				+ " &nbsp;");
		writer.write("</td>");
		writer.write("</tr>");
		writer.write("<tr bgcolor =#FDFEFE>");
		writer.write("<td colspan= 6 align=right>");
		
		writer.write("<table>");
		writer.write("<tr><td>");
		writer.write("<table width=350 border=0 cellspacing =1 cellpadding=1>");
		/*writer.write("<tr><td width=30% height=15 align=right><b><font color= #000080 face= 'Trebuchet MS' size=1>"
				+ "Total Automated Manual Scripts "
				+ "</td></font><td width=35% height=15><b><font color= #000080 face= Verdana size=1>: &nbsp;&nbsp;"
				 + "</font></td></b></tr>");*/
		writer.write("<tr><td width=30% height=15 align=right><b><font color= #000080 face= 'Trebuchet MS' size=1>"
				+ "Total Manual Scripts Executed "
				+ "</td></font><td width=35% height=15><b><font color= #000080 face= Verdana size=1>: &nbsp;&nbsp;"
				+ tcCount + "</font></td></b></tr>");
		writer.write("<tr><td width=30% height=15 align=right><b><font color= #000080 face= 'Trebuchet MS' size=1>"
				+ "Total # of Scripts "
				+ "</td></font><td width=35% height=15><b><font color= #000080 face= Verdana size=1>: &nbsp;&nbsp;"
				+ scriptCt + "</font></td></b></tr>");
		writer.write("<tr><td width=30% height=15 align=right><b><font color= green face= 'Trebuchet MS' size=1>"
				+ "# of Test Scripts Passed"
				+ "</font></td><td width=35% height=15><b><font color= green face= Verdana size=1>: &nbsp;&nbsp;"
				+ scriptPassed + "</font></td></b></tr>");
		writer.write("<tr><td width=30% height=15 align=right><b><font color= #FF3333 face= 'Trebuchet MS' size=1>"
				+ "# of Test Scripts Failed"
				+ "</font></td><td width=35% height=15><b><font color= ff3333 face= Verdana size=1>: &nbsp;&nbsp;"
				+ scriptFailed + "</font></b></td></tr>");
		writer.write("</table>");
		writer.write("</td></tr>");
		/*
		writer.write("<table width=350 border=0 cellspacing =1 cellpadding=1>");
		writer.write("<p align=center><tr><td width=30% height=15 align=right><b><font color= #000080 face= 'Trebuchet MS' size=2.5>"
				+ "Total # Manul Testcase : "+tcCount+"</b></tr>");
		writer.write("<p align=center><tr><td width=30% height=15 align=right><b><font color= #000080 face= 'Trebuchet MS' size=2.5>"
				+ "Total # of Scripts : "+scriptCt+"</b></tr>");				
		writer.write("<p align=center><tr><td width=30% height=15 align=right><b><font color= green face= 'Trebuchet MS' size=2.5>"
				+ "# of Test Scripts Passed : "+scriptPassed+"</b></tr>");				
		writer.write("<p align=center><tr><td width=30% height=15 align=right><b><font color= #FF3333 face= 'Trebuchet MS' size=2.5>"
				+ "# of Test Scripts Failed : "+scriptFailed+"</b></tr>");		*/		
		writer.write("</table>");
		writer.write("</td>");
		writer.write("</tr>");
		writer.write("</blockquote>");
		writer.write("</body>");
		writer.write("</html>");
		writer.close();
	}

	protected String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();

			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}

		return null;
	}
}