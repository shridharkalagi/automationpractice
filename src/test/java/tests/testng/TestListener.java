package tests.testng;

import io.qameta.allure.*;
import io.qameta.allure.model.Link;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import setup.*;

public class TestListener implements ITestListener {
	private final static Logger logger = Logger.getLogger(TestListener.class);

	@Override
	public synchronized void onStart(ITestContext context) {
		logger.info("Test Suite started!");
	}

	@Override
	public synchronized void onFinish(ITestContext context) {
		logger.info(("Test Suite is ending!"));
		FrameworkProperties.writeProperties();
	}

	@Override
	public synchronized void onTestStart(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " started!"));
		browserStackLinkGenerator();
	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " passed!"));
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " failed!"));
	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {
		logger.info((result.getMethod().getMethodName() + " skipped!"));
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		logger.info(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private void browserStackLinkGenerator() {
		Allure.addLinks(new Link()
			.setName("BrowserStack")
			.setUrl("https://automate.browserstack.com/builds/f535bea9b4c4f1050bccd80507ab9b175b959d8a/sessions/" + DriverBase.get().getSession(DriverBase.get().getDriver())));
	}

}
