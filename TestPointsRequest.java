package TFSAutomation.TFSAutomation;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestPointsRequest {

	private String columns;
	private int configurationFilter;
	private String outcomeFilter;
	private boolean recursive;
	private boolean repopulateSuite;
	private int testPlanId;
	private int testSuiteId;
	private String testerFilter;
	private int top;
	
	public String getColumns() {
		return columns;
	}
	public void setColumns(String columns) {
		this.columns = columns;
	}
	public int getConfigurationFilter() {
		return configurationFilter;
	}
	public void setConfigurationFilter(int configurationFilter) {
		this.configurationFilter = configurationFilter;
	}
	public String getOutcomeFilter() {
		return outcomeFilter;
	}
	public void setOutcomeFilter(String outcomeFilter) {
		this.outcomeFilter = outcomeFilter;
	}
	public boolean isRecursive() {
		return recursive;
	}
	public void setRecursive(boolean recursive) {
		this.recursive = recursive;
	}
	public boolean isRepopulateSuite() {
		return repopulateSuite;
	}
	public void setRepopulateSuite(boolean repopulateSuite) {
		this.repopulateSuite = repopulateSuite;
	}
	public int getTestPlanId() {
		return testPlanId;
	}
	public void setTestPlanId(int testPlanId) {
		this.testPlanId = testPlanId;
	}
	public int getTestSuiteId() {
		return testSuiteId;
	}
	public void setTestSuiteId(int testSuiteId) {
		this.testSuiteId = testSuiteId;
	}
	public String getTesterFilter() {
		return testerFilter;
	}
	public void setTesterFilter(String testerFilter) {
		this.testerFilter = testerFilter;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	
}
