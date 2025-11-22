// import './assets/main.css';
import { VueMaskDirective } from 'v-mask';

import { createApp, markRaw } from 'vue';

// Vuetify
import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import '@mdi/font/css/materialdesignicons.css';
import { VDateInput } from 'vuetify/labs/components';
import router from './router';
import App from './App.vue';
import store from "@/store/index.js";

// import mixins from '@/utils/mixins.js';

const app = createApp(App);

const VueMask = {
  beforeMount: VueMaskDirective.bind,
  updated: VueMaskDirective.componentUpdated,
  unmounted: VueMaskDirective.unbind,
};

app.directive('mask', VueMask);


app.use(store);
// app.mixin({
//   methods: { ...mixins() },
// });

const vuetify = createVuetify({
  components: {
    ...components,
    VDateInput,
  },
  directives,
});

app.use(vuetify);
app.use(router);


app.mount('#app');
