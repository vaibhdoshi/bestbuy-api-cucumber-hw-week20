package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.StoresSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoreSteps {
    static ValidatableResponse response;
    public static String name = "Abc" + TestUtils.getRandomValue();
    public static String type = "Def";
    public static String address = "999 1st Street A";
    public static String address2 = "abc";
    public static String city = "London";
    public static String state = "MN";
    public static String zip = "25687";
    public static double lat = 44.879314;
    public static double lng = 93.077156;
    public static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    public static int storeID;
    @Steps
    StoresSteps storesSteps;


    @When("^User sends a Get request for store$")
    public void userSendsAGetRequestForStore() {
        response = storesSteps.getAllStores();
    }

    @Then("^User should get list of stores and must get back a valid status code 200$")
    public void userShouldGetListOfStoresAndMustGetBackAValidStatusCode() {
        response.statusCode(200).log().all();
    }

    @When("^User creates new store record with all information$")
    public void userCreatesNewStoreRecordWithAllInformation() {
        storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours).log().all().statusCode(201);
    }

    @Then("^I verify that new store is created and must get valid status code 201$")
    public void iVerifyThatNewStoreIsCreatedAndMustGetValidStatusCode() {
        HashMap<String, Object> storeRecord = storesSteps.getStoreInformationWithName(name);
        Assert.assertThat(storeRecord, hasValue(name));
        storeID = (int) storeRecord.get("id");
    }

    @When("^User can update new record with all store details$")
    public void userCanUpdateNewRecordWithAllStoreDetails() {
        name = name + TestUtils.getRandomValue();
        storesSteps.changeStoreInfo(storeID, name, type, address, address2, city, state, zip, lat, lng, hours).log().all().statusCode(200);
        HashMap<String, Object> storeRecord = storesSteps.getStoreInformationWithName(name);
        Assert.assertThat(storeRecord, hasValue(name));
    }

    @Then("^I verify that store is updated$")
    public void iVerifyThatStoreIsUpdated() {
        HashMap<String, Object> storeRecord = storesSteps.getStoreInformationWithName(name);
        Assert.assertThat(storeRecord, hasValue(name));
        storeID = (int) storeRecord.get("id");
    }

    @When("^User can delete new record for store$")
    public void userCanDeleteNewRecordForStore() {
        storesSteps.deleteStoreRecord(storeID).log().all().statusCode(200);
    }

    @Then("^I verify store is deleted and must get valid status code 404$")
    public void iVerifyStoreIsDeletedAndMustGetValidStatusCode() {
        storesSteps.verifyStoreRecord(storeID).log().all().statusCode(404);

    }
}
