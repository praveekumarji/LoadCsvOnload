package com.example.CsvReadOnLoad.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.example.CsvReadOnLoad.pojo.CountryDetails;

public class CsvLoader {
	
	public static String TYPE = "text/csv";
	  static String[] HEADERs = { "Name", "Code"};
	  public static boolean hasCSVFormat(MultipartFile file) {
	    if (!TYPE.equals(file.getContentType())) {
	      return false;
	    }
	    return true;
	  }
	  public static Map<String, String> loadCsv(InputStream is) {
	    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
	     
	      Map<String,String> mapCountries = new HashMap<String, String>();
	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	      for (CSVRecord csvRecord : csvRecords) {
	    	  CountryDetails country = new CountryDetails(
	              csvRecord.get("Name"),
	              csvRecord.get("Code")
	            
	            );
	        
	        mapCountries.put(country.getName(), country.getCode());
	      }
	      return mapCountries;
	      } catch (IOException e) {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
	public static void loadCsv(Resource rescource) {
		// TODO Auto-generated method stub
		
		//InputStream is = rescource.getInputStream(rescource.getFile());
		
	}

}
