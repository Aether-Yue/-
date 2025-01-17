package com.automation.service;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
*失败重试
 */
public class Retry implements IRetryAnalyzer {
    private int retryCount = 0;
    private int maxRetryCount = 1;//失败重试次数

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < maxRetryCount) {
            System.out.println("Retrying test " + result.getName() + " with status "
                    + getResultStatusName(result.getStatus()) + " for the " + (retryCount + 1) + " time(s).");
            retryCount++;
            return true;
        }
        resetRetrycount(); // 每次跑完一条用例后，重置retryCount为0，这样dataProvider 数据驱动测试支持
        return false;
    }

    public String getResultStatusName(int status) {
        String resultName = null;
        if (status == 1)
            resultName = "SUCCESS";
        if (status == 2)
            resultName = "FAILURE";
        if (status == 3)
            resultName = "SKIP";
        return resultName;
    }

    public boolean isRetryAvailable() {
        return retryCount < maxRetryCount;
    }

    public void resetRetrycount() {
        retryCount = 0;
    }

}
