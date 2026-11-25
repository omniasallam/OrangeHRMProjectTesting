package base;

import Utlis.DriverFactory;
import Utlis.TestLogger;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

/** Logs every test (INFO) and failures (ERROR) and attaches a screenshot to Allure on failure. */
public class TestListener implements ITestListener {
    @Override public void onTestStart(ITestResult r) { TestLogger.logger.info("START: " + r.getName()); }
    @Override public void onTestSuccess(ITestResult r) { TestLogger.logger.info("PASS: " + r.getName()); }
    @Override public void onTestSkipped(ITestResult r) { TestLogger.logger.warn("SKIPPED/RETRIED: " + r.getName()); }

    @Override
    public void onTestFailure(ITestResult r) {
        TestLogger.logger.error("FAIL: " + r.getName() + " - " + (r.getThrowable() == null ? "" : r.getThrowable().getMessage()));
        screenshot();
    }

    @Attachment(value = "Failure screenshot", type = "image/png")
    public byte[] screenshot() {
        if (DriverFactory.getDriver() == null) return new byte[0];
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
