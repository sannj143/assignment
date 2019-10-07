package com.hiver.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class CaptureScreenshotUtil {
	private static final Logger LOG = Logger.getLogger(CaptureScreenshotUtil.class.getName());

	public static void captureScreenshot(WebDriver driver, ITestResult result) {
		Screenshot capture = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		String testClassName = StringUtils.substringAfterLast(result.getTestClass().getName(), ".");

		File file = new File("./screenshots/" + result.getTestContext().getSuite().getName() + "/" + testClassName);
		try {
			if (file.exists() && file.isDirectory()) {

				ImageIO.write(capture.getImage(), "png",
						new File(file.getPath() + "/" + result.getMethod().getMethodName() + ".png"));

			} else {
				file.mkdir();
				ImageIO.write(capture.getImage(), "png",
						new File(file.getPath() + "/" + result.getMethod().getMethodName() + ".png"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOG.info("Screenshot Captured!!!!!!!");
	}

	public static void cleanFolder(String suiteName) {
		File file = new File("./screenshots/" + suiteName + "/");

		try {
			if (file.exists() && file.isDirectory()) {
				FileUtils.cleanDirectory(file);
				LOG.info("Screesshots have been deleted successfully from " + suiteName + " Folder");
			}else
			{
				file.mkdir();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
