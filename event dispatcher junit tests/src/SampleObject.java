
public class SampleObject {
	private int intTest;
	private String strParam;
	private float floatParam;
	private Object objectParam;

	public SampleObject(int intParam, String strParam, float floatParam, Object objectParam) {
		this.intTest = intParam;
		this.strParam = strParam;
		this.floatParam = floatParam;
		this.objectParam = objectParam;
	}

	public int getIntTest() {
		return intTest;
	}

	public void setIntTest(int intTest) {
		this.intTest = intTest;
	}

	public String getStrParam() {
		return strParam;
	}

	public void setStrParam(String strParam) {
		this.strParam = strParam;
	}

	public float getFloatParam() {
		return floatParam;
	}

	public void setFloatParam(float floatParam) {
		this.floatParam = floatParam;
	}

	public Object getObjectParam() {
		return objectParam;
	}

	public void setObjectParam(Object objectParam) {
		this.objectParam = objectParam;
	}
}
