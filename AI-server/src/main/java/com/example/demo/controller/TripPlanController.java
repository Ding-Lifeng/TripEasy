package com.example.demo.controller;

import com.example.demo.common.CommonResult;
import com.example.demo.entity.TripPlan;
import com.example.demo.service.TripPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.util.JwtTokenUtil;

import java.util.List;

@RestController
@RequestMapping("/tripPlan")
public class TripPlanController {
    @Autowired
    private TripPlanService tripPlanService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/add")
    public CommonResult<?> addTripPlan(@RequestHeader("Authorization") String token, @RequestBody TripPlan tripPlan){
        long uid = Long.parseLong(jwtTokenUtil.getUidFromToken(token.split(" ")[1]));
        tripPlan.setUid(uid);
        tripPlanService.addTripPlan(tripPlan);
        String data = "旅行规划保存成功!";
        return CommonResult.success(data);
    }

    @GetMapping("/getInfo")
    public CommonResult<?> getTripInfo(@RequestHeader("Authorization") String token) {
        long uid = Long.parseLong(jwtTokenUtil.getUidFromToken(token.split(" ")[1]));
        List<TripPlan> data= tripPlanService.getTripInfo(uid);
        return CommonResult.success(data);
    }

    @PostMapping("/deletePlan")
    public CommonResult<?> deleteTripPlan(@RequestHeader("Authorization") String token, @RequestBody TripPlan tripPlan) {
        long uid = Long.parseLong(jwtTokenUtil.getUidFromToken(token.split(" ")[1]));
        tripPlan.setUid(uid);
        tripPlanService.deleteTripPlan(tripPlan);
        String data = "旅行规划删除成功!";
        return CommonResult.success(data);
    }

    @PostMapping("/updatePlan")
    public CommonResult<?> updateTripPlan(@RequestHeader("Authorization") String token, @RequestBody TripPlan tripPlan) {
        long uid = Long.parseLong(jwtTokenUtil.getUidFromToken(token.split(" ")[1]));
        tripPlan.setUid(uid);
        tripPlanService.updateTripPlan(tripPlan);
        String data = "旅行规划修改成功!";
        return CommonResult.success(data);
    }
}
