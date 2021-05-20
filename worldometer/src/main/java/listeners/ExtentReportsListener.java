package listeners;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Test;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ExtentReportsListener implements IReporter {

    private ExtentReports extentReports;

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        extentReports = new ExtentReports("Extent.html",true);

        for(ISuite iSuite : suites){
            Map<String, ISuiteResult> result = iSuite.getResults();

            for(ISuiteResult iSuiteResult:result.values()){
                ITestContext iTestContext = iSuiteResult.getTestContext();

                buildTestNodes(iTestContext.getPassedTests(), LogStatus.PASS);
                buildTestNodes(iTestContext.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(iTestContext.getSkippedTests(), LogStatus.SKIP);
            }
        }

        extentReports.flush();
        extentReports.close();
    }

    private void buildTestNodes(IResultMap iResultMap,LogStatus logStatus){
        ExtentTest extentTest;

        if(iResultMap.size() > 0){
            for(ITestResult iTestResult: iResultMap.getAllResults()){
                extentTest = extentReports.startTest(iTestResult.getMethod().getMethodName());
                extentTest.setStartedTime(getTime(iTestResult.getStartMillis()));
                extentTest.setEndedTime(getTime(iTestResult.getEndMillis()));
                for(String group: iTestResult.getMethod().getGroups()){
                    extentTest.assignCategory(group);
                }
                if (iTestResult.getThrowable() != null) {
                    extentTest.log(logStatus, iTestResult.getThrowable());
                } else {
                    extentTest.log(logStatus, "Test " + logStatus.toString().toLowerCase()
                            + "ed");
                }

                extentReports.endTest(extentTest);
            }
        }
    }

    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();

    }
}
