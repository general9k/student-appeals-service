export default {
  CHANGE_DATA_BY_KEY(state, payload) {
    Object.keys(payload).forEach((key) => {
      state[key] = payload[key]
    })
  }
}
