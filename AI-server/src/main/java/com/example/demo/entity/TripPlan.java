package com.example.demo.entity;

import lombok.Data;

@Data
public class TripPlan {
    private Long trip_id;
    private Long uid;
    private String time;
    private String content;
}
