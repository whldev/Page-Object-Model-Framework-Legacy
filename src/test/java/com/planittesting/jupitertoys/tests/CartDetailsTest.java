package com.planittesting.jupitertoys.tests;

import com.planittesting.jupitertoys.model.data.CartDetails;
import com.planittesting.jupitertoys.model.data.ItemDetails;
import com.planittesting.jupitertoys.support.ConfigFileReader;
import org.testng.annotations.Test;
import com.planittesting.jupitertoys.model.pages.CartPage;
import com.planittesting.jupitertoys.model.pages.HomePage;
import com.planittesting.jupitertoys.model.popup.LoginPopup;
import com.planittesting.jupitertoys.model.pages.ShopPage;

import java.util.ArrayList;
import java.util.List;

public class CartDetailsTest extends BaseTest {

    @Test
    public void buyProducts() {
        ConfigFileReader configFileReader = new ConfigFileReader();
        HomePage homePage = new HomePage(driver);
        LoginPopup loginPopup = homePage.navigateToLoginPage();
        loginPopup.login(configFileReader.getUsername(), configFileReader.getPassword());

        ShopPage shopPage = homePage.navigateToShopPage().waitUntilImagesDisplayed();
        List<ItemDetails> items = new ArrayList<>();
        items.add(new ItemDetails().setName("teddy bear").setQuantity("2"));
        items.add(new ItemDetails().setName("stuffed frog").setQuantity("1"));
        CartDetails cartDetails = new CartDetails().setBoughtProducts(items);
        shopPage.buyProduct(cartDetails);
        shopPage.checkCartCount(cartDetails);

        CartPage cartPage = shopPage.navigateToCartPage();
        cartPage.checkCart(cartDetails);
    }
}
