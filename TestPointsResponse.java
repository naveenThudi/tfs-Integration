package TFSAutomation.TFSAutomation;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestPointsResponse {
	
	private List<TestPoints> testPoints;

	public List<TestPoints> getTestPoints() {
		return testPoints;
	}

	public void setTestPoints(List<TestPoints> testPoints) {
		this.testPoints = testPoints;
	}
	
	

}
