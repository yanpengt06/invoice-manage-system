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