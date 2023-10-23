package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 어딘가에 내장될수있다
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
