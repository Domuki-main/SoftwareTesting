package com.sse3305.service;

import java.util.ArrayList;

public interface EBService {
	
	ArrayList<Double> residential(String vtype, int veconsumption);

	ArrayList<Double> nonresidential(String vtype, String vsector, String vtariff, int veconsumption);

}
