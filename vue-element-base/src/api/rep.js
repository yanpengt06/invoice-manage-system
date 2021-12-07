import request from '@/utils/request'  

export function getRepItemsByRepId(id) {
    return request({
      url: '/repositoryItem/searchByRepositoryId',
      method: 'get',
      params: {repositoryId:id}
    })
  }

  export function modifyStock(repId,goodId,num) {
    return request({
      url: '/repositoryItem/mdyStock',
      method: 'get',
      params: {
        repId:repId,
        goodId:goodId,
        num :num
      }
    })
  }