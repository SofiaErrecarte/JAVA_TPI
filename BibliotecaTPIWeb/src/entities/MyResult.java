package entities;



public class MyResult {

	private int status;
	private String err_message;
	public enum results{
		OK,
		Err,
		Warning
	}
	
	private results result;
	
	public results getResult() {
		return result;
	}
	public void setResult(results result) {
		this.result = result;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getErr_message() {
		return err_message;
	}
	public void setErr_message(String err_message) {
		this.err_message = err_message;
	}
	
}
