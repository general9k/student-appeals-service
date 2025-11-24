import {API} from "@/utils/Api.js";

export default {
  async getTopics({commit}) {
    const {data: {topics}} = await API.get('/api/v1/topics')
    commit('CHANGE_DATA_BY_KEY', {topics})
  },

  async createTopic({state, dispatch, commit}) {
    try {
      await API.post('/api/v1/topic', state.form)

      dispatch('getTopics')
      commit('CHANGE_DATA_BY_KEY', {modalView: false})
      dispatch('clearForm')
    } catch (e) {
      console.error(e);
    }
  },

  async editTopic({state, dispatch, commit}) {
    try {
      await API.put(`/api/v1/topic/${state.form.id}`, state.form)

      dispatch('getTopics')
      commit('CHANGE_DATA_BY_KEY', {modalView: false})
      dispatch('clearForm')
    } catch (e) {
      console.error(e);
    }
  },

  async deleteTopic({state, dispatch, commit}) {
    try {
      await API.delete(`/api/v1/topic/${state.form.id}`)

      dispatch('getTopics')
      commit('CHANGE_DATA_BY_KEY', {deleteView: false})
      dispatch('clearForm')
    } catch (e) {
      console.error(e);
    }
  },

  clearForm({commit}) {
    commit('SET_FORM', {
      id: null,
      name: null,
    })
  }
}
