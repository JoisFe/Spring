package hello.spring_basic.order;

public class Order {
    private Long memberId; // 회원 아이디
    private String itemName; // 물건 이름
    private int itemPrice; // 물건 가격
    private int discountPrice; // 할인 가격

    public Order(Long memberId, String itemName, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice; //Order 위해 필요한 정보들
    }

    public int calculatePrice() {
        return itemPrice - discountPrice; //정액 할인 정책에 맞게끔 할인 적용
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
    /*
    toString함수 특징은 객체 자체를 출력하면 toString()이 호출이 됨.

    ex)
    order라는 객체가 있다면
    System.out.println(order); -> order 내의 toString()함수 호출
     */
}

