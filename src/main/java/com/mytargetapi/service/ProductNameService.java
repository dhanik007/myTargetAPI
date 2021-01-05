package com.mytargetapi.service;


import com.mytargetapi.constant.APIConstants;
import com.mytargetapi.exception.ProductNotFoundException;
import com.mytargetapi.model.service.ProductName;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * This class is responsible for constructing the ProductName object by parsing
 * the JSON object returned by Target end point
 */
@Service
@PropertySource("classpath:application.properties")
public class ProductNameService {
    @Value("${restservice.product.baseurl}")
    private String baseUrl;

    @Value("${restservice.product.excludeparams}")
    private String excludeParams;
    private RestTemplate restTemplate = new RestTemplate();

    public ProductName getProductName(Long productID) throws Exception {

        String endPointURL = baseUrl + productID + excludeParams;
        String endpointResponse = null;
        try {
            endpointResponse = restTemplate.getForObject(endPointURL, String.class);

        } catch (HttpClientErrorException e) {
            throw new ProductNotFoundException("No matching product found for the given ProductID");
        }
        if (endpointResponse == null) {
            throw new ProductNotFoundException("No matching product found for the given ProductID");
        }
        JSONObject requestJSONObject = new JSONObject(endpointResponse);
        ProductName productName = parseJSON(requestJSONObject);
        return productName;
    }

    public ProductName parseJSON(JSONObject requestJSONObject) throws JSONException {
        ProductName productName = new ProductName();
        JSONObject product = requestJSONObject.getJSONObject(APIConstants.PRODUCT);
        JSONObject item = product.getJSONObject(APIConstants.ITEM);
        JSONObject productDescription = item.getJSONObject(APIConstants.PRODUCT_DESCRIPTION);
        productName.setProductName(productDescription.getString(APIConstants.TITLE));
        return productName;
    }

}
