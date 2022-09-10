package com.example.CsvReadOnLoad.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.example.CsvReadOnLoad.util.CsvLoader;

@Service
public class CountryDetailsLoader {
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	public CountryDetailsLoader() {
		
	}
	
	@PostConstruct
	public void init() {
		 Resource resource = resourceLoader.getResource("classpath:country.csv");
		  try {
			InputStream inputStream = resource.getInputStream();
			 Map<String, String> mapContry = CsvLoader.loadCsv(inputStream);
			 System.out.println(mapContry);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
