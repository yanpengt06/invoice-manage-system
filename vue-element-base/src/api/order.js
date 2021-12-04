import request from '@/utils/request'

export function getAll() {
  return request({
    url: '/order/all',
    method: 'get',
  })
}

export function getOrderDetail(orderId) {
    return request({
      url: '/orderItem/searchByOrderId',
      method: 'get',
      params: {orderId}
    })
}

export function makeOrder(orderItems,id) {
  return request({
    url: '/order/makeOrder',
    method: 'post',
    data: {
      orderItems:orderItems,
      id:id
    }
  })
}

export function saveOrder(orderItems,id) {
  return request({
    url: '/order/save',
    method: 'post',
    data: {
      orderItems:orderItems,
      id:id
    }
  })
}

export function returnOrder(id) {
  return request({
    url: '/order/delById',
    method: 'get',
    params: {id:id} 
  })
}

