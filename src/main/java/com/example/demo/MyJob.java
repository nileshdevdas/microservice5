package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJob {

	@Scheduled(fixedDelay = 5000)
	public void processFiles() {
		File file = new File("d:/inputData/");
		File[] files = file.listFiles();
		if (files.length != 0) {
			List<String> smallAirports = new ArrayList<String>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(files[0]));
				String line = "";
				while ((line = reader.readLine()) != null) {
					String[] cols = line.split(",");
					if (cols[2].equals("\"large_airport\"") && cols[8].equals("\"IN\"")) {
						smallAirports.add(line);
					}
				}
				System.out.println("Total Large Airports = " + smallAirports.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
