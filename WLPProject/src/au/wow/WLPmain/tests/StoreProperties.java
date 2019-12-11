package au.wow.WLPmain.tests;

import au.wow.wlp.utils.HTMLReport;


public class StoreProperties extends HTMLReport{
	
	public void getStoreDetails(String stNum){ 
			
		System.out.println(System.getProperty("store"));
		switch (stNum){
			case "3064":
				store = "3064";
				user="SAAP";
				pwd="sql";
				eng1="SAAPDB_RCS_3064smssrs001_3064";
				database1="SAAP_RCS_rem_3064";
				host1="3064smssrs001";
				break;
			case "3574":
				store = "3574";
				user="SAAP";
				pwd="sql";
				eng1="SAAPDB_RCS_NCDWMBLAST0027_3574";
				database1="SAAP_RCS_rem_3574";
				host1="NCDWMBLAST0027.wowcorp.com.au";
				break;
			case "9384":
				store = "9384";
				user="SAAP";
				pwd="sql";
				eng1="SAAPDB_RCS_9384SMSSRS001_9384";
				database1="SAAP_RCS_rem_9384";
				host1="9384SMSSRS001";
				break;
			case "1616":
				store = "1616";
				user="SAAP";
				pwd="sql";
				eng1="SAAPDB_RCS_4033smssfs001_1616";
				database1="SAAP_RCS_rem_1616";
				host1="4033smssfs001";
				break;
		}
	}
}
