package Helper;

import org.openqa.selenium.WebDriver;

public class Util {
    /**
     * Yiğithan KADIOĞLU
     */



    public void Maximize(WebDriver driver){
        //Fullscreen browser window
        System.out.println("Maximizing window");
        driver.manage().window().maximize();
    }
}

