package com.bestbuy.cucumber.steps;

import com.bestbuy.bestbuyinfo.ProductsSteps;
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
public class ProductSteps {
    static ValidatableResponse response;
    public static String name = "Reading books" + TestUtils.getRandomValue();
    public static String type = "ABC" + TestUtils.getRandomValue();
    public static double price = 5.49;
    public static String upc = "041333244019";
    public static int shipping = 0;
    public static String description = "Adventure Books" + TestUtils.getRandomValue();
    public static String manufacturer = "Disney";
    public static String model = "MN2400B4Z";
    public static String url = "htt://www.bestbuy.com/site/Amazon-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";
    public static String image = "htt://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";
    public static int productID;
    @Steps
    ProductsSteps productsSteps;

    @When("^User sends a Get request$")
    public void userSendsAGetRequest() {
        response = productsSteps.getAllProducts();
    }

    @Then("^User should get list of products and must get back a valid status code 200$")
    public void userShouldGetListOfProductsAndMustGetBackAValidStatusCode() {
        response.statusCode(200).log().all();
    }

    @When("^User creates new product record with all information$")
    public void userCreatesNewProductRecordWithAllInformation() {
        productsSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all().statusCode(201);

    }

    @Then("^I verify that new product is created and must get valid status code 201$")
    public void iVerifyThatNewProductIsCreatedAndMustGetValidStatusCode() {
        HashMap<String, Object> product = productsSteps.verifyProductInformation(name);
        productID = (int) product.get("id");
        Assert.assertThat(product, hasValue(name));
    }

    @When("^User can update new record with all product details$")
    public void userCanUpdateNewRecordWithAllProductDetails() {
        name = name + TestUtils.getRandomValue();

        productsSteps.updateProduct(productID, name, type, price, upc, shipping, description, manufacturer, model, url, image).log().all();

    }

    @Then("^I verify that product is updated$")
    public void iVerifyThatProductIsUpdated() {
        HashMap<String, Object> product = productsSteps.verifyProductInformation(name);
        productID = (int) product.get("id");
        Assert.assertThat(product, hasValue(name));

    }

    @When("^User can delete new record$")
    public void userCanDeleteNewRecord() {
        productsSteps.deleteProduct(productID).log().all().statusCode(200);
    }

    @Then("^I verify product is deleted and must get valid staus code 404$")
    public void iVerifyProductIsDeletedAndMustGetValidStausCode() {
        productsSteps.verifyProductDeleted(productID).log().all().statusCode(404);

    }


}
