package com.gorest.cucumber;

import com.gorest.userinfo.UserSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;


public class MyStepdefs {
    static ValidatableResponse response;
    static int userID;
    static String name ;
    @Steps
    UserSteps userSteps;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response= userSteps.getAllUserInfo();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^I create a new user by providing the information name \"([^\"]*)\"  email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iCreateANewUserByProvidingTheInformationNameEmailGenderStatus(String name, String email, String gender, String status)  {
        response = userSteps.createUser(name, email, gender, status);

    }

    @Then("^I verify that the user with \"([^\"]*)\" is created$")
    public void iVerifyThatTheUserWithIsCreated(String field)  {
        response.statusCode(201);
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        userID = (int) userMap.get("id");
        Assert.assertThat(userMap, hasValue(name));
    }

    @And("^I update the user with information name \"([^\"]*)\"  email \"([^\"]*)\" gender \"([^\"]*)\" status \"([^\"]*)\"$")
    public void iUpdateTheUserWithInformationNameEmailGenderStatus(String name, String email, String gender, String status)  {

        name = name +"_updated";
        response = userSteps.updateUser(userID, name, email, gender, status);

    }

    @And("^I delete the user that created with name \"([^\"]*)\"$")
    public void iDeleteTheUserThatCreatedWithName(String arg0)  {
        response = userSteps.deleteUser(userID);
    }

    @Then("^The user deleted successfully from the list$")
    public void theUserDeletedSuccessfullyFromTheList() {
        response.statusCode(204);
        userSteps.getStoreById(userID).statusCode(404);
    }
}
