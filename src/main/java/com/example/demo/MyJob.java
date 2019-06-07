package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyJob {

	@Autowired
	private DataSource dataSource;

	@Scheduled(fixedDelay = 5000)
	public void processFiles() {
		try {
			Connection con = dataSource.getConnection();
			System.out.println(con);
			con.close();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		File file = new File("d:/inputData/");
		File[] files = file.listFiles();
		if (files.length != 0) {
			List<String> smallAirports = new ArrayList<String>();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(files[0]));
				String line = "";
				while ((line = reader.readLine()) != null) {
					String[] cols = line.split(",");
					if (cols[2].equals("\"small_airport\"") && cols[8].equals("\"IN\"")) {
						smallAirports.add(line);
					}
				}
				System.out.println("Total Airports = " + smallAirports.size());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
