package helper;

public class EmptyFieldException extends Exception {

    private String fieldName;

    public EmptyFieldException(String fieldName, String message) {
        super(message);
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }
}
