package example.cucumber;

public class ErrorMessageHolder {
    private String errorMessage = "";

	public String getErrorMessage() {
		String msg = errorMessage;
		errorMessage = "";
		return msg;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
    
}
