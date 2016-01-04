package com.ftr.app;

import java.io.IOException;

import org.testng.annotations.Test;
//import org.apache.log4j.Logger;


public class ASR_TA_Checkout extends Setup_Teardown {
	
	//static final Logger log = Logger.getLogger(ASR_TA_Checkout.class);
		
	@Test(priority = 1)
	public void VFO_TA_Creation() throws InterruptedException, IOException {
		
		p.load(fi);
		//BasicConfigurator.configure();
		//log.info("# # # # # # # # # # # # # # # # # # # # # # # # # # # ");
		
		VFO_LoginPage vl = new VFO_LoginPage(driver);
	
		vl.setUName(p.getProperty("UID"));
		vl.setPWD(p.getProperty("PWD"));
		vl.setModule(p.getProperty("MOD"));
		vl.signIN();

		vl = null;

		VFO_WorkList vw = new VFO_WorkList(driver);

		vw.clickCreateTroubleReport();
		vw.troubleReportInitiation("TestAuto_ASRTA_Check");
		vw.clickNext();
		
		vw = null;
		
		VFO_CreateTroubleReportRequest vc = new VFO_CreateTroubleReportRequest(driver);
		vc.submit();
		vc.getTroubleReportID();
		
		
		
				
	}
	
	

}
