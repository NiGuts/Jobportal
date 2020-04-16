package com.example.restservice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class JobDataController {
	String[] zip = new String[1000];
	boolean loaded = false;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/api/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "HI";
	}
	@GetMapping("/api/zip")
	public ResponseEntity<String[]>  getZip() {

		try {
			File myObj = new File("src/main/java/com/example/restservice/plz.csv");
			Scanner myReader = new Scanner(myObj);
			int currentLine = 0;
			int currentIndex = 0;
			//int bound= new Random().nextInt(10000)+zip.length;
			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				if (currentLine>0 && currentIndex<zip.length /*&& currentLine>=bound-zip.length && currentLine<bound*/ ) {
				zip[currentIndex] = line;
					currentIndex++;
				}
				currentLine++;
			}
			myReader.close();
			loaded = true;
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}return new ResponseEntity<>(zip, HttpStatus.OK);
	}


	@GetMapping("/api/berufsfeld")
	public ResponseEntity<String[]>  getBerufsfeld() {
		String[] s = {
				"Alle Berufsfelder",
				"Softwareentwicklung & IT",
				"UI Design",
				"User Experience",
				"Marketing",
				"Civil Engineering",
				"Vertrieb",
				"Support",
				"Produktentwicklung",
				"Maschinenbau",
				"Medizintechnik",
				"Architektur und Bauwesen",
				"Kunsthandwerk",
				"Sozialwesen"
 	};
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/api/einstiegsart")
	public ResponseEntity<String[]>  getEinstiegsart() {
		String[] s = {
				"Alle Einstiegsarten",
				"Freiwilliges Praktikum",
				"Pflichtpraktikum",
				"Ausbildung",
				"Abschlussarbeit",
				"Berufseinstieg",
				"Young Professional",
				"Professional",
		};
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/api/land")
	public ResponseEntity<String[]>  getLand() {
		String[] s = {
				"Belgien",
				"Dänemark",    "Deutschland",
				"Estland",
				"Finnland",
				"Frankreich",    "Griechenland",    "Island",    "Italien",    "Lettland",    "Liechtenstein",    "Litauen",    "Luxemburg",
			"Malta",    "Niederlande",    "Norwegen",    "Österreich",    "Polen",    "Portugal",    "Schweden",    "Schweiz",    "Slowakei",
				"Slowenien",    "Spanien",    "Tschechien",    "Ungarn",
		};
		return new ResponseEntity<>(s, HttpStatus.OK);
	}

	@GetMapping("/api/region")
	public ResponseEntity<String[]>  getRegion() {
		String[] s = {
				"Baden-Württemberg",
				"Bayern",
				"Berlin",
				"Brandenburg",
				"Bremen",
				"Hamburg",
				"Hessen",
				"Mecklenburg-Vorpommern",
				"Niedersachsen",
				"Nordrhein-Westfalen",
				"Rheinland-Pfalz",
				"Saarland",
				"Sachsen",
				"Sachsen-Anhalt",
				"Schleswig-Holstein",
				"Thüringen"
				};
		return new ResponseEntity<>(s, HttpStatus.OK);
	}
}
