package com.example.demo.service;

import com.example.demo.entity.TripPlan;
import com.example.demo.mapper.TripPlanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TripPlanService {
    @Autowired
    private TripPlanMapper tripPlanMapper;

    @Transactional
    public void addTripPlan(TripPlan tripPlan) {
        tripPlanMapper.insertTripPlan(tripPlan);
    }

    public void deleteTripPlan(TripPlan tripPlan) {
        tripPlanMapper.deleteTripPlan(tripPlan);
    }

    public void updateTripPlan(TripPlan tripPlan) {
        tripPlanMapper.updateTripPlan(tripPlan);
    }

    public List<TripPlan> getTripInfo(Long uid) {
        return tripPlanMapper.getTripInfo(uid);
    }
}
