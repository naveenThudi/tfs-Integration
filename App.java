package TFSAutomation.TFSAutomation;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


/**
 * Hello world!
 *
 */
public class App 
{
	/*@Autowired
	RestTemplate restTemplate;
	*/
	
    public static void main( String[] args ) throws NoSuchAlgorithmException, KeyManagementException
	{
    	
    	SSLContext ctx = SSLContext.getInstance("TLS"); ctx.init(new KeyManager[0],
  			  new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());
  			  SSLContext.setDefault(ctx);
		
  	    String projectName = "Access DLLs";
  	    int testSuiteId = 16;
  	    int testPlanId = 14;
  	    int testCaseId = 12;
  	    String changeStatusTo = "passed";
		String projectId = getProjectId(projectName);
		int testPointId = getTestPointId(projectId,testPlanId,testSuiteId,testCaseId);
		updateTestCaseStatus(testPlanId,testSuiteId,testPointId,changeStatusTo,projectId,testCaseId);
		
		
	
	}
    



	private static void updateTestCaseStatus(int testPlanId, int testSuiteId, int testPointId, String changeStatusTo,String projectId,int testCaseId) {
		
		UpdateStatusRequest updateStatusRequest =  makeUpdateRequest(testPlanId,testSuiteId,testPointId,changeStatusTo);
		String uri = "https://dev.azure.com/nreddythudi/"+projectId+"/_api/_testManagement/BulkMarkTestPoints?__v=5";
		/*Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("projectId",projectId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);*/
		
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestWithGetBodyFactory());
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic bnJlZGR5dGh1ZGk6bDRnZXF6ajd3ZmhhcDN3Mnlodml2azJ1em90cmd6Y2N2d2djcWR4anZxNHh0YmVlMjJwYQ==");
		HttpEntity<UpdateStatusRequest> entity = new HttpEntity<UpdateStatusRequest>(updateStatusRequest,headers);
		ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
		if (result.getStatusCode().value() == 200) {
			System.out.println("Successfully updated Test status for " + testCaseId);
		}else
			System.out.println("Unable to update the status of Test for " + testCaseId);
	}




	private static UpdateStatusRequest makeUpdateRequest(int testPlanId, int testSuiteId, int testPointId,
			String changeStatusTo) {
		UpdateStatusRequest updateStatusRequest = new UpdateStatusRequest();
		List<Integer> testPointIdList = new ArrayList<>();
		testPointIdList.add(testPointId);
		updateStatusRequest.setPlanId(testPlanId);
		updateStatusRequest.setSuiteId(testSuiteId);
		updateStatusRequest.setTestPointIds(testPointIdList);
		updateStatusRequest.setOutcome(getStatusCodes(changeStatusTo));
		
		
		return updateStatusRequest;
	}

	private static Integer getStatusCodes(String changeStatusTo) {
		Map<String, Integer> map = new HashMap<>();
		map.put("passed", 2);
		map.put("failed", 3);
		map.put("inconclusive",4);
		map.put("active",1);
		map.put("block test",5);
		return map.get(changeStatusTo);
	}




	private static int getTestPointId(String projectId, int testPlanId, int testSuiteId,int testCaseId) {
		TestPointsRequest testPointRequest = makeTestPointRequest(testPlanId,testSuiteId);
		String uri = "https://dev.azure.com/nreddythudi/"+projectId+"/_api/_testManagement/GetTestPointsForSuite";
		/*Map<String, String> uriParams = new HashMap<String, String>();
		uriParams.put("projectId",projectId);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);*/
		
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestWithGetBodyFactory());
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic bnJlZGR5dGh1ZGk6bDRnZXF6ajd3ZmhhcDN3Mnlodml2azJ1em90cmd6Y2N2d2djcWR4anZxNHh0YmVlMjJwYQ==");
		HttpEntity<TestPointsRequest> entity = new HttpEntity<TestPointsRequest>(testPointRequest,headers);
		ResponseEntity<TestPointsResponse> result = restTemplate.exchange(uri, HttpMethod.POST, entity, TestPointsResponse.class);
		return fetchTestPointId(result.getBody().getTestPoints(),testCaseId);
	}


	private static int fetchTestPointId(List<TestPoints> testPoints,int testCaseId) {
		TestPoints testPointDetails = testPoints.stream()
				  .filter(testPoint -> testCaseId == testPoint.getTestCaseId())
				  .findAny()
				  .orElse(null);
		
		return testPointDetails.getTestPointId();
	}


	private static TestPointsRequest makeTestPointRequest(int testPlanId, int testSuiteId) {
		
		TestPointsRequest testPointsRequest = new TestPointsRequest();
		testPointsRequest.setColumns(null);
		testPointsRequest.setConfigurationFilter(-1);
		testPointsRequest.setOutcomeFilter("");
		testPointsRequest.setRecursive(false);
		testPointsRequest.setRepopulateSuite(true);
		testPointsRequest.setTesterFilter("ALL");
		testPointsRequest.setTestPlanId(testPlanId);
		testPointsRequest.setTestSuiteId(testSuiteId);
		testPointsRequest.setTop(500);
		return testPointsRequest;
	}


	private static String getProjectId(String projectName) {
    	String projectId =  null;
    	final String uri = "https://dev.azure.com/nreddythudi/_apis/projects";
		RestTemplate restTemplate = new RestTemplate(new SimpleClientHttpRequestWithGetBodyFactory());
		HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "Basic bnJlZGR5dGh1ZGk6bDRnZXF6ajd3ZmhhcDN3Mnlodml2azJ1em90cmd6Y2N2d2djcWR4anZxNHh0YmVlMjJwYQ==");
		HttpEntity<ProjectDetails> entity = new HttpEntity<ProjectDetails>( headers);

		ResponseEntity<ProjectDetails> result = restTemplate.exchange(uri, HttpMethod.GET, entity, ProjectDetails.class);
		ProjectDetails projectDetials = result.getBody();
		
		Value value = projectDetials.getValue().stream()
				  .filter(val -> projectName.equals(val.getName()))
				  .findAny()
				  .orElse(null);
		if(null != value)
			 projectId = value.getId();
		
		return projectId;
	}

	public static class DefaultTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			// TODO Auto-generated method stub
			
		}

		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
