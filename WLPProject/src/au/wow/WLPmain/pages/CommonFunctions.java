package au.wow.WLPmain.pages;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;

import au.wow.wlp.utils.TestBase;

public class CommonFunctions extends TestBase{
	List<List<Object>> results;
	
	/**
	 * Returns promotion weekdate to return the articles from DB 
	 * @param promofor
	 * @return promotion weekdate
	 */
	public String getWeekDateForPromotion(String promofor){
		String returndate=null;
		Calendar cal=null;
		switch (promofor) {
		case "Current Week":
			returndate=formatDate(new Date());
			break;
		case "Next Week":
			returndate=formatDate(dateAdd().getTime());
			break;
		case "Two Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 7);
			returndate=formatDate(cal.getTime());
			break;
		case "Three Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 14);
			returndate=formatDate(cal.getTime());
			break;
		case "Four Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 21);
			returndate=formatDate(cal.getTime());
			break;
		case "Five Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 28);
			returndate=formatDate(cal.getTime());
			break;
		default:
			break;
		}
		System.out.println("Before Passing to query: " + returndate);
		return returndate;
	}
	
	/**
	 * Calculating weekday and adding days to retrieve the nearest wednesday
	 * @return date after adding x days
	 */
	public Calendar dateAdd(){
		Calendar date = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
		int currWeekDay = date.get(Calendar.DAY_OF_WEEK);
		System.out.println("Current Week day is"+currWeekDay);
		int diff = currWeekDay - Calendar.WEDNESDAY;
		int add = 0;
		System.out.println("diff>>>>>>>>>" + diff);
		if(diff<0){
			add = -diff;
		}else if(diff>0){
			add=7-diff;
		}else if(diff==0){
			add=7;
		}
		date.add(Calendar.DATE, add);
		System.out.println("After adding: " + date.toString());
		return date;
	}
	
	public Calendar dateAddTue(){
		Calendar date = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
		int currWeekDay = date.get(Calendar.DAY_OF_WEEK);
		System.out.println("Current Week day is"+currWeekDay);
		int diff = currWeekDay - Calendar.TUESDAY;
		int add = 0;
		System.out.println("diff>>>>>>>>>" + diff);
		if(diff<0){
			add = -diff;
		}else if(diff>0){
			add=7-diff;
		}else if(diff==0){
			add=7;
		}
		date.add(Calendar.DATE, add);
		System.out.println("After adding: " + date.toString());
		return date;
	}
	
	
	/**
	 * Formats the given date to dd/MM/yyyy format
	 * @param date
	 * @return
	 */
	public String formatDate(Date date){		
		return new SimpleDateFormat("dd/MM/yyyy").format(date);

	}
	
	public String AddDate(Date nDate,int daytoAdd) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		c.setTime(nDate); // Now use today date.
		c.add(Calendar.DATE, daytoAdd); // Adding 5 days
		return sdf.format(c.getTime());
	}
	/**
	 * Retrieves article num from RC DB
	 * @param query
	 * @return Articlenumber
	 * @throws SQLException 
	 */
	/*public String getTestDataFromDB(String query) throws SQLException{
		log.info("--------------Query the tables for the article-----------");
		results = sql.executeQuery(getContext(), query);
		if(results!=null){
			return (String) results.get(0).get(0);
		}else{
			throw new SkipException("No data available in RC for the matching criteria");
		}
	}*/

	public Date ConvertdateFormat(String inVendorNum) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
		Date dtrosterdate = df.parse(inVendorNum);
		return dtrosterdate;
	}

	public Date ConvertdateFormatddmmyy(String from_date) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dtrosterdate = df.parse(from_date);
		return dtrosterdate;
	}

	public String getWeekDateForPromotionTue(String promofor){
		String returndate=null;
		Calendar cal=null;
		switch (promofor) {
		case "Current Week":
			returndate=formatDate(new Date());
			break;
		case "Next Week":
			returndate=formatDate(dateAddTue().getTime());
			break;
		case "Two Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 7);
			returndate=formatDate(cal.getTime());
			break;
		case "Three Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 14);
			returndate=formatDate(cal.getTime());
			break;
		case "Four Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 21);
			returndate=formatDate(cal.getTime());
			break;
		case "Five Weeks Out":
			cal = dateAdd();
			cal.add(Calendar.DATE, 28);
			returndate=formatDate(cal.getTime());
			break;
		default:
			break;
		}
		System.out.println("Before Passing to query: " + returndate);
		return returndate;
	}
	
	/**
	 * @param results
	 * @return
	 */
	public int RandomFromDB(List<List<Object>> results) {
		int max = results.size();
		int min=1;
		Random rand=new Random();
		int num = rand.nextInt((max+1) - min) + min;
		return num-1;
	}
	
	
	
	public Object[] fnVendorWarHouNumber(List<List<Object>> results, String stVenWar) throws IOException{
		List<Object> result=null;
		for(int i=0;i<=results.size()-1;i++)
		{
			if(results.get(i).get(4).toString().length()>=7 && stVenWar.equalsIgnoreCase("Vendor") &&
					results.get(i).get(4).toString().length()!=1)
			{
				result = results.get(i);

				Report_AddStep("testcase", "Vendor Details "+ result,"","", "Pass");
				System.out.println("Into vendor functionality");
				break;
			}
			else if(results.get(i).get(4).toString().length()<=5 && stVenWar.equalsIgnoreCase("WareHouse") &&
					results.get(i).get(4).toString().length()!=1)
			{
				result =results.get(i);
				Report_AddStep("testcase", "WareHouse Details "+ result,"","", "Pass");
				break;
			}
			else{
				result = results.get(i);
			}
		}
		return result.toArray();
	}
	
	
	public List<WebElement> prepareWebElementWithDynamicXpath (String xpathValue, String substitutionValue ) {
		return driver1.findElements(By.xpath(xpathValue.replace("dynamic", substitutionValue)));
	}
	
	public WebElement prepareWebElementWithDynamicXpathWithInt(String xpathValue, int i) {
		return driver1.findElement(By.xpath(xpathValue.replace("dynamic", ""+i)));
	}
}