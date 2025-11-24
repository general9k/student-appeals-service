import { createStore } from 'vuex';

import state from './state';
import actions from './actions';
import mutations from './mutations';

import auth from './auth';
import notes from "@/store/notes/index.js";
import topics from "@/store/topics/index.js";
import getters from "@/store/getters.js";

export default createStore({
  state,
  actions,
  getters,
  mutations,
  modules: {
    auth,
    notes,
    topics
  },
});
