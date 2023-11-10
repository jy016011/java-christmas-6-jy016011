package christmas.constants;

public enum Error {
    ERROR_HEADER("[ERROR]");

    private final String errorMessage;

    private Error(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(){
        return errorMessage;
    }
}