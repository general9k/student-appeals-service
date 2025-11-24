import {API} from "@/utils/Api.js";
import router from "@/router/index.js";

export default {

  async login({ commit }, credentials) {
    commit('SET_LOADING', true)
    commit('CLEAR_ERROR')

    try {
      const { data } = await API.post('/api/v1/login', credentials)

      if (!data) {
        throw new Error('Ошибка авторизации')
      }

      if (data.token) {
        commit('SET_TOKEN', data.token)
        commit('SET_USER', credentials)
        await router.push('/notes')
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

  async getUser() {
    const {data: user} = await API.get('/api/v1/current_user')
    console.log(user)
  },

  logout({ commit }) {
    commit('LOGOUT');
  },

  clearError({ commit }) {
    commit('CLEAR_ERROR')
  }
}
