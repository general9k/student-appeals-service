import {api} from "@/utils/Api.js";
import {ro} from "vuetify/locale";
import router from "@/router/index.js";

export default {
  // async auth() {
  //   const {data: {token}} = await api.post('/api/v1/login', {
  //     "username": "admin",
  //     "password": "admin"
  //   })
  //
  //   localStorage.setItem('authKey', token)
  // }

  async login({ commit }, credentials) {
    commit('SET_LOADING', true)
    commit('CLEAR_ERROR')

    try {
      // Замените на ваш реальный API endpoint
      // const response = await fetch('api/v1/login', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify(credentials)
      // })
      console.log(credentials)
      const { data } = await api.post('/api/v1/login', credentials)

      if (!data) {
        throw new Error('Ошибка авторизации')
      }

      // const data = await response.json()

      if (data.token) {
        commit('SET_TOKEN', data.token)
        // Если API возвращает информацию о пользователе
        // if (data.user) {
        //   commit('SET_USER', data.user)
        // }
        router.push('/notes')
        return { success: true }
      } else {
        throw new Error('Токен не получен')
      }
    } catch (error) {
      commit('SET_ERROR', error.message)
      return { success: false, error: error.message }
    } finally {
      commit('SET_LOADING', false)
    }
  },

  logout({ commit }) {
    commit('LOGOUT');
    // window.location.reload();
  },

  clearError({ commit }) {
    commit('CLEAR_ERROR')
  }
}
