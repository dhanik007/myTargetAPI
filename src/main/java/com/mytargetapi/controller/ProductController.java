package com.mytargetapi.controller;

import com.mytargetapi.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mytargetapi.model.business.Product;
import com.mytargetapi.service.ProductService;

/**
 * This class contains RESTFUL services to get product details(i.e., name and
 * price of the product) and also to update the price of the product.
 */
@RestController
@RequestMapping("/")

public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "products/{productID:[\\d]+}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable("productID") Long productID) throws Exception {
        if (productID == null || productID < 0) {
            throw new IllegalArgumentException("productID parameter cannot be null or less than 0");
        }
        Product product = productService.getProductInfo(productID);
        if (product.getProductName() == null || product.getProductID() == null)
            throw new ProductNotFoundException("ProductID you are searching is not found");
        return product;

    }

    @RequestMapping(value = "products/{productID}", method = RequestMethod.PUT)
    public String updateProductPriceInfo(@PathVariable("productID") Long productID, @RequestBody Product product)
            throws Exception {
        if (productID == null || productID < 0) {
            throw new IllegalArgumentException("productID parameter cannot be null or less than 0");

        }
        boolean updated = productService.updateProductPriceInfo(productID, product.getPriceInfo().getPrice());
        if (updated)
            return "Given product with id" + productID + " is successfully updated";
        else
            return productID + "is not updated";
    }
}
