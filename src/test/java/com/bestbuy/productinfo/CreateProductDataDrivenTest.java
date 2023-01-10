package com.bestbuy.productinfo;

import com.bestbuy.bestbuyinfo.ProductsSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@Concurrent(threads = "8x")
@UseTestDataFrom("src/test/java/resources/testdata/productinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class CreateProductDataDrivenTest extends TestBase {
    private String name;
    private String type;
    private double price;
    private String upc;
    private int shipping;
    private String description;
    private String manufacturer;
    private String model;
    private String url;
    private String image;

    @Steps
    ProductsSteps productsSteps;

    @Title("Data driven test for adding multiple products to the application")
    @Test
    public void createMultipleProducts() {
        productsSteps.createProduct(name, type, price, upc, shipping, description, manufacturer, model, url, image).statusCode(201);

    }

}
