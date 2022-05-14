package com;
import com.Unit;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/UnitsAPI")
public class UnitsAPI extends HttpServlet{
	
	private static final long serialVersionUID =1L;
	Unit unitobj = new Unit();
	
	public UnitsAPI()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
			{
			String output = unitobj.insertUnit(request.getParameter("unitcode"),
			request.getParameter("name"),
			request.getParameter("address"),
			request.getParameter("date"),
			request.getParameter("nunits"),
			request.getParameter("period"),
			request.getParameter("pricep"),
			request.getParameter("tprice"));
			
			response.getWriter().write(output);
			}
	protected void doPut(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
	{
		 Map paras = getParasMap(request);
		 
		 String output = unitobj.updateUnit(paras.get("hidUnitIDSave").toString(),
				 paras.get("unitcode").toString(),
				 paras.get("name").toString(),
				 paras.get("address").toString(),
				 paras.get("date").toString(),
				 paras.get("nunits").toString(),
				 paras.get("period").toString(),
				 paras.get("pricep").toString(),
				 paras.get("tprice").toString());
				
		 
		response.getWriter().write(output);
			 
			 
			
	} 
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
			{
			Map paras = getParasMap(request);
			String output = unitobj.deleteUnit(paras.get("uid").toString().trim());
			response.getWriter().write(output);
			}
	private static Map getParasMap(HttpServletRequest request)
	{
		Map<String, String> map = new HashMap<String, String>();
		
		try
		{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			
			String[] params = queryString.split("&");
			for (String param : params)
			{ 
	
				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}
}
