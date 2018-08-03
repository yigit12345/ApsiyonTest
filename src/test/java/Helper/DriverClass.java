package Helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass {
    /**
     * Yiğithan KADIOĞLU
     */

    //region Crossbrowser Testing cloud
/*
    static String username = ";
    static String authkey = "";
    public RemoteWebDriver remoteWebDriver;

    public RemoteWebDriver setRemoteWebDriver(String name, String build, String browserName, String version, String platform, String screenResolution, String record_video, String record_network) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("name", name);
        caps.setCapability("build", build);
        caps.setCapability("browserName", browserName);
        caps.setCapability("version", version);
        caps.setCapability("platform", platform);
        caps.setCapability("screenResolution", screenResolution);
        caps.setCapability("record_video", record_video);
        caps.setCapability("record_network", record_network);

        remoteWebDriver = new RemoteWebDriver(new URL("http://" + username + ":" + authkey + ""), caps);
        System.out.println(remoteWebDriver.getSessionId());
        return remoteWebDriver;
    }*/

   //endregion

    //region Local Testing Driver
    public WebDriver setUpDriver() {
        System.setProperty("webdriver.chrome.driver","//Users/elektronetuser/Desktop/chromedriver_/chromedriver");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
    //endregion
}
