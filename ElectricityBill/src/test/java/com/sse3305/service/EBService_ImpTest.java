package com.sse3305.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EBService_ImpTest {
	
	//copy from the main program used to keep format of double values
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	//assume the program already run in residential/non- residential method (pre-condition: the controller can assign correct method when accept values from JSP UI). The result of this Junit global test will be compared with the UI operation test.
	@Test
	@DisplayName("Global Test 1")
	void Global_Test1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		String vtype = "Residential";
		String vsector = "";
		String vtariff = "";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.residential(vtype, veconsumption);
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("Global Test 2")
	void Global_Test2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		String vtype = "Non-Residential";
		String vsector = "";
		String vtariff = "Tariff B";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.nonresidential(vtype, vsector, vtariff, veconsumption);
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("Global Test 3")
	void Global_Test3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		String vtype = "Non-Residential";
		String vsector = "Commercial";
		String vtariff = "";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.nonresidential(vtype, vsector, vtariff, veconsumption);
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("Global Test 4")
	void Global_Test4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		String vtype = "Non-Residential";
		String vsector = "Commercial";
		String vtariff = "Tariff B";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.nonresidential(vtype, vsector, vtariff, veconsumption);
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("Global Test 5")
	void Global_Test5() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = -600;
		String vtype = "Non-Residential";
		String vsector = "Commercial";
		String vtariff = "Tariff B";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.nonresidential(vtype, vsector, vtariff, veconsumption);
			if(EbillR.get(0) < 0)real = true;
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("Global Test 6")
	void Global_Test6() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 0;
		String vtype = "Residential";
		String vsector = "";
		String vtariff = "";
		boolean real = false;
		try{
			ArrayList<Double> EbillR = imp.residential(vtype,veconsumption);
		}catch(Exception e) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilNR Test 1")
	void calcNoDiscBilNR_EP_Test1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 120;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 52.2;
		double f2 = 52.2;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 1.62;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilNR Test 2")
	void calcNoDiscBilNR_EP_Test2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 240;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 107.36;
		double f2 = 87;
		double a2 = 20.36;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 3.24;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilNR Test 3")
	void calcNoDiscBilNR_EP_Test3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = -100;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 0;
		double f2 = 0;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 0;
		assertEquals(t, tax);
	}

	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 1 number 1")
	void calcNoDiscBilNR_BVA_Test1_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 0;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 0;
		double f2 = 0;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 0;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 1 number 2")
	void calcNoDiscBilNR_BVA_Test1_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 1;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 0.44;
		double f2 = 0.44;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 0.0135;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 1 number 3")
	void calcNoDiscBilNR_BVA_Test1_3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 199;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 86.57;
		double f2 = 86.57;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 2.69;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 1 number 4")
	void calcNoDiscBilNR_BVA_Test1_4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 200;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 87;
		double f2 = 87;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 2.7;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 2 number 1")
	void calcNoDiscBilNR_BVA_Test2_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 201;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 87.51;
		double f2 = 87;
		double a2 = 0.51;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 2.72;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 2 number 2")
	void calcNoDiscBilNR_BVA_Test2_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 202;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 88.02;
		double f2 = 87;
		double a2 = 1.02;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 2.73;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilNR Test 3 number 1")
	void calcNoDiscBilNR_BVA_Test3_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = -1;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilNR(veconsumption);
		double eb = 0;
		double f2 = 0;
		double a2 = 0;
		assertEquals(f2, round(bilNR_detail.get(0),2));
		assertEquals(a2, round(bilNR_detail.get(1),2));
		assertEquals(eb, round(bilNR_detail.get(2),2));
		double tax = round(imp.calcTaxNR(veconsumption, eb, 0),2);
		double t = 0;
		assertEquals(t, tax);
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 1")
	void calcNoDiscBilR_EP_Test1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 120;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 26.16;
		double f200 = 26.16;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 2")
	void calcNoDiscBilR_EP_Test2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 240;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 56.96;
		double f200 = 43.6;
		double n100 = 13.36;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 3")
	void calcNoDiscBilR_EP_Test3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 450;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 154.4;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 77.4;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 4")
	void calcNoDiscBilR_EP_Test4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 700;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 286.4;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 54.6;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 5")
	void calcNoDiscBilR_EP_Test5() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 1000;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 452.7;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 163.8;
		double n900 = 57.1;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("Equivalence Partitioning of calcNoDiscBilR Test 6")
	void calcNoDiscBilR_EP_Test6() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = -100;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 0;
		double f200 = 0;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 1 number 1")
	void calcNoDiscBilR_BVA_Test1_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption =0;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 0;
		double f200 = 0;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 1 number 2")
	void calcNoDiscBilR_BVA_Test1_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption =1;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 0.22;
		double f200 = 0.22;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 1 number 3")
	void calcNoDiscBilR_BVA_Test1_3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption =199;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 43.38;
		double f200 = 43.38;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 1 number 4")
	void calcNoDiscBilR_BVA_Test1_4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption =200;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 43.6;
		double f200 = 43.6;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 2 number 1")
	void calcNoDiscBilR_EP_Test2_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 201;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 43.93;
		double f200 = 43.6;
		double n100 = 0.33;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 2 number 2")
	void calcNoDiscBilR_EP_Test2_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 202;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 44.27;
		double f200 = 43.6;
		double n100 = 0.67;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 2 number 3")
	void calcNoDiscBilR_EP_Test2_3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 299;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 76.67;
		double f200 = 43.6;
		double n100 = 33.07;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 2 number 4")
	void calcNoDiscBilR_EP_Test2_4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 300;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 77;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 3 number 1")
	void calcNoDiscBilR_EP_Test3_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 301;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 77.52;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 0.52;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 3 number 2")
	void calcNoDiscBilR_EP_Test3_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 302;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 78.03;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 1.03;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 3 number 3")
	void calcNoDiscBilR_EP_Test3_3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 599;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 231.28;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.28;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 3 number 4")
	void calcNoDiscBilR_EP_Test3_4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 231.8;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 4 number 1")
	void calcNoDiscBilR_EP_Test4_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 601;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 232.35;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 0.55;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 4 number 2")
	void calcNoDiscBilR_EP_Test4_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 602;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 232.89;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 1.09;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 4 number 3")
	void calcNoDiscBilR_EP_Test4_3() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 899;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 395.05;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 163.25;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 4 number 4")
	void calcNoDiscBilR_EP_Test4_4() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 900;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 395.6;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 163.8;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 5 number 1")
	void calcNoDiscBilR_EP_Test5_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 901;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 396.17;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 163.8;
		double n900 = 0.57;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 5 number 2")
	void calcNoDiscBilR_EP_Test5_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 902;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 396.74;
		double f200 = 43.6;
		double n100 = 33.4;
		double n300 = 154.8;
		double n300_2 = 163.8;
		double n900 = 1.14;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("BVA of calcNoDiscBilR Test 6 number 1")
	void calcNoDiscBilR_EP_Test6_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = -1;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 0;
		double f200 = 0;
		double n100 = 0;
		double n300 = 0;
		double n300_2 = 0;
		double n900 = 0;
		assertEquals(f200, round(bilNR_detail.get(0),2));
		assertEquals(n100, round(bilNR_detail.get(1),2));
		assertEquals(n300, round(bilNR_detail.get(2),2));
		assertEquals(n300_2, round(bilNR_detail.get(3),2));
		assertEquals(n900, round(bilNR_detail.get(4),2));
		assertEquals(eb, round(bilNR_detail.get(5),2));
	}
	
	@Test
	@DisplayName("calcTaxR Test 1")
	void calcTaxR_Test1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 231.8;
		double t = 0;
		double tax = imp.calcTaxR(veconsumption, eb, 0);
		assertEquals(t, round(tax,2));
	}
	
	@Test
	@DisplayName("calcTaxR Test 2")
	void calcTaxR_Test2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 1000;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 452.7;
		double t = 13.25;
		double tax = imp.calcTaxR(veconsumption, eb, 0);
		assertEquals(t, round(tax,2));
	}
	
	@Test
	@DisplayName("BVA of calcTaxR Test 1")
	void BAV_calcTaxR_Test1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 600;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 231.8;
		double t = 0;
		double tax = imp.calcTaxR(veconsumption, eb, 0);
		assertEquals(t, round(tax,2));
	}
	
	@Test
	@DisplayName("BVA of calcTaxR Test 2 number 1")
	void BAV_calcTaxR_Test2_1() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 601;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 232.35;
		double t = 0.03;
		double tax = imp.calcTaxR(veconsumption, eb, 0);
		assertEquals(t, round(tax,2));
	}
	
	@Test
	@DisplayName("BVA of calcTaxR Test 2 number 2")
	void BAV_calcTaxR_Test2_2() {
		EBService_Imp imp = new EBService_Imp();
		int veconsumption = 602;
		ArrayList<Double> bilNR_detail = imp.calcNoDiscBilR(veconsumption);
		double eb = 232.89;
		double t = 0.07;
		double tax = imp.calcTaxR(veconsumption, eb, 0);
		assertEquals(t, round(tax,2));
	}
	
	
	
	//test round method
	@Test
	@DisplayName("round Test 1")
	void round_Test1() {
		EBService_Imp imp = new EBService_Imp();
		double value = 0.333333;
		int places = -1;
		double e;
		boolean real = false;
		try {
			e = imp.round(value, places);
			//assertEquals(3, e);
		}catch(Exception e1) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("round Test 2")
	void round_Test2() {
		EBService_Imp imp = new EBService_Imp();
		double value = 0.333333;
		int places = 0;
		double e;
		boolean real = false;
		try {
			e = imp.round(value, places);
			assertEquals(0, e);
		}catch(Exception e1) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("round Test 3")
	void round_Test3() {
		EBService_Imp imp = new EBService_Imp();
		double value = 0.333333;
		int places = 1;
		double e;
		boolean real = false;
		try {
			e = imp.round(value, places);
			assertEquals(0.3, e);
		}catch(Exception e1) {
			real = true;
		}
		assertEquals(false, real);
	}
	
	@Test
	@DisplayName("round Test 4")
	void round_Test4() {
		EBService_Imp imp = new EBService_Imp();
		double value = 0.333333;
		int places = 2;
		double e;
		boolean real = false;
		try {
			e = imp.round(value, places);
			assertEquals(0.33, e);
		}catch(Exception e1) {
			real = true;
		}
		assertEquals(false, real);
	}
	
}
