import request from '@/utils/request'

// 用户注册
export function submit(name, password, real_name, gender, phoneNum) {
    const data = {
        name,
        password,
        real_name,
        gender,
        phoneNum
    }
    return request({
        url: '/user/add',
        method: 'post',
        data: data
    })
}