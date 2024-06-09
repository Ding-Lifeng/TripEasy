import request from '@/utils/request';

// 获取当前时间，精确到分钟，并转换为中国时间
function getChinaTime() {
    const now = new Date();
    now.setHours(now.getHours());
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}`;
}

// 提交旅行规划内容
export function dealWithTripContent(planContent) {
    const data = {
        time: getChinaTime(), // 获取当前时间，精确到分钟
        content: planContent
    };
    return request({
        url: '/tripPlan/add',
        method: 'post',
        data: data
    });
}

export function getTripContents() {

    return request({
        url: '/tripPlan/getInfo',
        method: 'get',
    })
}

export function deleteTripContent(tripId) {
    const data = {
        trip_id: tripId
    };
    return request({
        url: `/tripPlan/deletePlan`,
        method: 'post',
        data: data
    });
}

export function updateTripContent(plan) {
    const data = {
        trip_id: plan.trip_id,
        time: plan.time,
        content: plan.content
    };
    return request({
        url: '/tripPlan/updatePlan',
        method: 'post',
        data: data
    });
}