export default {
  CHANGE_DATA_BY_KEY(state, payload) {
    Object.keys(payload).forEach((key) => {
      state[key] = payload[key]
    })
  },

  ADD_COMMENT(state, data) {
    state.comments.push(data)
  },

  SET_FORM(state, payload) {
    Object.keys(state.form).forEach((key) => {
      state.form[key] = payload[key]
    })
  },
  CLEAR_FILTER(state) {
    Object.keys(state.filter).forEach((key) => {
      state.filter[key] = null
    })
  },
}
