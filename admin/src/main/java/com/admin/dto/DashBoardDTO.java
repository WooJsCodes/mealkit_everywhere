package com.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashBoardDTO {

    private String nowYear;
    private String nowMonth;
    private String nowDay;

    private int totalPrice;
    private int totalShip;
    private int totalItemCnt;
    private double confirmation;

    private String sellIncrease;
    private String itemCntIncrease;
    private String shipIncrease;
    private String confirmationIncrease;
}