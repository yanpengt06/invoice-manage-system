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
  export function getGoodsByRepId(repId) {
    return request({
      url: '/repositoryItem/getGoodsByRepId',
      method: 'get',
      params: {
        repId:repId
      }
    })
  }
  export function addRep(rep) {
    return request({
      url: '/repository/add',
      method: 'post',
      data: rep
    })
  }

  export function getAllReps() {
    return request({
      url: '/repository/all',
      method: 'get',
    })
  }