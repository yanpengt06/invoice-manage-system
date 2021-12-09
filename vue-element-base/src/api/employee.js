import request from '@/utils/request'  

export function setPos(eply) {
    return request({
      url: '/employee/update',
      method: 'post',
      data : eply
    })
}

export function getAllEplys() {
  return request({
    url: '/employee/all',
    method: 'get',
  })
}

export function addEply(eply) {
  return request({
    url: '/employee/add',
    method: 'post',
    data: eply
  })
}