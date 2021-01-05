package com.mytargetapi.controller;

import com.mytargetapi.model.business.PriceInfo;
import com.mytargetapi.model.business.Product;
import com.mytargetapi.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;

/**
 * This class provides unit test case for ProductController. It tests
 * getProductDetails and update API method.
 *
 * @author Dhanik Dharmappa
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    PriceInfo priceInfo = new PriceInfo("USD", (float) 99.9);
    Long productID = (long) 13860428;
    String expected = "{\"productID\":13860428,\"productName\":\"The Big Lebowski (Blu-ray)\",\"priceInfo\":{\"currency\":\"USD\",\"price\":99.9}}";
    Product mockProduct = new Product(productID, "The Big Lebowski (Blu-ray)", priceInfo);

    /**
     * This method tests getProduct REST API call. It performs a Mock HTTP

     *
     * @throws Exception
     */
    @Test
    public void getProductDetails() throws Exception {

        Mockito.when(productService.getProductInfo(Mockito.anyLong())).thenReturn(mockProduct);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/products/" + productID)
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        System.out.println(result.getResponse());

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), true);
        System.out.println("UnitTestSuccessful");
    }

    /**
     * This method tests updateProductPriceInfo RESTAPI call. It performs a Mock
     * request and evaluates the response against HTTPStatus value.
     *
     * @throws Exception
     */
    @Test
    public void update() throws Exception {
        Mockito.when(productService.updateProductPriceInfo(Mockito.anyLong(), Mockito.anyFloat())).thenReturn(true);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/products/13860428")
                .accept(MediaType.APPLICATION_JSON).content(expected).contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

    }

}
