package TFSAutomation.TFSAutomation;

import java.util.List;

public class UpdateStatusRequest {

	private int planId;
	private int suiteId;
	private List<Integer> testPointIds;
	private int outcome;
	
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getSuiteId() {
		return suiteId;
	}
	public void setSuiteId(int suiteId) {
		this.suiteId = suiteId;
	}
	public List<Integer> getTestPointIds() {
		return testPointIds;
	}
	public void setTestPointIds(List<Integer> testPointIds) {
		this.testPointIds = testPointIds;
	}
	public int getOutcome() {
		return outcome;
	}
	public void setOutcome(int outcome) {
		this.outcome = outcome;
	}
	
	
	
}
