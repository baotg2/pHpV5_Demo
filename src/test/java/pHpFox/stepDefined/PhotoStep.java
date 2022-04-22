package pHpFox.stepDefined;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebElement;
import pHpFox.pageObject.Components;
import pHpFox.support.DataExcutor;
import pHpFox.support.IsComponentVisible;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static pHpFox.conf.Index.driver;
import static pHpFox.conf.Index.selectPlatform;

public class PhotoStep {
    IsComponentVisible isComponentVisible = new IsComponentVisible();
    Components components = new Components();
    DataExcutor dataExcutor = new DataExcutor();
    Integer idOfLastImageBeforeUpload;
    @Then("the user want to add new album")
    public void addAlbum(){
        isComponentVisible.waitElement(By.xpath("//form[@data-testid ='form']"));
        components.componentButtonDataTestID("add_new_album").click();
        isComponentVisible.waitElement(By.xpath("//input[@data-testid ='inputNewAlbumName']"));
        components.componentInputDataTestID("inputNewAlbumName").sendKeys(dataExcutor.readConstants("Album"));
        components.componentTextAreaDataTestID("inputNewAlbumDescription").sendKeys(dataExcutor.readConstants("Description"));
    }

    @Then("the user want upload {int} photo")
    public void addPhoto(int numberOfImage) throws IOException {
        isComponentVisible.waitElement(By.xpath("//form[@data-testid ='form']"));
        if(selectPlatform.equals("browserStack")){
            WebElement upload = components.componentInputType("file");
            ((RemoteWebElement) upload ).setFileDetector(new LocalFileDetector());
            upload.sendKeys(dataExcutor.getRandomPathDocuments());
            if(numberOfImage > 1){
                for (int i=0; i<numberOfImage; i++){
                    isComponentVisible.waitElement(By.xpath("//button[text() ='Add Photos']"));
                    upload.sendKeys(dataExcutor.getRandomPathDocuments());
                }
            }
        }
        else {
            components.componentInputType("file").sendKeys(dataExcutor.getRandomPathDocuments());
            if(numberOfImage > 1){
                for (int i=0; i<numberOfImage; i++){
                    isComponentVisible.waitElement(By.xpath("//button[text() ='Add Photos']"));
                    components.componentInputType("file").sendKeys(dataExcutor.getRandomPathDocuments());
                }
            }
        }
    }


    @And("^the user verify \"([^\"]*)\" before upload$")
    public Integer getNumberOfLastImage (String itemName){
        isComponentVisible.waitElement(By.xpath("//div[@data-testid='"+itemName+"']//a"));
        List<WebElement> listElementImage = driver.findElements(By.xpath("//div[@data-testid='"+itemName+"']//a"));
        int index = listElementImage.get(0).getAttribute("href").lastIndexOf('/');
        String lastString = listElementImage.get(0).getAttribute("href").substring(index +1);
        idOfLastImageBeforeUpload = Integer.valueOf(lastString);
        return idOfLastImageBeforeUpload;
    }

    @And("^the user verify \"([^\"]*)\" after upload$")
    public boolean isUploadSuccess(String itemName){
        driver.navigate().refresh();
        isComponentVisible.waitElement(By.xpath("//div[@data-testid='"+itemName+"']//a"));
        boolean ass = false;
        List<WebElement> listElementImage = driver.findElements(By.xpath("//div[@data-testid='"+itemName+"']//a"));
        int index = listElementImage.get(0).getAttribute("href").lastIndexOf('/');
        String lastString = listElementImage.get(0).getAttribute("href").substring(index +1);
        int idImageUploaded = Integer.parseInt(lastString);
        if (idImageUploaded > idOfLastImageBeforeUpload){
            ass = true;
        }
        return ass;
    }
    @And("^the user back to home page$")
    public void isBackToHomePage(){
        components.componentLinkDataTestID("linkLogo").click();
    }

    @And("^the user see title \"([^\"]*)\" is displayed")
    public void isTitleDisplayed(String titleName){
        assertTrue(components.componentH1ItemTitle(titleName).isDisplayed());
    }

    @And("^the user click on div \"([^\"]*)\" and process")
    public void accessBlogOnSearchResult(String itemName) throws InterruptedException {
        isComponentVisible.waitElement(By.xpath("//div[@data-testid='"+itemName+"']"));
        isComponentVisible.iWaitForSeconds(2);
        driver.findElement(By.xpath("//div[@data-testid='"+itemName+"']")).click();
    }
    @And("^the user see result of \"([^\"]*)\" displayed")
    public void isReactionSuccess(String reactionName){
        isComponentVisible.waitElement(By.xpath("//span[@data-testid='"+reactionName+"']"));
        assertTrue(components.componentSpanDataTestID(reactionName).isDisplayed());
    }
}
