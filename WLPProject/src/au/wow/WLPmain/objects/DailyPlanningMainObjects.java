package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DailyPlanningMainObjects {
	
	public static final String listUOMRadio="//*[@id='uomRadio_pos']/p/input[dynamic]";
	
	@FindBy(tagName="body")
	public WebElement body;
	
	@FindBy(xpath= "//*[@id='form1:menu2']")
	public WebElement dropdonw;
	
	@FindBy(id= "form1:linkGo")
	public WebElement linkGo;
	
	@FindBy(xpath= "//*[@id='dailyForm:dd1Dailyplan']")
	public WebElement dailyplan;
	
	
	@FindBy(xpath= "//nobr[contains(text(),'CHECKOUT')]/../../td")
	public List<WebElement> checkouttot;
	
	
}	
