package com.driver.Service;

import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.Repository.OrderRepo;
import com.driver.Repository.PartnerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    PartnerRepo partnerRepo;

    public void addOrder(Order order){
        String id = order.getId();

        int time = order.getDeliveryTime();
        System.out.println("time is + " +time);
        orderRepo.allOrders.put(id,order);
        System.out.println("All Orders: " +orderRepo.allOrders);
    }

    public void pair(String orderId, String partnerId) {
        orderRepo.orderPartnerPair.put(orderId,partnerId);
        System.out.println("All orders: " + orderRepo.allOrders);
        System.out.println("Order Partner Pairs: "+orderRepo.orderPartnerPair);
    }

    public Order getOrder(String orderId) {
        System.out.println("get Order: " + orderRepo.allOrders.get(orderId));

        return orderRepo.allOrders.get(orderId);
    }

    public List<String> getAllOrders() {
        List<String> list = new ArrayList<>(orderRepo.allOrders.keySet());
        System.out.println("all Orders List: "+list);
        return list;
    }

    public Integer getUnassignedOrdersCount() {
        int count = 0;
        for (String s: orderRepo.allOrders.keySet()){
            if(!orderRepo.orderPartnerPair.containsKey(s))
                count++;
        }
        System.out.println("Unassigned Count: " +count);
        return count;
    }

    public void removeOrder(String orderId) {
        //Delete an order and also
        // remove it from the assigned order of that partnerId
        String partnerId = orderRepo.orderPartnerPair.get(orderId);
        List<String> list = partnerRepo.partnerOrderPair.get(partnerId);
        List<String> newLIst = new ArrayList<>();
        if(list != null){
            for (String s : list){
                if (!s.equals(orderId)){
                    newLIst.add(s);
                }
            }
        }
        DeliveryPartner deliveryPartner = partnerRepo.allParnters.get(partnerId);
        deliveryPartner.setNumberOfOrders(deliveryPartner.getNumberOfOrders() - 1);
        partnerRepo.allParnters.put(partnerId, deliveryPartner);
        orderRepo.allOrders.remove(orderId);
        orderRepo.orderPartnerPair.remove(orderId);
        partnerRepo.partnerOrderPair.put(partnerId,newLIst);
    }
}
