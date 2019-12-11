package au.wow.WLPmain.objects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WeeklyTradingStatementZoneObjects 
{
	@FindBy(xpath= "//span[@id='j_id_jsp_2073548615_0:status.start' and @style='display: none;']")
	public WebElement Spinner;
	
	@FindBy(xpath= "/html/frameset/frame[2]") 
	public WebElement DataFrame;
	
	@FindBy(xpath= "//*[@id='form1:buttonLogoff']")
	public WebElement Logoff;
	
	@FindBy(xpath= "/html/frameset/frame[1]")
	public WebElement TitleFrame;
	
	@FindBy(xpath= "//*[@id='lnktable']/tbody/tr/td/a[1]")
	public WebElement ZoneHyperLink;
	
	@FindBy(xpath= "//*[@id='lnktable']/tbody/tr/td/a[2]")
	public WebElement GroupHyperLink;
		
	public static final String Departments="//td[contains(@id,'form:table1:dynamic:j')][1]";	//Groups column
	public static final String TotalTitle="//td[contains(@id,'form:table1:j')][1]";	//Zone Total Text
	public static final String DataTable="//*[@id='form:table1']/tbody/tr";
	
//Sales										
	public static final String ActualSales="//td[contains(@id,'form:table1:dynamic:j')][2]";
	public static final String PlannedSales="//td[contains(@id,'form:table1:dynamic:j')][3]/span[1]";
	public static final String PlannedSalesGrwth="//td[contains(@id,'form:table1:dynamic:j')][3]/span[2]";
	public static final String LYSales="//td[contains(@id,'form:table1:dynamic:j')][4]/span[1]";
	public static final String LYSalesGrwth="//td[contains(@id,'form:table1:dynamic:j')][4]/span[2]";
	public static final String BudgetSales="//td[contains(@id,'form:table1:dynamic:j')][5]/span[1]";
	public static final String BudgetSalesGrwth="//td[contains(@id,'form:table1:dynamic:j')][5]/span[2]";
	public static final String AvgBasketSales="//td[contains(@id,'form:table1:dynamic:j')][6]/span[1]";
	public static final String Mix="//td[contains(@id,'form:table1:dynamic:j')][6]/span[2]";
	
	public static final String ActualSalesTtl="//td[contains(@id,'form:table1:j')][2]";
	public static final String PlannedSalesTtl="//td[contains(@id,'form:table1:j')][3]/span[1]";
	public static final String PlannedSalesGrwthTtl="//td[contains(@id,'form:table1:j')][3]/span[2]";
	public static final String LYSalesTtl="//td[contains(@id,'form:table1:j')][4]/span[1]";
	public static final String LYSalesGrwthTtl="//td[contains(@id,'form:table1:j')][4]/span[2]";
	public static final String BudgetSalesTtl="//td[contains(@id,'form:table1:j')][5]/span[1]";
	public static final String BudgetSalesGrwthTtl="//td[contains(@id,'form:table1:j')][5]/span[2]";
	public static final String AvgBasketSalesTtl="//td[contains(@id,'form:table1:j')][6]/span[1]";
	public static final String MixTtl="//td[contains(@id,'form:table1:j')][6]/span[2]";

//Items and ItemPrice
	public static final String PlannedItem="//td[contains(@id,'form:table1:dynamic:j')][7]/span[1]";
	public static final String ActualItem="//td[contains(@id,'form:table1:dynamic:j')][7]/span[2]";
	public static final String PlannedItemPrice="//td[contains(@id,'form:table1:dynamic:j')][8]/span[1]";
	public static final String ActualItemPrice="//td[contains(@id,'form:table1:dynamic:j')][8]/span[2]";

	public static final String PlannedItemTtl="//td[contains(@id,'form:table1:j')][7]/span[1]";
	public static final String ActualItemTtl="//td[contains(@id,'form:table1:j')][7]/span[2]";
	public static final String PlannedItemPriceTtl="//td[contains(@id,'form:table1:j')][8]/span[1]";
	public static final String ActualItemPriceTtl="//td[contains(@id,'form:table1:j')][8]/span[2]";
	
//Customers
	public static final String ThisYrCustomer="//td[contains(@id,'form:table1:dynamic:j')][9]";
	public static final String PercentageLastCustomer="//td[contains(@id,'form:table1:dynamic:j')][10]/span[1]";
	public static final String DeptPenetrationCustomer="//td[contains(@id,'form:table1:dynamic:j')][10]/span[3]";
	
	public static final String ThisYrCustomerTtl="//td[contains(@id,'form:table1:j')][9]";
	public static final String PercentageLastCustomerTtl="//td[contains(@id,'form:table1:j')][10]/span[1]";
	public static final String DeptPenetrationCustomerTtl="//td[contains(@id,'form:table1:j')][10]/span[3]";
	
//Hours
	public static final String ActualHours="//td[contains(@id,'form:table1:dynamic:j')][11]/span[1]";
	public static final String VarSMSHours="//td[contains(@id,'form:table1:dynamic:j')][11]/span[2]";
	public static final String PlannedHours="//td[contains(@id,'form:table1:dynamic:j')][12]/span[1]";
	public static final String VarPlannedHours="//td[contains(@id,'form:table1:dynamic:j')][12]/span[2]";
	public static final String ActualSMSHours="//td[contains(@id,'form:table1:dynamic:j')][13]/span[1]";
	public static final String ORPercentageHours="//td[contains(@id,'form:table1:dynamic:j')][13]/span[2]";
	
	public static final String ActualHoursTtl="//td[contains(@id,'form:table1:j')][11]/span[1]";
	public static final String VarSMSHoursTtl="//td[contains(@id,'form:table1:j')][11]/span[2]";
	public static final String PlannedHoursTtl="//td[contains(@id,'form:table1:j')][12]/span[1]";
	public static final String VarPlannedHoursTtl="//td[contains(@id,'form:table1:j')][12]/span[2]";
	public static final String ActualSMSHoursTtl="//td[contains(@id,'form:table1:j')][13]/span[1]";
	public static final String ORPercentageHoursTtl="//td[contains(@id,'form:table1:j')][13]/span[2]";
	
	//Wages
		public static final String PlannedCPHWages="//td[contains(@id,'form:table1:dynamic:j')][17]";
		public static final String ActualWages="//td[contains(@id,'form:table1:dynamic:j')][14]/span[1]";
		public static final String ActualWagesPercentage="//td[contains(@id,'form:table1:dynamic:j')][14]/span[2]";
		public static final String BudgetWages="//td[contains(@id,'form:table1:dynamic:j')][15]/span[1]";
		public static final String BudgetWagesPercentage="//td[contains(@id,'form:table1:dynamic:j')][15]/span[2]";
		public static final String PlannedWages="//td[contains(@id,'form:table1:dynamic:j')][18]/span[1]";
		public static final String PlannedWagesPercentage="//td[contains(@id,'form:table1:dynamic:j')][18]/span[2]";
		public static final String AllowedWages="//td[contains(@id,'form:table1:dynamic:j')][16]";
		public static final String CPHWages="//td[contains(@id,'form:table1:dynamic:j')][19]/span[1]";
		public static final String VarBudgetWages="//td[contains(@id,'form:table1:dynamic:j')][19]/span[2]";
		
		public static final String PlannedCPHWagesTtl="//td[contains(@id,'form:table1:j')][17]";
		public static final String ActualWagesTtl="//td[contains(@id,'form:table1:j')][14]/span[1]";
		public static final String ActualWagesPercentageTtl="//td[contains(@id,'form:table1:j')][14]/span[2]";
		public static final String BudgetWagesTtl="//td[contains(@id,'form:table1:j')][15]/span[1]";
		public static final String BudgetWagesPercentageTtl="//td[contains(@id,'form:table1:j')][15]/span[2]";
		public static final String PlannedWagesTtl="//td[contains(@id,'form:table1:j')][18]/span[1]";
		public static final String PlannedWagesPercentageTtl="//td[contains(@id,'form:table1:j')][18]/span[2]";
		public static final String AllowedWagesTtl="//td[contains(@id,'form:table1:j')][16]";
		public static final String CPHWagesTtl="//td[contains(@id,'form:table1:j')][19]/span[1]";
		public static final String VarBudgetWagesTtl="//td[contains(@id,'form:table1:j')][19]/span[2]";
		
		//Wages Modiefied
			public static final String PlannedCPHWageswts="//td[contains(@id,'form:table1:dynamic:j')][18]";
			public static final String ActualWageswts="//td[contains(@id,'form:table1:dynamic:j')][14]/span[1]";
			public static final String ActualWagesPercentagewts="//td[contains(@id,'form:table1:dynamic:j')][14]/span[2]";
			public static final String BudgetWageswts="//td[contains(@id,'form:table1:dynamic:j')][16]/span[1]";
			public static final String BudgetWagesPercentagewts="//td[contains(@id,'form:table1:dynamic:j')][16]/span[2]";
			public static final String PlannedWageswts="//td[contains(@id,'form:table1:dynamic:j')][15]/span[1]";
			public static final String PlannedWagesPercentagewts="//td[contains(@id,'form:table1:dynamic:j')][15]/span[2]";
			public static final String AllowedWagesswts="//td[contains(@id,'form:table1:dynamic:j')][17]";
			public static final String CPHWageswts="//td[contains(@id,'form:table1:dynamic:j')][19]";
			public static final String VarBudgetWageswts="//td[contains(@id,'form:table1:dynamic:j')][19]/span[2]";
			
			public static final String PlannedCPHWagesTtlwts="//td[contains(@id,'form:table1:j')][18]";
			public static final String ActualWagesTtlwts="//td[contains(@id,'form:table1:j')][14]/span[1]";
			public static final String ActualWagesPercentageTtlwts="//td[contains(@id,'form:table1:j')][14]/span[2]";
			public static final String BudgetWagesTtlwts="//td[contains(@id,'form:table1:j')][16]/span[1]";
			public static final String BudgetWagesPercentageTtlwts="//td[contains(@id,'form:table1:j')][16]/span[2]";
			public static final String PlannedWagesTtlwts="//td[contains(@id,'form:table1:j')][15]/span[1]";
			public static final String PlannedWagesPercentageTtlwts="//td[contains(@id,'form:table1:j')][15]/span[2]";
			public static final String AllowedWagesTtlwts="//td[contains(@id,'form:table1:j')][17]";
			public static final String CPHWagesTtlwts="//td[contains(@id,'form:table1:j')][19]";
			public static final String VarBudgetWagesTtlwts="//td[contains(@id,'form:table1:j')][19]/span[2]";
	
	//New Table
		public static final String Weeks="//td[contains(@id,'form:table2:dynamic:j')][1]";	//department column
		public static final String MonthTotal="//td[contains(@id,'form:table2:j')][1]";	//Store Total Text
		public static final String Table="//*[@id='form:table2']/tbody/tr";
		
		//Monthly Data Table - Sales
		public static final String ActualSalesNew = "//td[contains(@id,'form:table2:dynamic:j')][5]";
		public static final String ActualSalesTtlNew = "//td[contains(@id,'form:table2:j')][5]";
		public static final String WeeklyPlannedSales = "//td[contains(@id,'form:table2:dynamic:j')][4]";
		public static final String WeeklyPlannedSalesTtl = "//td[contains(@id,'form:table2:j')][4]";
		public static final String MonthlyPlannedSales = "//td[contains(@id,'form:table2:dynamic:j')][3]";
		public static final String MonthlyPlannedSalesTtl = "//td[contains(@id,'form:table2:j')][3]";
		public static final String BudgetSalesNew = "//td[contains(@id,'form:table2:dynamic:j')][2]";
		public static final String BudgetSalesTtlNew = "//td[contains(@id,'form:table2:j')][2]";
		public static final String ForecastSalesNew = "//td[contains(@id,'form:table2:dynamic:j')][2]";
		public static final String ForecastSalesTtlNew="//td[contains(@id,'form:table2:j')][2]";
			
		//Monthly Data Table - Wages
		public static final String ActualWagesNew = "//td[contains(@id,'form:table2:dynamic:j')][9]";
		public static final String ActualWagesTtlNew = "//td[contains(@id,'form:table2:j')][9]";
		public static final String WeeklyPlannedWages = "//td[contains(@id,'form:table2:dynamic:j')][8]";
		public static final String WeeklyPlannedWagesTtl = "//td[contains(@id,'form:table2:j')][8]";
		public static final String MonthlyPlannedWages = "//td[contains(@id,'form:table2:dynamic:j')][7]";
		public static final String MonthlyPlannedWagesTtl = "//td[contains(@id,'form:table2:j')][7]";
		public static final String BudgetWagesNew = "//td[contains(@id,'form:table2:dynamic:j')][6]";
		public static final String BudgetWagesTtlNew = "//td[contains(@id,'form:table2:j')][6]";
		public static final String ForecastWagesNew = "//td[contains(@id,'form:table2:dynamic:j')][6]";
		public static final String ForecastWagesTtlNew = "//td[contains(@id,'form:table2:j')][6]";

		//Monthly Data Table - Wages %
		public static final String ActualWagePerNew = "//td[contains(@id,'form:table2:dynamic:j')][13]";
		public static final String ActualWagePerTtlNew = "//td[contains(@id,'form:table2:j')][13]";
		public static final String WeeklyPlannedWagePer = "//td[contains(@id,'form:table2:dynamic:j')][12]";
		public static final String WeeklyPlannedWagePerTtl = "//td[contains(@id,'form:table2:j')][12]";
		public static final String MonthlyPlannedWagePer = "//td[contains(@id,'form:table2:dynamic:j')][11]";
		public static final String MonthlyPlannedWagePerTtl = "//td[contains(@id,'form:table2:j')][11]";
		public static final String BudgetWagePerNew = "//td[contains(@id,'form:table2:dynamic:j')][10]";
		public static final String BudgetWagePerTtlNew = "//td[contains(@id,'form:table2:j')][10]";
		public static final String ForecastWagePerNew = "//td[contains(@id,'form:table2:dynamic:j')][10]";
		public static final String ForecastWagePerTtlNew = "//td[contains(@id,'form:table2:j')][10]";

		//Monthly Data Table - OR
		public static final String ActualORNew = "//td[contains(@id,'form:table2:dynamic:j')][16]";
		public static final String ActualORTtlNew = "//td[contains(@id,'form:table2:j')][16]";
		public static final String WeeklyPlannedOR = "//td[contains(@id,'form:table2:dynamic:j')][15]";
		public static final String WeeklyPlannedORTtl = "//td[contains(@id,'form:table2:j')][15]";
		public static final String MonthlyPlannedOR = "//td[contains(@id,'form:table2:dynamic:j')][14]";
		public static final String MonthlyPlannedORTtl = "//td[contains(@id,'form:table2:j')][14]";

		//CPH
		public static final String ActualCPHNew = "//td[contains(@id,'form:table2:dynamic:j')][19]";
		public static final String ActualCPHTtlNew = "//td[contains(@id,'form:table2:j')][19]";
		public static final String WeeklyPlannedCPH = "//td[contains(@id,'form:table2:dynamic:j')][18]";
		public static final String WeeklyPlannedCPHTtl = "//td[contains(@id,'form:table2:j')][18]";
		public static final String MonthlyPlannedCPH = "//td[contains(@id,'form:table2:dynamic:j')][17]";
		public static final String MonthlyPlannedCPHTtl = "//td[contains(@id,'form:table2:j')][17]";
}