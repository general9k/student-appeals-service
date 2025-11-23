import {API} from "@/utils/Api.js";

export default {
  async getStatuses({commit}) {
    const {data: {statuses}} = await API.get('/api/v1/statuses')
    commit('CHANGE_DATA_BY_KEY', {statuses})
  },

  async getTopics({commit}) {
    const {data: {topics}} = await API.get('/api/v1/topics')
    commit('CHANGE_DATA_BY_KEY', {topics})
  }
}
