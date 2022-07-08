package MetaFox.stepDefined;

import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import MetaFox.browserConfig.Index;
import MetaFox.pageObject.Components;
import MetaFox.support.DataExecutor;
import MetaFox.support.IsComponentVisible;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * ---------------------------------------------------------------------------------------------------------------------------------------------
 *
 * @version 1.0.1
 * ---------------------------------------------------------------------------------------------------------------------------------------------
 * @Author baotg2
 * @purpose: WhenStepDefinitions is class defined all steps use Method @When
 * @since 04-05-2022
 */
public class WhenStepDefinitions {
    Components components = new Components( Index.getDriver() );
    IsComponentVisible isComponentVisible = new IsComponentVisible( Index.getDriver() );
    DataExecutor dataExecutor = new DataExecutor();

    /**------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param item item name
     * @param url link to page want to open
     * @purpose link to page on Side Menu
     * ------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When ( "^the browser opened at item \"([^\"]*)\" and tab \"([^\"]*)\"$" )
    public void openNewURL( String item , String url ) throws InterruptedException {
        Thread.sleep(3000);
        isComponentVisible.waitElement(By.xpath("//a[contains(@href, '" + item + "')]"));
        components.componentLinkText( item ).click();
        if ( !Objects.equals( url , "" ) ) {
            components.componentLinkText( url ).click();
        }
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param item link want to moved
     * @purpose moved to other page by href
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     */
    @When ( "^the user want to click on \"([^\"]*)\"$" )
    public void accessNewPage( String item ) {
        isComponentVisible.waitElement( By.xpath( "//a[contains(@href, '" + item + "')]" ) );
        components.componentLinkText( item ).click();
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param item value behind url homepage
     * @purpose move to page has format homepgarUrl/item
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When ( "^the move to page \"([^\"]*)\"$" )
    public void openNewPage( String item ) {
        Index.getDriver().get( dataExecutor.readConstants("URL") + item);
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------------
     * @purpose move to previous page
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When ( "^back to previous page" )
    public void backToPreviousPage() {
        Index.getDriver().navigate().back();
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param placeHolder style of field sign up
     * @param value input
     * @purpose input value of email form
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When ( "^the user input info sign up \"([^\"]*)\" with value \"([^\"]*)\"$" )
    public void valueEmailSignUp( String placeHolder , String value ) {
        components.componentSearchAttributes( placeHolder ).sendKeys( value );
        components.componentSearchAttributes( placeHolder ).sendKeys( Keys.ENTER );
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param value of tool tip
     * @purpose access tool tip on user profile from more
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When ( "^the user want access tool tip \"([^\"]*)\" from More")
    public void accessToolTip(String value){
        components.componentTooltip(value).click();
    }

    /**-----------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param value is value want to input
     * @param element is element on web page
     * @purpose add value on TextArea
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-02-2022
     */
    @When("^the user add value \"([^\"]*)\" on textArea \"([^\"]*)\"$")
    public void addContent(String value, String element){
        isComponentVisible.waitElement(By.xpath("//textarea[@data-testid ='" + element +"']"));
        components.componentTextAreaDataTestID(element).clear();
        components.componentTextAreaDataTestID(element).sendKeys(value);
    }

    /**------------------------------------------------------------------------------------------------------------------------------------
     * @purpose the user want to change cover image
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user want to changed cover image")
    public void changeCOverImage(){
        isComponentVisible.waitElement(By.xpath("//div[@data-testid='upload_photo']"));
        components.componentDivDataTestID("upload_photo").click();
        dataExecutor.uploadFile(dataExecutor.getRandomPathDocuments().replace( "\\\\" , "\\" ));
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param index is index of element on dropdown list
     * @purpose select elements on dropdown list has position index
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @Since 04-05-2022
     */
    @When("the user want to select category at {int} on dropdown list {string}")
    public void selectCategory(int index, String dropdown_listName) throws InterruptedException {
        Thread.sleep(2000);
        for (int i = 0; i< index; i++){
            components.componentInputID(dropdown_listName).sendKeys(Keys.DOWN);
        }
        components.componentInputID(dropdown_listName).sendKeys(Keys.ENTER);
    }

    /**------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param actionName is actionName
     * @purpose applied on button click
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user see \"([^\"]*)\" and click on$")
    public void clickOnActionMenu(String actionName){
        components.componentsActionButton(actionName).click();
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param type is type want to select
     * @param id is position of type
     * @purpose select type Polls
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user select type \"([^\"]*)\" on \"([^\"]*)\"$")
    public void selectTypeOnField(@NotNull String type, String id){
        isComponentVisible.waitElement(By.id(id));
        components.componentsListDivRole("button").get(0).click();
        switch (type){
            case "Checkbox":
               components.componentsDivDataValue("2").click();
               break;
            case "Multiple Choice":
                components.componentsDivDataValue("1").click();
                break;
            case "Written Answer":
                components.componentsDivDataValue("0").click();
                break;
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param notification is content of the notification
     * @throws InterruptedException is thrown if interruptedException
     * @purpose click on notification has content is notification
     * @Author baotg2
     * ----------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user click on notification \"([^\"]*)\" and process$")
    public void findAndSelectNotifications(String notification) throws InterruptedException {
        Thread.sleep(3000);
        isComponentVisible.waitElement(By.xpath("//div[@data-testid='itemSummary']"));
        int temp = components.componentListDivDataTestID("itemSummary").size();
        for(int i = 0; i < temp; i ++){
            if (components.componentListDivDataTestID("itemSummary").get(i).getText().contains(notification)){
                components.componentListDivDataTestID("itemSummary").get(i).click();
            }
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @throws InterruptedException is thrown when interruptedException
     * @purpose select first item form tag list
     * @Author baotg2
     * --------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user tag first friend on list tag$")
    public void tagFirstFriendOnList() throws InterruptedException {
        Thread.sleep(6000);
        isComponentVisible.waitElement(By.xpath("//h5[@data-testid='itemTitle']"));
        if( components.componentsListH5DataTestID("itemTitle").isDisplayed()){
           components.componentsListH5DataTestID("itemTitle").click();
           components.componentsListH5DataTestID("itemTitle").click();
        }
        else {
            components.componentsDivMsg("No people found").isDisplayed();
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param actionInvite is action name on invite tab
     * @purpose approve or deny the invite
     * @Author baotg2
     * ----------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-16-2022
     */
    @When("^the user \"([^\"]*)\" on invite$")
    public void actionOnInvite(String actionInvite){
        isComponentVisible.waitElement(By.xpath("//h5[text()='"+actionInvite+"']"));
        components.componentH5Text(actionInvite).click();
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param actionInvite is action name on invite tab
     * @purpose see element h5 has value is actionInvite displayed
     * @Author baotg2
     * ----------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-16-2022
     */
    @When("^the user see h5 text \"([^\"]*)\" displayed$")
    public void isElementH5Display(String actionInvite){
        isComponentVisible.waitElement(By.xpath("//h5[text()='"+actionInvite+"']"));
        assertTrue(components.componentH5Text(actionInvite).isDisplayed());
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param actionInvite name of the user invited
     * @purpose see user invited displayed on invite tab
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user see \"([^\"]*)\" on invited tab$")
    public void actionOnInvitedTab(String actionInvite){
        isComponentVisible.waitElement(By.xpath("//a[text()='"+actionInvite+"']"));
        assertTrue(Index.getDriver().findElement(By.xpath("//a[text()='"+actionInvite+"']")).isDisplayed());
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param type is type want to select on Polls
     * @purpose select type when create a new POlls
     * @Author baotg2
     * ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user select type \"([^\"]*)\"$")
    public void selectType(String type){
        isComponentVisible.waitElement(By.xpath("//input[@aria-label ='"+type+"']"));
        if(!components.componentsInputChecked(type).isSelected()){
            components.componentsInputChecked(type).click();
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param username is the username vote on Polls
     * @throws InterruptedException is thrown if interruptedException
     * @purpose control vote random option of Polls
     * -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user \"([^\"]*)\" action vote on random options$")
    public void actionVoteOnPoll(String username) throws InterruptedException {

        if ( components.componentListButtonText("Vote again").size() !=0)
        {
            components.componentListButtonText("Vote again").get(0).click();
        }
        isComponentVisible.waitElement(By.xpath("//label"));
        int size = components.componentsListLabel().size();
        int randomPosition = isComponentVisible.randomNumber(size);
        components.componentsListLabel().get(randomPosition).click();
        String value = components.componentsListLabel().get(randomPosition).getText();
        components.componentButtonText("Vote").click();
        Thread.sleep(2000);
        if(components.componentsDivMsg("vote").isEnabled()){
            components.componentsDivMsg("vote").click();
            components.componentPText(value).click();
            assertTrue(components.componentLinkText(username).isDisplayed());
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param divText is text of element
     * @purpose verify that element is not displayed
     * @Author baotg2
     * ---------------------------------------------------------------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When("^the user don't see \"([^\"]*)\" displayed on screen$")
    public void isDivDisplayed(String divText){
        assertEquals(0, components.componentsListDivMsg(divText).size());
    }

    /** ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param divText is role vale of the element
     * @purpose click on the div element has role is divText
     * @Author baotg2
     * ---------------------------------------------------------------- ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @When("^the user click on the \"([^\"]*)\" on screen$")
    public void clickOnDiv(String divText){
        isComponentVisible.waitElement(By.xpath("//div[@role='"+divText+"']"));
        components.componentDivRole(divText).click();
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     *
     * @param user is name of the user
     * @purpose see the user interested/invited of events
     * @Author baotg2
     *  ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     * @since 06-15-2022
     */
    @When("^the user see \"([^\"]*)\" on tab$")
    public void verifyUserOnTabGuests(String user){
        if(components.componentsListDivMsg(user).size() != 0){
            assertTrue(components.componentsListDivMsg(user).get(0).isDisplayed());
        }
        else{
            assertTrue(components.componentDivDataTestID("itemUser").isDisplayed());
        }
    }

    /**------------------------------------------------------------------------------------------------------------------------------------------------
     * @purpose refers page before handle another action
     * @Author baotg2
     * -----------------------------------------------------------------------------------------------------------------------------------------------
     * @since 04-05-2022
     */
    @And("the user want to refresh page before handle the action")
    public void theUserWantToRefreshPageBeforeHandleTheAction() {
        Index.getDriver().navigate().refresh();
    }
}