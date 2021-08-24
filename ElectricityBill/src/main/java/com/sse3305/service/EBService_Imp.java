package com.sse3305.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class EBService_Imp implements EBService {
	double amount = 0.00;
	double tax = 0.00;
	double total= 0.00;
	int print_status = 0;

	@Override
	public ArrayList<Double> residential(String vtype, int veconsumption) {

		ArrayList<Double> EbillR = new ArrayList<Double>();
		ArrayList<Double> BilDetail = new ArrayList<Double>();

		BilDetail = calcNoDiscBilR(veconsumption);
		amount = BilDetail.get(5);
		tax = calcTaxR(veconsumption, amount, 0);
		total = amount + tax;
		
		EbillR.add(round(amount, 2));
		EbillR.add(round(tax, 2));
		EbillR.add(round(total,2));
		EbillR.add(BilDetail.get(0));
		EbillR.add(BilDetail.get(1));
		EbillR.add(BilDetail.get(2));
		EbillR.add(BilDetail.get(3));
		EbillR.add(BilDetail.get(4));

		return EbillR;
	}

	// To calculate residential no discount bill
	public ArrayList<Double> calcNoDiscBilR(int consumption) {

		ArrayList<Double> bil_detail = new ArrayList<Double>();
		double first200kwh = 0.00;
		double next100kwh = 0.00;
		double next300kwh_1 = 0.00;
		double next300kwh_2 = 0.00;
		double nextbalancekwh = 0.00;
		double bil_amount;

		if (consumption >= 201) {
			if (consumption >= 301) {
				if (consumption >= 601) {
					if (consumption >= 901) {
						first200kwh = (200 * 21.80) / 100;
						next100kwh = (100 * 33.40) / 100;
						next300kwh_1 = (300 * 51.60) / 100;
						next300kwh_2 = (300 * 54.60) / 100;
						consumption = consumption - 900;
						nextbalancekwh = (consumption * 57.10) / 100;
						bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2 + nextbalancekwh;
					} else {
						first200kwh = (200 * 21.80) / 100;
						next100kwh = (100 * 33.40) / 100;
						next300kwh_1 = (300 * 51.60) / 100;
						consumption = consumption - 600;
						next300kwh_2 = (consumption * 54.60) / 100;
						bil_amount = first200kwh + next100kwh + next300kwh_1 + next300kwh_2;
					}
				} else {
					first200kwh = (200 * 21.80) / 100;
					next100kwh = (100 * 33.40) / 100;
					consumption = consumption - 300;
					next300kwh_1 = (consumption * 51.60) / 100;
					bil_amount = first200kwh + next100kwh + next300kwh_1;
				}
			} else {
				first200kwh = (200 * 21.80) / 100;
				consumption = consumption - 200;
				next100kwh = (consumption * 33.40) / 100;
				bil_amount = first200kwh + next100kwh;
			}
		} else {
			bil_amount = (consumption * 21.80) / 100;
			consumption = consumption - 200;
		}

		bil_detail.add(first200kwh);
		bil_detail.add(next100kwh);
		bil_detail.add(next300kwh_1);
		bil_detail.add(next300kwh_2);
		bil_detail.add(nextbalancekwh);
		bil_detail.add(bil_amount);

		return bil_detail;
	}

	@Override
	public ArrayList<Double> nonresidential(String vtype, String vsector, String vtariff, int veconsumption) {

		ArrayList<Double> EbillNR = new ArrayList<Double>();
		ArrayList<Double> BilDetailNR = new ArrayList<Double>();

		if (vsector.equals("Commercial")) {
			if (vtariff.equals("Tariff B")) {
				BilDetailNR = calcNoDiscBilNR(veconsumption);
				amount = BilDetailNR.get(2);
				tax = calcTaxNR(veconsumption, amount, 0);
				total = amount + tax;
			} else if (vtariff.equals("Tariff C1")) {
				// not yet implement

			} else if (vtariff.equals("Tariff C2")) {
				// not yet implement

			}
		} else if (vsector.equals("Industry")) {
			// not yet implement
		}

		EbillNR.add(round(amount,2));
		EbillNR.add(round(tax,2));
		EbillNR.add(round(total,2));
		EbillNR.add(BilDetailNR.get(0));
		EbillNR.add(BilDetailNR.get(1));
		return EbillNR;
	}

	// To calculate non-residential no discount bill
	public ArrayList<Double> calcNoDiscBilNR(int consumption) {

		ArrayList<Double> bilNR_detail = new ArrayList<Double>();
		double bil_amount = 0.00;
		double first200kwh = 0.00;
		double nextbalancekwh = 0.00;

		if (consumption > 201) {
			first200kwh = 200 * 0.435;
			consumption = consumption - 200;
			nextbalancekwh = consumption * 0.509;
			bil_amount = first200kwh + nextbalancekwh;

		} else {
			bil_amount = (consumption * 0.435);
		}

		bilNR_detail.add(first200kwh);
		bilNR_detail.add(nextbalancekwh);
		bilNR_detail.add(bil_amount);
		return bilNR_detail;
	}

	// To calculate 6% on bill amount. The tax is charged for consumption more than
	// 600kWh
	public static double calcTaxR(int consumption, double bil_amount, int disc) {
		if (consumption < 600) {
			return 0;
		} else {
			return ((bil_amount - 231.80) * 0.06);
		}
	}

	// The ICPT is charged based on total consumption.
	public static double calcTaxNR(int consumption, double bil_amount, int disc) {
		return (consumption * 0.0135);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

}
