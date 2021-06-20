package ManagementForm;

import java.util.Arrays;

public class Pet {
	private String petID;
	private String customerID;
	private String petName;
	private String petCategory;
	private byte[] picture;
	private String camNum;

	
	public Pet() {
		}


	public Pet(String petID, String customerID, String petName, String petCategory, byte[] picture, String camNum) {
		super();
		this.petID = petID;
		this.customerID = customerID;
		this.petName = petName;
		this.petCategory = petCategory;
		this.picture = picture;
		this.camNum = camNum;
	}


	public String getPetID() {
		return petID;
	}


	public void setPetID(String petID) {
		this.petID = petID;
	}


	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public String getPetName() {
		return petName;
	}


	public void setPetName(String petName) {
		this.petName = petName;
	}


	public String getPetCategory() {
		return petCategory;
	}


	public void setPetCategory(String petCategory) {
		this.petCategory = petCategory;
	}


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public String getCamNum() {
		return camNum;
	}


	public void setCamNum(String camNum) {
		this.camNum = camNum;
	}


	@Override
	public String toString() {
		return "Pet [petID=" + petID + ", customerID=" + customerID + ", petName=" + petName + ", petCategory="
				+ petCategory + ", picture=" + Arrays.toString(picture) + ", camNum=" + camNum + "]";
	}

	
	
	
	
}