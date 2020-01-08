package apidispatcher.apigateway.platenogroup.com;

public class ComplexObject {

	public ComplexObject() {
	}

	public ComplexObject(String msg) {
		System.out.println("我已经被创建了 - " + msg);
	}

	private String id;
	private Integer value;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

//	@Override
//	public String toString() {
//		return "ComplexObject [id=" + id + ", value=" + value + "]";
//	}

}
