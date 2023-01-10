package com.gorest.cucumber.steps;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.UserSteps;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.core.annotations.WithTag;
import net.thucydides.core.annotations.WithTags;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Random;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class UserCRUDTestWithSteps extends TestBase {

    public static String getRandomValue(){
        Random random = new Random();
        int randomInt = random.nextInt(100000);
        return Integer.toString(randomInt);
    }

    static String name = "ABCD"+getRandomValue() ;
    static String email= "1234"+getRandomValue()+"@gmail.com";
    static String gender = "male";
    static String status = "active";
    static int userId;




    @Steps
    UserSteps userSteps;
    @WithTag("userfeature:NEGATIVE")
    @Title("This will create new user")
    @Test
    public void test001(){
         ValidatableResponse response = userSteps.createUser( name,  email,  gender,  status);
         response.log().all().statusCode(201);
    }
    @WithTags({
    @WithTag("userfeature:SMOKE"),
    @WithTag("userfeature:NEGATIVE")})
    @Title("Verify if the user was added to the application")
    @Test
    public void test002(){
        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap,hasValue(name));
        userId = (int) userMap.get("id");

    }
    @WithTags({
            @WithTag("userfeature:SANITY"),
            @WithTag("userfeature:NEGATIVE")})

    @Title("Update the user information and verify the updated information")
    @Test
    public void test003(){
        name = name+ "_updated";
       ValidatableResponse  response =userSteps.updateUser(userId,name,email,gender,status);
      response.log().all().statusCode(200);

        HashMap<String, Object> userMap = userSteps.getUserInfoByName(name);
        Assert.assertThat(userMap,hasValue(name));


    }
    @WithTag("userfeature:POSITIVE")
    @Title("Delete the product and verify if the product is deleted!")
    @Test
    public void test004() {
        userSteps.deleteUser(userId).statusCode(204);
        userSteps.getStoreById(userId) .statusCode(404);

    }






}
