package com.driver.Repository;

import com.driver.DeliveryPartner;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PartnerRepo {
    public Map<String, DeliveryPartner> allParnters = new HashMap<>();
    public Map<String, List<String>> partnerOrderPair = new HashMap<>();
}
