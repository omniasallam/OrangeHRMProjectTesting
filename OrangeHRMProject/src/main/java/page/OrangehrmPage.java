package page;

import org.openqa.selenium.WebDriver;

public class OrangehrmPage {
    private WebDriver driver;
    public OrangehrmPage(WebDriver driver){
        this.driver=driver;
    }
    public String getUrlOrangeHrm(){
       return driver.getCurrentUrl();
    }
}
