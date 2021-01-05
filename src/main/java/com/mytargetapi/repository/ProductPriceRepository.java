package com.mytargetapi.repository;

import com.mytargetapi.model.repository.ProductPrice;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, String> {
    @Query("SELECT productID,currency,price FROM productprices WHERE productID=?0")
    public ProductPrice getProductPrice(Long productID);

    @Query("UPDATE productprices SET price=?1 WHERE productID=?0 IF EXISTS")
    public boolean updateProductPrice(Long productID, Float price);

}
