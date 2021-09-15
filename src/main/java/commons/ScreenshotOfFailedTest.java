package commons;

import org.openqa.selenium.NoSuchSessionException;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ScreenshotOfFailedTest extends Base implements ITestListener{

	


	/**
	 * @throws N/A
	 * @Description get the test suite Name that its failing then take an screenshot if it failed.
	 * @Author Sergio Ramones
	 * @Date 04-JUN-2021 
	 * @Parameter ITestResult
	 * @return N/A
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			driver = getDriver();

			if (driver.toString().contains("null") == false) {
				takeScreenShot();
				Reporter.log("********* Error " + result.getStatus() + " test has failed **********", true);
				Reporter.log("********* Error " + result.getTestName() + " test has failed **********", true);
				Reporter.log("********* Error " + result.getMethod() + " test has failed **********", true);
				Reporter.log("********* Error " + result.getName() + " test has failed **********", true);

				try {
					VideoRecorder_utlity.stopRecord();
				} catch (Exception e) {
					Reporter.log("********* Video can't stop becaouse has not started. **********", true);
				}

			}//end if

		} catch (NoSuchSessionException e) {
			Reporter.log("********* Error " + result.getTestName()
					+ " test has can not be executed as Session ID is null **********", true);
		}
	}// end OntestFailure
	
		
}
