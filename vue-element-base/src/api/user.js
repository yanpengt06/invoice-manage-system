import request from '@/utils/request'
import { removeToken } from '@/utils/auth'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  removeToken()
  return this.$router.push({path:'/login'});
}

export function mdfPwd(username,pwd,newPwd) {
  return request({
    url: '/user/mdfPwd',
    method: 'get',
    params: { 
      username:username,
      pwd:pwd,
      newPwd:newPwd
     }
  })
}