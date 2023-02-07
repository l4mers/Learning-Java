package tools;

import components.*;
import interfaces.CompareCustomerId;
import interfaces.CompareInt;
import interfaces.CompareString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class Search {

    public List<OrderItem> search(interfaces.Search setting, List<OrderItem> shoeList, String word){
        return shoeList.stream().filter(c -> setting.search(c, word)).
                collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(e -> e.order().getCustomer().id()))), ArrayList::new));
    }
    public List<OrderCount> convertOrderListToOrderCountList(List<Order> orders, interfaces.OrderCount orderCount){
        return orders.stream().map(Order::getCustomer).toList()
                .stream().map(e->{
                    int count = orders.stream().mapToInt(ee-> orderCount.countOrders(e, ee)).sum();
                    return new OrderCount(count, e);
                }).collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(e -> e.customer().id()))), ArrayList::new))
                .stream().sorted(Comparator.comparingInt(OrderCount::count).reversed()).toList();
    }
    public List<CustomerValue> convertOrderItemListToCustomerValueList(List<OrderItem> ordersItems, CompareCustomerId maxValue) {
        return ordersItems.stream().map(e->e.order().getCustomer()).toList()
                .stream().map(e->{
                    int value = ordersItems.stream().mapToInt(ee-> maxValue.compare(ee, e)).sum();
                    return new CustomerValue(value, e);
                }).collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(e -> e.customer().id()))), ArrayList::new))
                .stream().sorted(Comparator.comparingInt(CustomerValue::totalValue).reversed()).toList();
    }

    public List<CountyValue> convertOrderItemListToCountyValueList(List<OrderItem> ordersItems, CompareString topList) {
        return ordersItems.stream().map(e->e.order().getCustomer().county()).toList()
                .stream().map(e->{
                    int value = ordersItems.stream().mapToInt(ee ->topList.topCounty(ee, e)).sum();
                    return new CountyValue(value, e);
                }).collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(CountyValue::county))), ArrayList::new))
                .stream().sorted(Comparator.comparingInt(CountyValue::total).reversed()).toList();
    }
    public List<TopShoeList> convertOrderItemListToTopShoeList(List<OrderItem> ordersItems, CompareInt topList) {
        return ordersItems.stream().map(OrderItem::shoe).toList()
                .stream().map(e->{
                    int quantity = ordersItems.stream().mapToInt(ee->topList.topCount(ee, e)).sum();
                    return new TopShoeList(e, quantity);
                }).collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparingInt(c -> c.shoe().id()
                ))), ArrayList::new))
                .stream().sorted(Comparator.comparingInt(TopShoeList::quantity).reversed()).limit(10).toList();
    }
}
