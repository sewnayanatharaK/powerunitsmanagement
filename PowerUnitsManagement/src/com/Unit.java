package com;
import java.sql.*;

	
	

	public class Unit {

		public Connection connect()
		{
		 Connection con = null;

		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/units", 
					 "root", "");
			 
			 
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		 return con;
		}
		
//		public String readItems()
//		{ 
//				String output = "";
//				
//				try
//				{ 
//						Connection con = connect();
//						
//						if (con == null) 
//						{ 
//							return "Error while connecting to the database for reading."; 
//						} 
//					 
//			 // Prepare the html table to be displayed
//					 output = "<table border='1'><tr><th>Item Code</th>" 
//								 +"<th>Item Name</th><th>Item Price</th>"
//								 + "<th>Item Description</th>" 
//								 + "<th>Update</th><th>Remove</th></tr>"; 
//					 
//					 String query = "select * from items"; 
//					 Statement stmt = con.createStatement(); 
//					 ResultSet rs = stmt.executeQuery(query); 
//					 
//			 // iterate through the rows in the result set
//					 while (rs.next()) 
//					 { 
//						 String itemID = Integer.toString(rs.getInt("itemID")); 
//						 String itemCode = rs.getString("itemCode"); 
//						 String itemName = rs.getString("itemName"); 
//						 String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
//						 String itemDesc = rs.getString("itemDesc"); 
//						 
//			 // Add a row into the html table
//						 output += "<tr><td><input id ='hidItemIDUpdate' name ='hidItemIDUpdate' type='hidden' value='" + itemID + " '>"	+ itemCode + "</td>";
//						
//						 output += "<td>" + itemName + "</td>"; 
//						 output += "<td>" + itemPrice + "</td>"; 
//						 output += "<td>" + itemDesc + "</td>";
//			 // buttons
//						 output += "<td><input name='btnUpdate' id ='" + itemID + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
//						 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-rechearcherid='"+ itemID + " '>" + "</td></tr>";  
//					 } 
//					 con.close(); 
//					 
//			 // Complete the html table
//					 output += "</table>"; 
//					 
//					 
//					 
//			 } 
//				catch (Exception e) 
//				{ 
//					output = "Error while reading the items."; 
//					System.err.println(e.getMessage()); 
//				} 
//				return output; 
//		}
		
		public String readUnits()
			{
				String output = "";
				try
					{
						Connection con = connect();
						if (con == null)
						{
							return "Error while connecting to the database for reading.";
						}
						
		// Prepare the html table to be displayed
						output = "<table border='1'><tr><th>Unit Code</th>"
								+ "<th>Name</th><th>Address</th>"
								+ "<th>Date</th>"
								+ "<th>No .of Units</th>"
								+ "<th>Period</th>"
								+ "<th>Price per Unit</th>"
								+ "<th>Total Price</th>"
								+ "<th>Update</th><th>Remove</th></tr>";
						
						String query = "select * from unitdetails";
						Statement stmt = con.createStatement();
						ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
						while (rs.next())
						{
							String uid = Integer.toString(rs.getInt("uid"));
							String unitcode = rs.getString("unitcode");
							String name = rs.getString("name");
							String address = rs.getString("address");
							String date = rs.getString("date");
							String nunits = Integer.toString(rs.getInt("nunits"));
							String period = rs.getString("period");
							String pricep = Double.toString(rs.getDouble("pricep"));
							String tprice = Double.toString(rs.getDouble("tprice"));
		
		// Add into the html table
							 output += "<tr><td><input id ='hidUnitIDUpdate' name ='hidUnitIDUpdate' type='hidden' value='" + uid + " '>"	+ unitcode + "</td>";
							output += "<td>" + name + "</td>";
							output += "<td>" + address + "</td>";
							output += "<td>" + date + "</td>";
							output += "<td>" + nunits + "</td>";
							output += "<td>" + period + "</td>";
							output += "<td>" + pricep + "</td>";
							output += "<td>" + tprice + "</td>";
		
		// buttons
//							output += "<td><input name='btnUpdate' type='button' value='Update' "
//									+ "class='btnUpdate btn btn-secondary' data-itemid='" + uid + "'></td>"
//									+ "<td><input name='btnRemove' type='button' value='Remove' "
//									+ "class='btnRemove btn btn-danger' data-itemid='" + uid + "'></td></tr>";
							
							 output += "<td><input name='btnUpdate' id ='" + uid + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
								 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-uid='"+ uid + " '>" + "</td></tr>"; 
						}
						con.close();
						
		// Complete the html table
						output += "</table>";
					}
				catch (Exception e)
				{
					output = "Error while reading the units.";
					System.err.println(e.getMessage());
				}
		return output;
		}

		public String insertUnit(String unitcode, String name,String address, String date,String nunits, String period, String pricep, String tprice )
						{
							String output = "";
							try
							{
								Connection con = connect();
								if (con == null)
								{
									return "Error while connecting to the database for inserting.";
								}
				// create a prepared statement
				String query = " insert into unitdetails(`uid`,`unitcode`,`name`,`address`,`date`,`nunits`, `period`, `pricep` , `tprice`)"
				
									+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, unitcode);
				preparedStmt.setString(3, name);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, date);
				preparedStmt.setInt(6, Integer.parseInt(nunits));
				preparedStmt.setString(7, period);
				preparedStmt.setDouble(8, Double.parseDouble(pricep));
				preparedStmt.setDouble(9, Double.parseDouble(tprice));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newItems = readUnits();
				output = "{\"status\":\"success\", \"data\": \"" +
				newItems + "\"}";
				}
				catch (Exception e)
							{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the unit.\"}";
								System.err.println(e.getMessage());
							}
							return output;
						}
		
		public String updateUnit(String uid, String unitcode, String name,String address, String date,String nunits, String period,String pricep, String tprice)
					{
						String output = "";
						try
						{
							Connection con = connect();
							if (con == null)
							{
								return "Error while connecting to the database for updating.";
							}
				// create a prepared statement
				String query = "UPDATE unitdetails SET unitcode=?,name=?,address=?,date=?,nunits=?,period=?,pricep=?,tprice=? WHERE uid=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
						// binding values
						preparedStmt.setString(1, unitcode);
						preparedStmt.setString(2, name);
						preparedStmt.setString(3, address);
						preparedStmt.setString(4, date);
						preparedStmt.setInt(5, Integer.parseInt(nunits));
						preparedStmt.setString(6, period);
						preparedStmt.setDouble(7, Double.parseDouble(pricep));
						preparedStmt.setDouble(8, Double.parseDouble(tprice));
						preparedStmt.setInt(9, Integer.parseInt(uid));	
						
						
						// execute the statement
						preparedStmt.execute();
						con.close();
						String newItems = readUnits();
						output = "{\"status\":\"success\", \"data\": \"" +
						newItems + "\"}";
						}
						catch (Exception e)
							{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
							System.err.println(e.getMessage());
							}
							return output;
					}

public String deleteUnit(String uid)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for deleting.";
				}
	// create a prepared statement
	String query = "delete from unitdetails where uid=?";
	PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	preparedStmt.setInt(1, Integer.parseInt(uid));
	
	// execute the statement
	preparedStmt.execute();
	con.close();
	String newItems = readUnits();
	output = "{\"status\":\"success\", \"data\": \"" +
	newItems + "\"}";
	}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
	}


		
	}
	


