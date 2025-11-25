import {API} from "@/utils/Api.js";
import router from "@/router/index.js";

export default {

  async login({ commit, dispatch }, credentials) {
    commit('SET_LOADING', true)
    commit('CLEAR_ERROR')

    try {
      const { data } = await API.post('/api/v1/login', credentials)

      if (!data) {
        throw new Error('Ошибка авторизации')
      }

      if (data.token) {
        commit('SET_TOKEN', data.token)
        dispatch('getUser');
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

  async getUser({commit}) {
    let user = null;
    if (!localStorage.getItem('user')) {
      const {data} = await API.get('/api/v1/current_user')
      user = data;
    } else {
      user = JSON.parse(localStorage.getItem('user'))
    }
    console.log(user)
    commit('SET_USER', user)
  },

  logout({ commit }) {
    commit('LOGOUT');
  },

  clearError({ commit }) {
    commit('CLEAR_ERROR')
  }
}
