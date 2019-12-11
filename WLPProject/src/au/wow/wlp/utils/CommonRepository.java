package au.wow.wlp.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("rawtypes")
public class CommonRepository {
        
        
      WebDriver driver;
      JavascriptExecutor executor;
      WebDriverWait wait;
        
        
        public CommonRepository(WebDriver driver){
               
               this.driver = driver;
               this.executor = (JavascriptExecutor)driver;
        }
        
        public void clickButton(WebDriver driver,By locator) throws InterruptedException{
            
     	   WebElement button = (WebElement)(new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(locator));
           button.click();
        
          
     }
        
        public void SwitchToFrame(WebDriver driver,By elementBy) throws Exception
    	{
    		try 
    		{
    			driver.switchTo().defaultContent();
    			if (driver.findElement(elementBy).isDisplayed()) 
    			{
    				WebElement frameName = driver.findElement(elementBy);
    				driver.switchTo().frame(frameName);
    			}
    		}
    		catch (Exception e) 
    		{
    			System.out.println("Generic Exception occured");
    		}
    	}
        
        public void JsclickButton(WebDriver driver,By locator) throws InterruptedException{
       
        	   WebElement button = (WebElement)(new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(locator));
               executor.executeScript("arguments[0].click();", button);
           
             
        }
        
        public void clickIcon(WebDriver driver,By locator) throws InterruptedException{
            
        	WebElement button = (WebElement)(new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(locator));
            executor.executeScript("arguments[0].click();", button);
        
          
     }
        
        public boolean isElementEnabled(WebDriver driver,By locator) throws InterruptedException{
        	boolean value=false;
        	
        	WebElement button =(new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(locator));
          if(button.getAttribute("disabled")==null)
          {
        	  value=true;
        	  
          }
        	return value;
        	
        }
        
        public void sendText(WebDriver driver,By locator, String text){
        	WebElement textBox = (WebElement) (new WebDriverWait(driver, 180)).until(ExpectedConditions.visibilityOfElementLocated(locator));
               textBox.sendKeys(text);
               
        }
        
        public void clearText(WebDriver driver,By locator){
        	WebElement textBox = (WebElement) (new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(locator));
            textBox.clear();
           
            
             
     }
        public void sendKeys(WebDriver driver,By locator, Keys key){
        	WebElement textBox = (WebElement) (new WebDriverWait(driver, 100)).until(ExpectedConditions.presenceOfElementLocated(locator));
              textBox.sendKeys(key);
            
             
           
     }
        
        public void clickLink(WebDriver driver,By locator) {
               WebElement linkElement= driver.findElement(locator);
                   executor.executeScript("arguments[0].click();",linkElement);
        }
        
         public void selectDropdown(WebDriver driver,By locator,String SelectBy,String value){
               Select dropDown=new Select(driver.findElement(locator));
               if(SelectBy.equalsIgnoreCase("VALUE")){
               dropDown.selectByValue(value);
               }
               else if(SelectBy.equalsIgnoreCase("VisibleText")){
                       dropDown.selectByVisibleText(value);
               }
                       
         }
         
         public boolean popupSelect(WebDriver driver,By locator,String reasonCode) throws InterruptedException
         {
        	 System.out.println("fdgf");
        	 boolean option=false;
        	 if(isElementPresent(driver,locator,10))
        	 {
        		
        		 WebElement reasonElement=(WebElement)(new WebDriverWait(driver, 240)).until(ExpectedConditions.presenceOfElementLocated(locator));
            	 executor.executeScript("arguments[0].scrollIntoView('true');",reasonElement); 
            	 List<WebElement> reasonSize= (List<WebElement>) reasonElement.findElements(By.tagName("li"));
            	 int l=0;
            	 for(int i=0;i<reasonSize.size();i++)
            	 { 
            		 String prop=((WebElement) reasonSize.get(i)).getAttribute("disabled");

            		 if(prop!=null)
            		 {
            			 l++;
            		 } 	
            		String test=reasonSize.get(i).getText();
            		 if(reasonSize.get(i).getText().equalsIgnoreCase(reasonCode))
            		 {
            			 option=true;
            			 int k=i-l;

            			 for(int j=k;j>0;j--)
            			 {
            				 reasonElement.sendKeys(Keys.DOWN);
            				 Thread.sleep(500);
            			 }
            			 break;
            		 }


            	 }

        	 }
        	 return option;

        	
         }
         
         public boolean popupContains(WebDriver driver,By locator,String reasonCode) throws InterruptedException
         {
        	 boolean option=false;
        	 if(isElementPresent(driver,locator,10))
        	 {
        		
        		 WebElement reasonElement=(WebElement)(new WebDriverWait(driver, 240)).until(ExpectedConditions.presenceOfElementLocated(locator));
            	 executor.executeScript("arguments[0].scrollIntoView('true');",reasonElement); 
            	 List<WebElement> reasonSize= (List<WebElement>) reasonElement.findElements(By.tagName("li"));
            	 int l=0;
            	 for(int i=0;i<reasonSize.size();i++)
            	 { 
            		 String prop=((WebElement) reasonSize.get(i)).getAttribute("disabled");

            		 if(prop!=null)
            		 {
            			 l++;
            		 } 	
            		String test=reasonSize.get(i).getText();
            		 if(reasonSize.get(i).getText().contains(reasonCode))
            		 {
            			 option=true;
            			 int k=i-l;

            			 for(int j=k;j>0;j--)
            			 {
            				 reasonElement.sendKeys(Keys.DOWN);
            				 Thread.sleep(500);
            			 }
            			 break;
            		 }


            	 }

        	 }
        	 return option;

        	
         }
         public boolean gridSelection(WebDriver driver,By locator,String option)
         {        
        	 boolean value=false;
        	 try{
        		
        	        //	 System.out.println("sdf");
        	        //	 List<MobileElement> searchlist= driver.findElements(locator);
        	        	 String opt="98183222031914705";
        	        	 WebElement searchlist=(WebElement)(new WebDriverWait(driver, 240)).until(ExpectedConditions.presenceOfElementLocated(locator));
        	        	 String path="//span[contains(text(),'"+option+"')]";
        	        	 
        	        	 WebElement spanText=searchlist.findElement(By.xpath(path));
        	        	 executor.executeScript("arguments[0].focus();",spanText);
        				 executor.executeScript("arguments[0].click();", spanText);
        	        	 
        	        	 value=true;
        	 }catch(org.openqa.selenium.NoSuchElementException e)
        	 {
        		 value=false;
        	 }
        	
        	 
        	/* MobileElement test=(MobileElement) driver.findElement(By.id("hdgrid-page-0"));
        	 boolean te=test.isDisplayed();
        	 MobileElement last=test.findElement(By.xpath("//span[contains(text(),'982356031228150010410')]"));
        	 executor.executeScript("arguments[0].focus();",last);
			 executor.executeScript("arguments[0].click();", last);
			 
        	 executor.executeScript("arguments[0].scrollIntoView('true');",last); 
        	 
        	 List<MobileElement> list1=test.findElements(By.xpath("//span[contains(text(),'98235603122815001040')]"));
        	 executor.executeScript("arguments[0].focus();",list1);
			 executor.executeScript("arguments[0].click();", list1);
			 
        	 int i=1;
        	 for(MobileElement e:list1)
        	 { i++;
        		//	driver.scrollTo(e.getText());
        		 executor.executeScript("arguments[0].focus();",e);
    			 executor.executeScript("arguments[0].click();", e);
    			 if(i==10)
    			 {
    				 executor.executeScript("arguments[0].scrollIntoView('true');",e); 
    				 
    			 }
        	//	String prop=e.getAttribute("disabled");
        		// MobileElement spanelement= (MobileElement) e.findElement(By.tagName("span"));
        		 System.out.println(e.getText());
        		 if(e.getText().equalsIgnoreCase(option))
        		 {
        			 executor.executeScript("arguments[0].focus();",e);
        			 executor.executeScript("arguments[0].click();", e);
        			 value=true;
        			 break;

        		 }*/
        	 
        	 return value;
         }
         
         public boolean gridSelection1(WebDriver driver,By locator,String option)
         {
        	 boolean value=false;
        	 List<WebElement> searchlist= driver.findElements(locator);

        	 for(WebElement e:searchlist)
        	 {
        		 WebElement spanelement= (WebElement) e.findElement(By.tagName("span"));
        		 System.out.println(spanelement.getText());
        		 if(spanelement.getText().equalsIgnoreCase(option))
        		 {
        			 executor.executeScript("arguments[0].focus();",spanelement);
        			 executor.executeScript("arguments[0].click();", spanelement);
        			 value=true;
        			 break;

        		 }
        	 }
        	 return value;
         }
         
         public boolean isgridContains(WebDriver driver,By locator,String option)
         {
        	 boolean value=false;
        	 List<WebElement> searchlist= driver.findElements(locator);

        	 for(WebElement e:searchlist)
        	 {
        		 WebElement spanelement= (WebElement) e.findElement(By.tagName("span"));
        		 System.out.println(spanelement.getText());
        		 if(spanelement.getText().equalsIgnoreCase(option))
        		 {
        			
        			 value=true;
        			 break;

        		 }
        	 }
        	 return value;
         }
         
     	public boolean compareLists(HashMap listFromDB, HashMap listFromScreen, String valueType){
     		boolean compare=false;
     		if(listFromDB.equals(listFromScreen))
     			{
     			
     			}
    		
			return compare;
    	}
         
    
         
         public boolean isElementPresent(WebDriver driver,By locator, int counter) throws InterruptedException
         	{ 
        	 boolean elementPresent=false;
        	 try{
        		
                  int noOfChecks=0;
                  while(!elementPresent)
                  {
                          elementPresent=driver.findElement(locator).isDisplayed();
                          Thread.sleep(1000);
                          noOfChecks++;
                          if(noOfChecks==counter)
                          {
                                  break;
                          }
                          
                   }
        	 }catch(Exception e)
        	 {
        		 System.out.println("Element is not displayed");
        	 }
             
               return elementPresent;
        }
         
         public boolean isTitlePresent(WebDriver driver,By title, String titleName) throws InterruptedException
         {
        	
        	 boolean titlePresent=false;
        	
        	 try{
        		 
        		 WebElement titleElement=(WebElement) (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(title));;
        		 String titleText=titleElement.getText();
        		 
        		 if(titleText.equalsIgnoreCase(titleName))
        		 {
        			 titlePresent=true;
        		 }
        		 
        	 }catch(Exception e)
        	 {
        		 System.out.println("Title is not present");
        	 }
        	
                return titlePresent;
          }
         
         public String getTitle(WebDriver driver, By title)
         {
        	 WebElement titleElement=(WebElement) (new WebDriverWait(driver, 50)).until(ExpectedConditions.presenceOfElementLocated(title));;
    		 String titleText=titleElement.getText();
    		 return titleText;
         }
         
         public double TrimData(String Value)
         {
        	 double Data=0.0;
        	 if(Value.trim().isEmpty())
				{
					Data = 0.0;
				}
				else
				{
					Data = Double.parseDouble(Value.replaceAll(",", ""));	
				}	
    		 return Data;
         }
         
         
         public boolean isTitleContains(WebDriver driver,By title, String titleName) throws InterruptedException
         {
        	 boolean titlePresent=false;
        	 if(isElementPresent(driver,title,30))
        	 {
        		 WebElement titleElement=(WebElement) (new WebDriverWait(driver, 100)).until(ExpectedConditions.visibilityOfElementLocated(title));;
        		 String titleText=titleElement.getText();
        		
        		 if(titleText.trim().contains(titleName))
        		 {
        			 titlePresent=true;
        		 }
        	 }
        	 
                return titlePresent;
         }
        
         public void waitForSpinner(WebDriver driver){
        	                   WebDriverWait wait=new WebDriverWait(driver, 240);
               wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("spinner")));
        }
        
         public void waitforElement(WebDriver driver,By locator){
               WebDriverWait wait=new WebDriverWait(driver, 60);
               wait.until(ExpectedConditions.presenceOfElementLocated(locator)); 
         }
        
         public void waitforVisibility(WebDriver driver,By locator){
             WebDriverWait wait=new WebDriverWait(driver, 180);
             wait.until(ExpectedConditions.visibilityOfElementLocated(locator)); 
       }
         
  
}

