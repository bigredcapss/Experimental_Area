package bean;

public class PhoneNumber {
	private String countryCode;
	private String stateCode;
	private String number;
	public PhoneNumber() {
		
	}
	public PhoneNumber(String str){
		String[] strs = str.split("[-]");
		this.countryCode = strs[0];
		this.stateCode = strs[1];
		this.number = strs[2];
	}
	public String setAsString(){
		return countryCode+"-"+stateCode+"-"+number;
	}
	public PhoneNumber(String countryCode, String stateCode, String number) {
		super();
		this.countryCode = countryCode;
		this.stateCode = stateCode;
		this.number = number;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "PhoneNumber [countryCode=" + countryCode + ", stateCode=" + stateCode + ", number=" + number + "]";
	}
	
	
	

}
