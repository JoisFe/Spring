package hello.spring_basic.order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice); // createOrder 선언
}