package bean;

/**
 * Class created for the adress bean, contains: id, street, province, country, zip and phone#
 *
 */

public class AddressBean {
	private int id;
	private String streett;
	private String province;
	private String country;
	private String zip;
	private String phone;
	
	/**
	 * Constructor for creating an address bean
	 * @param id
	 * @param streett
	 * @param province
	 * @param country
	 * @param zip
	 * @param phone
	 */
	public AddressBean(int id, String streett, String province, String country, String zip, String phone) {
		super();
		this.id = id;
		this.streett = streett;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStreett() {
		return streett;
	}

	public void setStreett(String streett) {
		this.streett = streett;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
