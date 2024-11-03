package locators;

import models.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConstructorPage {
    private final WebDriver driver;

    private final By testElement = By.xpath("//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath("//span[text()='Булки']//parent::div");
    private final By saucesButton = By.xpath("//span[text()='Соусы']//parent::div");
    private final By ingredientsButton = By.xpath("//span[text()='Начинки']//parent::div");

    public ConstructorPage(WebDriver driver){
        this.driver = driver;
    }

    public void isPageOpened(){
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(testElement));
    }

    private boolean isButtonSelected(By button){
        var classes = driver.findElement(button).getAttribute("class");
        return classes.contains("tab_tab_type_current__2BEPc");
    }

    private void clickButton(By button){
        driver.findElement(button).click();
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.attributeContains(button, "class", Constants.SELECTED_CLASS_ATTRIBUTE));
    }

    public void clickBunsButton(){
        clickButton(bunsButton);
    }

    public void clickSaucesButton(){
        clickButton(saucesButton);
    }

    public void clickIngredientsButton(){
        clickButton(ingredientsButton);
    }

    public boolean isBunsButtonSelected(){
        return isButtonSelected(bunsButton);
    }

    public boolean isSaucesButtonSelected(){
        return isButtonSelected(saucesButton);
    }

    public boolean isIngredientsButtonSelected(){
        return isButtonSelected(ingredientsButton);
    }

}
