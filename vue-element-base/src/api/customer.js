import request from '@/utils/request'  

export function addCustomer(customer) {
    return request({
      url: '/customer/add',
      method: 'post',
      data: customer
    })
  }
  
export function getAllCustomers() {
  return request({
    url: '/customer/all',
    method: 'get'
  })
}


export function delCustm(id) {
  return request({
    url: '/customer/delById',
    method: 'get',
    params:{
      id:id
    }
  })
}  


export function getCustmById(id) {
  return request({
    url: '/customer/searchById',
    method: 'get',
    params:{
      id:id
    }
  })
}   

export function getCustmNum() {
  return request({
    url: '/customer/searchById',
    method: 'get',
  })
}  