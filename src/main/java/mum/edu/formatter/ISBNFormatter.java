package mum.edu.formatter;

import java.util.Locale;

import org.springframework.expression.ParseException;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import mum.edu.domain.ISBNumber;

public class ISBNFormatter implements Formatter<ISBNumber> {
	public static final String SEPARATOR = "-";

	public String print(ISBNumber isbn, Locale locale) {
		return isbn.getStart() + SEPARATOR + isbn.getMiddle() + SEPARATOR + isbn.getEnd();
	}

	public ISBNumber parse(String source, Locale locale) throws ParseException {
		ISBNumber isbNumber = null;
		try {
			String[] isbn = source.split(SEPARATOR);
			isbNumber = new ISBNumber(Integer.parseInt(isbn[0]), Integer.parseInt(isbn[1]), Integer.parseInt(isbn[2]));
		} catch (Exception e) {
			System.out.println("Error - Bad ISBN Format");
		}
		return isbNumber;
	}
}