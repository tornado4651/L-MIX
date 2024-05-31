import { login, logout, getInfo } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    username: '',
    nickname: '',
    telephone: '',
    avatar: '',
    birthday: '',
    gender: '',
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_USERNAME: (state, username) => {
    state.username = username
  },
  SET_AGE: (state, birthday) => {
    state.birthday = birthday
  },
  SET_GENDER: (state, gender) =>{
    state.gender = gender===1?'男':'女'
  },
  SET_TEL: (state, telephone) =>{
    state.telephone = telephone
  },
  SET_NICKNAME: (state, nickname) => {
    state.nickname = nickname
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      var loginData= new URLSearchParams({
        "grant_type": "password",
        "client_id": "client-app",
        "client_secret": "123456",
        "username": username,
        "password": password
      })
      login(loginData).then(response => {
        const { data } = response
        const token_str= "Bearer " + data["token"]
        commit('SET_TOKEN', token_str)
        setToken(token_str)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // get login user base info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const { data } = response
        if (!data) {
          return reject('登陆信息验证失败，请重新登陆！')
        }
        const { username, nickname, birthday, gender, telephone, avatar } = data
        commit('SET_USERNAME', username)
        commit('SET_NICKNAME', nickname)
        commit('SET_AGE', birthday)
        commit('SET_GENDER', gender)
        commit('SET_TEL', telephone)
        commit('SET_AVATAR', avatar)
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // remove token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken() // must remove  token  first
      commit('RESET_STATE')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

