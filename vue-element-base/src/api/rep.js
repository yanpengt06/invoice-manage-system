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

  export function deploy(repAid,repBid,goodId,num) {
    return request({
      url: '/repositoryItem/deploy',
      method: 'get',
      params: {
        repAid: repAid,
        repBid: repBid,
        goodId: goodId,
        num : num
      }
    })
  }

  export function input(goodId,num,repId) {
    return request({
      url: '/repositoryItem/input',
      method: 'get',
      params: {
        goodId:goodId,
        num:num,
        repId:repId
      }
    })
  }

  
  export function delRep(id) {
    return request({
      url: '/repository/delById',
      method: 'get',
      params: {
        id:id
      }
    })
  }

  export function countReps() {
    return request({
      url: '/repositoryItem/count',
      method: 'get',
    })
  }  