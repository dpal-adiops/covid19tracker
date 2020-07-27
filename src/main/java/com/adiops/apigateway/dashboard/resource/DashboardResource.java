package com.adiops.apigateway.dashboard.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.adiops.apigateway.common.helper.CSVHelper;
import com.adiops.apigateway.common.response.ResponseMessage;
import com.adiops.apigateway.common.response.ResponseStatusConstants;
import com.adiops.apigateway.common.response.RestException;
import com.adiops.apigateway.dashboard.resourceobject.Covid19DataRO;
import com.adiops.apigateway.dashboard.service.Covid19DataService;
import com.adiops.apigateway.profile.resourceobject.UserRO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The resource class for Dashboard 
 * @author Deepak Pal
 *
 */
@RestController
public class DashboardResource {

	@Autowired
	Covid19DataService mCovid19DataService;

	
	
	/**
	 * This method fetch a list of Covid19Info
	 * @return
	 */
	@ApiOperation(value = "Get a list of Covid19Info")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.OK, message = "dashboard data fetched.", response = Covid19DataRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	
	@RequestMapping(value = "/dashboard/data", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public List<Covid19DataRO> getCovid19DataROs() {
		return mCovid19DataService.getCovid19DataROs();
	}
	
	/**
	 * This method upload a file
	 * @param file
	 * @return
	 */
	@ApiOperation(value = "upload a file")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.CREATED, message = "File has been upload sucessfully.", response = UserRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = 412, message = "Precondition Failed", response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	@PostMapping("/dashboard/import")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				mCovid19DataService.importCSV(file);
				String message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (RestException e) {
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(e.getMessage()));
			}
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage("Please upload a csv file!"));
	}

	/**
	 * This method fetch a list of regions
	 * @return
	 */
	@ApiOperation(value = "Get a list of regions")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.OK, message = "All regions fetched.", response = String.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })
	
	@RequestMapping(value = "/dashboard/regions", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public List<String> getCovid19DataStateList() {
		return mCovid19DataService.getCovid19DataStates();
	}


	/**
	 * This method fetch the covid19 data for a region.
	 * @param region
	 * @return
	 */
	@ApiOperation(value = "Get a covid19Data info for a particular region")
	@ApiResponses({
			@ApiResponse(code = ResponseStatusConstants.OK, message = "region fetched.", response = Covid19DataRO.class),
			@ApiResponse(code = ResponseStatusConstants.INTERNAL_SERVER_ERROR, message = ResponseStatusConstants.INTERNAL_SERVER_ERROR_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.BAD_REQUEST, message = ResponseStatusConstants.BAD_REQUEST_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.NOT_FOUND, message = ResponseStatusConstants.NOT_FOUND_MESSAGE, response = RestException.class),
			@ApiResponse(code = ResponseStatusConstants.FORBIDDEN, message = ResponseStatusConstants.FORBIDDEN_MESSAGE, response = RestException.class) })

	@RequestMapping(value = "/dashboard/region/{region}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseStatus(HttpStatus.OK)
	public List<Covid19DataRO> getCovid19Data(@PathVariable("region") String region) {
		if (region.equalsIgnoreCase("india"))
			return mCovid19DataService.getCovid19CountryData();
		else
			return mCovid19DataService.getCovid19DataByState(region);
	}

}
