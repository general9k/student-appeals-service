import actions from "@/store/auth/actions.js";
import state from "@/store/auth/state.js";
import mutations from "@/store/auth/mutations.js";
import getters from "@/store/auth/getters.js";

export default {
  namespaced: true,
  actions,
  getters,
  mutations,
  state,
}
