package prj01;

public class SearchDto {
	private String key;
	private String value;
	
	public SearchDto() {
		
	}
	
	public SearchDto(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
			
	@Override
	public String toString() {
		return "SearchDto [key=" + key + ", value=" + value + "]";
	}
	
}
