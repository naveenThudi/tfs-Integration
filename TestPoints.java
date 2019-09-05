package TFSAutomation.TFSAutomation;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestPoints {
	
	private int testCaseId;
	
	private int testPointId;

	public int getTestCaseId() {
		return testCaseId;
	}

	public void setTestCaseId(int testCaseId) {
		this.testCaseId = testCaseId;
	}

	public int getTestPointId() {
		return testPointId;
	}

	public void setTestPointId(int testPointId) {
		this.testPointId = testPointId;
	}
	
	

}
