package com.sse3305;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sse3305.service.EBService_Imp;

public class EBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	public EBController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int veconsumption = Integer.parseInt(request.getParameter("econsumption"));
		String vtype = request.getParameter("type");
		String vsector = request.getParameter("sector");
		String vtariff = request.getParameter("tariff");

		EBService_Imp ebs = new EBService_Imp();

		ArrayList<Double> result = new ArrayList<Double>();

		switch (vtype) {
		case "Residential":
			result = ebs.residential(vtype, veconsumption);
			break;
		case "Non-Residential":
			result = ebs.nonresidential(vtype, vsector, vtariff, veconsumption);
			break;
		}

		request.setAttribute("type", vtype);
		request.setAttribute("sector", vsector);
		request.setAttribute("tariff", vtariff);
		request.setAttribute("econsumption", veconsumption);
		request.setAttribute("result", result);
	

		if (vtype.equals("Residential")) {
			RequestDispatcher rd = request.getRequestDispatcher("displayResult_R.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("displayResult.jsp");
			rd.forward(request, response);
		}
	}
}
