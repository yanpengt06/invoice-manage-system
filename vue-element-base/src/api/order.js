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

export function getAllOrderToCheck() {
  return request({
    url: '/order/all',                //  测试用，实际上/order/searchByState
    method: 'get',
    params: {state:"待审核"}
  })
}

export function passOrder(id) {
  return request({
    url: '/order/updateState',                
    method: 'post',
    params: {order_id:id,
      newState:"待支付"
    }
  })
}

export function refuseOrder(id) {
  return request({
    url: '/order/updateState',                
    method: 'post',
    params: {order_id:id,
      newState:"已保存"
    }
  })
}

export function getOrderById(id) {
  return request({
    url: '/order/searchById',                
    method: 'get',
    params:{id}
    
  })
}

export function orderCheckout(id) {
  return request({
    url: '/order/checkout',                
    method: 'get',
    params:{id}   
  })
}

export function getAllOrderToCheckout() {
  return request({
    url: '/order/all',                //  测试用，实际上/order/searchByState
    method: 'get',
    params: {state:"待支付"}
  })
}

export function cash(orderItems) {
  return request({
    url: '/order/cash',                
    method: 'post',
    data:orderItems
  })
}