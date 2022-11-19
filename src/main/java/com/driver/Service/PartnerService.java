package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Repository.OrderRepo;
import com.driver.Repository.PartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerService {
    @Autowired
    PartnerRepo partnerRepo;
    @Autowired
    OrderRepo orderRepo;

    public void addPartner(String partnerId) {
        System.out.println("Partner created");
        DeliveryPartner deliveryPartner = new DeliveryPartner(partnerId);
        partnerRepo.allParnters.put(partnerId,deliveryPartner);
        System.out.println("All Partners: "+partnerRepo.allParnters);
    }

    public void pair(String partnerId, String orderId) {
        List<String> list = partnerRepo.partnerOrderPair.get(partnerId);
        if(list == null)
            list = new ArrayList<>();
        list.add(orderId);
        partnerRepo.partnerOrderPair.put(partnerId,list);
        DeliveryPartner deliveryPartner = partnerRepo.allParnters.get(partnerId);
        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders() + 1);
        System.out.println("All partners: " +partnerRepo.allParnters);
        System.out.println("Partner Order Pairs: " +partnerRepo.partnerOrderPair);
    }

    public DeliveryPartner getPartner(String partnerId) {
        return partnerRepo.allParnters.get(partnerId);
    }

    public Integer getOrderCount(String partnerId) {
        int count = partnerRepo.partnerOrderPair.get(partnerId).size();
        System.out.println("All Partner orders count: " +count);
        return count;
    }

    public List<String> getAllOrders(String partnerId) {
        return partnerRepo.partnerOrderPair.get(partnerId);
    }

    public String getLastDeliveryTime(String partnerId) {
        String s = partnerRepo.partnerOrderPair.get(partnerId).get(partnerRepo.partnerOrderPair.get(partnerId).size() - 1);
        int time = orderRepo.allOrders.get(s).getDeliveryTime();
        System.out.println("Last delivery Time: " +time);
        return String.valueOf(time);
    }

    public void deletePartner(String partnerId) {
        DeliveryPartner deliveryPartner = partnerRepo.allParnters.get(partnerId);
        partnerRepo.allParnters.remove(partnerId);
        List<String> orders = partnerRepo.partnerOrderPair.get(partnerId);
        System.out.println("DeletePartner: All orders +" +orders);
        for (String s: orders){
            orderRepo.orderPartnerPair.remove(s);
        }
        partnerRepo.allParnters.remove(partnerId);
        partnerRepo.partnerOrderPair.remove(partnerId);
    }
}
