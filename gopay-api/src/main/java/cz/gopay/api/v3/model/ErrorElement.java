package cz.gopay.api.v3.model;



/**
 *
 * @author Zbynek Novak | novak.zbynek@gmail.com
 *
 */
public class ErrorElement {

    private ErrorScope scope;

    private String field;

    private int errorCode;

    private String errorName;

    private String message;

    private String description;

    public ErrorElement() {
    }

    public ErrorElement(String errorName, int errorCode, String message,
            String description) {
        super();
        this.scope = ErrorScope.G;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
        this.description = description;
    }

    public ErrorElement(String errorName, int errorCode, String field,
            String message, String description) {
        super();
        this.scope = ErrorScope.F;
        this.field = field;
        this.errorCode = errorCode;
        this.errorName = errorName;
        this.message = message;
        this.description = description;
    }

    public ErrorScope getScope() {
        return scope;
    }

    public String getField() {
        return field;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorName() {
        return errorName;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

}
