import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/auth/oauth/token',
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data
  })
}

export function getInfo() {
  return request({
    url: '/admin/user/currentUser',
    method: 'GET',
  })
}

export function logout() {
  return request({
    url: '/admin/user/logout',
    method: 'POST'
  })
}
