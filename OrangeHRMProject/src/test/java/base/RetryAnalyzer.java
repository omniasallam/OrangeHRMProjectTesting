package base;

import Utlis.TestLogger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/** Bonus: retry a failed test up to 2 times before marking it as failed. */
public class RetryAnalyzer implements IRetryAnalyzer {
    private static final int MAX_RETRY = 2;
    private int count = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            TestLogger.logger.warn("Retrying test " + result.getName() + " (attempt " + count + " of " + MAX_RETRY + ")");
            return true;
        }
        return false;
    }
}
