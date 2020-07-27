package com.adiops.apigateway.common.response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <ul>
 * <li>Allows to return custom headers.</li>
 * <li>Suppresses stack traces when logging level does not include DEBUG.</li>
 * </ul>
 * 
 */
@SuppressWarnings("serial")
public class RestException  extends Throwable
{
    public static final String HEADER_ERROR_TYPE = "error-type";

    public final static int ERROR_STATUS_GENERIC = 500;

    public final static int ERROR_STATUS_BAD_REQUEST = 400;
    public final static int ERROR_STATUS_UNAUTHORIZED = 401;
    public final static int ERROR_STATUS_FORBIDDEN = 403;
    public final static int ERROR_STATUS_NOT_FOUND = 404;
    public final static int ERROR_STATUS_METHOD_NOT_ALLOWED = 405;
    public final static int ERROR_STATUS_CONFLICT = 409;
    public final static int ERROR_STATUS_PRECONDITION_FAILED = 412;

    /**
     * The default status code to use.
     */
    private int statusCode = ERROR_STATUS_GENERIC;
    /**
     * The headers to be sent with the response.
     */
    private Map<String, String> headers = null;
    /**
     * The message that should be sent as the response entity.
     */
    private String responseBody = null;

    /**
     * Create a new instance with a specified statusCode
     * 
     * @param statusCode
     */
    public RestException()
    {
        this(ERROR_STATUS_GENERIC, null, null);
    }

    /**
     * Create a new instance with a specified statusCode
     * 
     * @param statusCode
     */
    public RestException(int statusCode)
    {
        this(statusCode, null, null);
    }

    /**
     * Create a new instance with the default statusCode and a custom message
     * 
     * @param message
     */
    public RestException(String message)
    {
        this(500, message, null);
    }

    /**
     * Create a new instance with a specified statusCode and a custom message.
     * 
     * @param statusCode
     * @param message
     */
    public RestException(int statusCode, String message)
    {
        this(statusCode, message, null);
    }

    /**
     * Create a new instance with a specified statusCode, a custom message, and a set of response headers.
     * 
     * @param statusCode
     * @param message
     * @param headers
     */
    public RestException(int statusCode, String message, Map<String, String> headers)
    {
        this.statusCode = statusCode;
        this.responseBody = message;
        this.headers = headers;
    }



    /**
     * Set a custom header at the response for this exception.
     * 
     * @param header
     *            The name of the header.
     * @param headerValues
     *            One or more values. Multiple values will be joined using ','.
     */
    public RestException headers(String header,String value)
    {
        if (this.headers == null)
        {
            this.headers = new HashMap<>();
        }
        this.headers.put(header, value);
        return this;
    }

    /**
     * Set the message for this exception.
     * 
     * @param message
     * @return
     */
    public RestException message(String message)
    {
        this.responseBody = message;
        return this;
    }

    /**
     * RFC2616: "The server has not found anything matching the Request-URI. "
     */
    public RestException notFound()
    {
        this.statusCode = RestException.ERROR_STATUS_NOT_FOUND;
        return this;
    }

    /**
     * RFC2616:
     * "The request could not be completed due to a conflict with the current state of the resource. This code is only allowed in situations where it is expected that the user may be able to resolve the conflict and resubmit the request. The response body should include enough information for the user to recognize the source of the conflict."
     */
    public RestException conflict()
    {
        this.statusCode = RestException.ERROR_STATUS_CONFLICT;
        return this;
    }

    /**
     * RFC2616:
     * "The request could not be understood by the server due to malformed syntax. The client SHOULD NOT repeat the request without modifications."
     */
    public RestException badRequest()
    {
        this.statusCode = RestException.ERROR_STATUS_BAD_REQUEST;
        return this;
    }
    
    /**
     * RFC2616:
     * "The precondition given in one or more of the request-header fields
     * evaluated to false when it was tested on the server. This response
     * code allows the client to place preconditions on the current resource
     * metainformation (header field data) and thus prevent the requested
     * method from being applied to a resource other than the one intended."
     */
    public RestException preconditionFailed()
    {
        this.statusCode = RestException.ERROR_STATUS_PRECONDITION_FAILED;
        return this;
    }

    /**
     * This exception indicates that the request could not be fulfilled due to invalid attributes contained in the
     * request body. It will return a status code 400 (Client error/Bad request).
     * 
     * @param invalidAttributeNames
     *            The names of the attributes that contained invalid values.
     * @return The {@link RestException}
     */
    public RestException invalidAttributes(String... invalidAttributeNames) 
    {
        if (invalidAttributeNames != null)
        {
            List<String> invalidAttributesList = Arrays.asList(invalidAttributeNames);
            return invalidAttributes(invalidAttributesList);
        }
        return this;
    }
    
    /**
     * This exception indicates that the request could not be fulfilled due to invalid attributes contained in the
     * request body. It will return a status code 400 (Client error/Bad request).
     * 
     * @param invalidAttributeNames
     *            The names of the attributes that contained invalid values.
     * @return The {@link RestException}
     */
    public RestException invalidAttributes(List<String> invalidAttributeNames)
    {
        this.statusCode = RestException.ERROR_STATUS_BAD_REQUEST;
        if (invalidAttributeNames != null && !invalidAttributeNames.isEmpty())
        {
            String joinedVals =join(invalidAttributeNames, ",");
            this.headers(HEADER_ERROR_TYPE, "error-invalid-attributes");
            this.headers("error-invalid-attributes", joinedVals);
            this.message("The following attributes are invalid: " + joinedVals);
        }
        return this;
    }

  

	private String join(List<String> invalidAttributeNames, String ch) {
		StringBuilder sb= new StringBuilder();
		invalidAttributeNames.forEach(name->{
			sb.append(name+ch);
		});
		
    	return sb.substring(0, sb.length()-2);
	}

	/**
     * This exception indicates that the request could not be fulfilled due to required attributes missing from the
     * request body. It will return a status code 400 (Client error/Bad request).
     * 
     * @param missingAttributeNames
     *            The names of the attributes that were missing values.
     * @return The {@link RestException}
     */
    public RestException missingAttributes(String... missingAttributeNames)
    {
        if (missingAttributeNames != null)
        {
            List<String> missingAttributesList = Arrays.asList(missingAttributeNames);
            return missingAttributes(missingAttributesList);
        }
        return this;

    }

    /**
     * This exception indicates that the request could not be fulfilled due to required attributes missing from the
     * request body. It will return a status code 400 (Client error/Bad request).
     * 
     * @param missingAttributeNames
     *            The names of the attributes that were missing values.
     * @return The {@link RestException}
     */
    public RestException missingAttributes(List<String> missingAttributeNames)
    {
        this.statusCode = RestException.ERROR_STATUS_BAD_REQUEST;
        if (missingAttributeNames != null && !missingAttributeNames.isEmpty())
        {
            String joinedVals = join(missingAttributeNames, ",");
            this.headers(HEADER_ERROR_TYPE, "error-missing-attributes");
            this.headers("error-missing-attributes", joinedVals);
            this.message("The following attributes are missing: " + joinedVals);
        }
        return this;
    }




    /**
     * @return The numeric HTTP status for this exception.
     */
    public int getStatusCode()
    {
        return statusCode;
    }

    /**
     * Returns the header with the given key.
     * @param key Key
     * @return Header
     */
    public String getHeader(String key)
    {
        if (headers == null)
        {
            return null;
        }
        return headers.get(key);
    }

    public String getMessage()
    {
        return responseBody;
    }


}
