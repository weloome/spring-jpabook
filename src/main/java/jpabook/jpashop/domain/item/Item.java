package jpabook.jpashop.domain.item;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item { // abstract: 추상 클래스로 보통 상속 목적으로 사용
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int sockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //== 비즈니스 로직 ==//
    /*
      도메인 주도 설계
      엔티티 자체가 해결할 수 있는 것들은 엔티티 안에 비즈니스 로직을 넣는게 좋다. = 객체 지향!
     */
    public void addStock(int quantity) {
        this.sockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.sockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.sockQuantity -= quantity;
    }
}
