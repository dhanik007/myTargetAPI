package com.mytargetapi.service;

import com.mytargetapi.model.business.PriceInfo;
import com.mytargetapi.model.business.Product;
import com.mytargetapi.model.repository.ProductPrice;
import com.mytargetapi.model.service.ProductName;
import com.mytargetapi.repository.ProductPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class acts as a middle layer between back-end service providers and
 * front-end REST API controllers. It constructs productObject for GET API by
 * calling ProductNameService and ProductPriceRepository service. It also
 * updates price of the product for PUT API.
 */
@Service
public class ProductService {
    @Autowired
    private ProductNameService productNameService;
    @Autowired
    private ProductPriceRepository productPriceRepository;

    public Product getProductInfo(Long productID) throws Exception {
        ProductName productName = productNameService.getProductName(productID);
        ProductPrice productPrice = productPriceRepository.getProductPrice(productID);
        return constructProduct(productID, productPrice, productName);
    }

    public boolean updateProductPriceInfo(Long productID, Float price) throws Exception {
        return productPriceRepository.updateProductPrice(productID, price);
    }

    public Product constructProduct(Long productID, ProductPrice productPrice, ProductName productName) {
        Product productObject = new Product();
        productObject.setProductName(productName.getProductName());
        productObject.setProductID(productID);
        PriceInfo priceInfo = new PriceInfo();
        if (productPrice == null) {
            priceInfo.setCurrency(null);
            priceInfo.setPrice(null);
        } else {
            priceInfo.setCurrency(productPrice.getCurrency());
            priceInfo.setPrice(productPrice.getPrice());
        }
        productObject.setPriceInfo(priceInfo);
        return productObject;
    }
}
