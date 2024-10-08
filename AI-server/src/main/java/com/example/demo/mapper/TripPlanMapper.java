package com.example.demo.mapper;

import com.example.demo.entity.TripPlan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TripPlanMapper {
    void insertTripPlan(TripPlan tripPlan);

    void deleteTripPlan(TripPlan tripPlan);

    void updateTripPlan(TripPlan tripPlan);

    List<TripPlan> getTripInfo(Long uid);
}
