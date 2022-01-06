import request from '@/utils/request'  

export function queryGoodById(id) {
    return request({
      url: '/good/searchById',
      method: 'get',
      params: {id}
    })
}

export function searchGoodsByName(name) {
  return request({
    url: '/good/blurSearch',
    method: 'get',
    params: {name}
  })
}

export function add(good) {
  return request({
    url: '/good/add',
    method: 'post',
    data:good
  })
}

export function getGoodStock(repId,goodId) {
  return request({
    url: '/good/getStockById',
    method: 'get',
    params: {repId:repId,
        goodId:goodId
    }
  })
}

export function getAllGoods() {
  return request({
    url: '/good/getAll',
    method: 'get',
  })
}

export function getGoodNum() {
  return request({
    url: '/good/count',
    method: 'get',
  })
}


export function modify(id,retail,wholesale) {
  return request({
    url: '/good/setPrice',
    method: 'get',
    params: {
      id:id,
      retail_price: retail,
      wholesale_price:wholesale
    }
  })
}
