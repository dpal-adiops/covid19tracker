package com.adiops.apigateway.common.helper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBeanBuilder;

/**
 * The pupose of this class to provide helping operation related to csv 
 * @author Deepak Pal
 *
 */
public class CSVHelper {
	public static String TYPE = "text/csv";

	public static boolean hasCSVFormat(MultipartFile file) {

		return true;
	}

	public static <T> List<T> csvToPOJOs(InputStream is, Class<T> clazz) {

		List<T> result = new CsvToBeanBuilder<T>(new InputStreamReader(is)).withType(clazz).build().parse();
		return result;

	}
}
