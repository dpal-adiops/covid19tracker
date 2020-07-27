package com.adiops.apigateway.common.response;


/**
 * Contains constants related to {@link Response}.
 */
public abstract class ResponseStatusConstants
{
    public static final int OK = 200;
    public static final String OK_MESSAGE = "OK";

    public static final int CREATED = 201;
    public static final String CREATED_MESSAGE = "Created";

    public static final int NO_CONTENT = 204;
    public static final String NO_CONTENT_MESSAGE = "No content";

    public static final int BAD_REQUEST = 400;
    public static final String BAD_REQUEST_MESSAGE = "Bad request";

    public static final int UNAUTHORIZED = 401;
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized";

    public static final int FORBIDDEN = 403;
    public static final String FORBIDDEN_MESSAGE = "FORBIDDEN";

    public static final int NOT_FOUND = 404;
    public static final String NOT_FOUND_MESSAGE = "Not found";
    
    public static final int CONFLICT = 409;
    public static final String CONFLICT_MESSAGE = "Conflict";

    public static final int INTERNAL_SERVER_ERROR = 500;
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Internal Server Error";
    
    public static final int NOT_IMPLEMENTED = 501;
    public static final String NOT_IMPLEMENTED_MESSAGE = "Not implemented";
    
    public static final int SERCICE_UNAVAILABLE = 503;
    public static final String SERVICE_UNAVAILABLE_MESSAGE = "Service unavailable";

    public static final int SEE_OTHER = 303;
    public static final String SEE_OTHER_MESSAGE = "See other";
    
    public static final int ACCEPTED = 202;
    public static final String ACCEPTED_MESSAGE = "Accepted";    
}
