package com.driver.Repository;

import com.driver.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepo {

    public Map<String, Order> allOrders = new HashMap<>();
    public Map<String,String> orderPartnerPair = new HashMap<>();

}
