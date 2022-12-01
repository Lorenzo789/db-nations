package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	private static final String URL = "jdbc:mysql://localhost:3306/db-nations";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	
	public static void main(String[] args) {
		
//		milestone1();
//		milestone2();
		bonus();
	}
	
	public static void milestone1() {
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
	
			final String sql = "SELECT countries.country_id, countries.name, regions.name, continents.name "
								+ " FROM countries"
								+ " JOIN regions"
								+ " ON countries.region_id = regions.region_id"
								+ " JOIN continents"
								+ " ON regions.continent_id = continents.continent_id"
								+ " ORDER BY countries.name";
	
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
						
						final int countryId = rs.getInt(1);
						final String countryName = rs.getString(2);
						final String regionsName = rs.getString(3);
						final String continentName = rs.getString(4);
						
						System.out.println(countryId + " - "
								+ countryName + " - "
								+ regionsName + " - "
								+ continentName);
					}
				}
			} 
	
			} catch (Exception e) {
	
			System.err.println("ERROR: " + e.getMessage());
		
			}
		}
	
	public static void milestone2() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Insert a nation name");
		String inputCountry = sc.nextLine();
		
		sc.close();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			final String sql = "SELECT * FROM countries WHERE countries.name LIKE ?";
	
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, "%" + inputCountry + "%");
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
				
						final int countryId = rs.getInt(1);
						final String countryName = rs.getString(2);
						
						System.out.println(countryId + " - "
								+ countryName);
					}
				}
			} 
	
			} catch (Exception e) {
	
			System.err.println("ERROR: " + e.getMessage());
		
			}
		}
	
	public static void bonus() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Insert a nation name");
		String inputCountry = sc.nextLine();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			final String sql = "SELECT * FROM countries WHERE countries.name LIKE ?";
	
			try (PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, "%" + inputCountry + "%");
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
				
						final int countryId = rs.getInt(1);
						final String countryName = rs.getString(2);
						
						System.out.println(countryId + " - "
								+ countryName);
					}
				}
			} 
	
			} catch (Exception e) {
	
			System.err.println("ERROR: " + e.getMessage());
		
			}
		
		System.out.println("Insert an id");
		int inputId = sc.nextInt();
		
		sc.close();
		
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
			
			final String sqlLang = " SELECT countries.country_id, countries.name, country_stats.population, country_stats.gdp, languages.`language` "
					+ " FROM countries "
					+ " JOIN country_languages "
					+ " ON country_languages.country_id = countries.country_id "
					+ " JOIN languages "
					+ " ON country_languages.language_id = languages.language_id "
					+ " JOIN country_stats "
					+ " ON country_stats.country_id = countries.country_id "
					+ " WHERE countries.country_id = ? "
					+ " GROUP BY countries.country_id, countries.name, country_stats.population, country_stats.gdp, languages.`language` ";
	
			try (PreparedStatement ps = con.prepareStatement(sqlLang)) {
				
				ps.setInt(1, inputId);
				
				try (ResultSet rs = ps.executeQuery()) {
					
					while(rs.next()) {
				
						final int countryId = rs.getInt(1);
						final String countryName = rs.getString(2);
						final long countryPopulation = rs.getLong(3);
						final long countryStats = rs.getLong(4);
						final String countryLanguage = rs.getString(5);
						
						System.out.println(countryId + " - "
								+ countryName + " - "
								+ countryPopulation + " - "
								+ countryStats + " - "
								+ countryLanguage);
					}
				}
			} 
	
			} catch (Exception e) {
	
			System.err.println("ERROR: " + e.getMessage());
		
			}
		
		}	
}

/*
try (Connection con = DriverManager.getConnection(URL, USER, PWS)) {

final String sql = "";

try (PreparedStatement ps = con.prepareStatement(sql)) {
	try (ResultSet rs = ps.executeQuery()) {
		
		while(rs.next()) {
			
			System.out.println();
		}
	}
} 

} catch (Exception e) {

System.err.println("ERROR: " + e.getMessage());
}
*/