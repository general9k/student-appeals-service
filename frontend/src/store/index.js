import { createStore } from 'vuex';

import state from './state';
import actions from './actions';
import mutations from './mutations';

import auth from './auth';
import notes from "@/store/notes/index.js";

export default createStore({
  state,
  actions,
  mutations,
  modules: {
    auth,
    notes
  },
});
