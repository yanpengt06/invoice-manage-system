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
    url: '/good/searchByName',
    method: 'get',
    params: {name}
  })
}
