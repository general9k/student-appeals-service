import {API} from "@/utils/Api.js";

export default {
  async getNotes({state, commit}) {
    const sort = ['topic', 'status'].includes(state.sort) ? `${state.sort}.name` : state.sort
    const {data: {notes, paginationInfo}} = await API.get('/api/v1/notes', { params: {
      page: state.page,
      size: state.size,
      sort: `${sort},${state.order.toUpperCase()}`,
      }
    })
    commit('CHANGE_DATA_BY_KEY', {notes, ...paginationInfo})
  },

  async createNote({state, dispatch, commit, rootState}) {
    try {
      await API.post('/api/v1/note', {...state.form, statusId: rootState.statuses.sort((a, b) => a.id - b.id)[0].id})

      dispatch('getNotes')
      commit('CHANGE_DATA_BY_KEY', {modalView: false})
      dispatch('clearForm')
    } catch (e) {
      console.error(e);
    }
  },

  async editNote({state, dispatch, commit}) {
    try {
      await API.put(`/api/v1/note/${state.form.id}/status`, {statusId: state.form.statusId})

      dispatch('getNotes')
      commit('CHANGE_DATA_BY_KEY', {modalView: false})
      dispatch('clearForm')
    } catch (e) {
      console.error(e);
    }
  },

  clearForm({commit}) {
    commit('SET_FORM', {
      id: null,
      name: null,
      description: null,
      topicId: null,
      statusId: null,
    })
  }
}
