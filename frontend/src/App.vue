<template>
    <component :is="layout">
      <router-view />
    </component>
</template>

<script>
import {mapGetters} from 'vuex';
import MainLayout from './layout/MainLayout.vue';
import AuthLayout from '@/layout/AuthLayout.vue';

export default {
  name: 'App',
  components: {
    MainLayout, AuthLayout,
  },
  computed: {
    ...mapGetters('auth', ['isAuthenticated']),
    layout() {
      if (this.isAuthenticated) {
        return 'MainLayout'
      }
      return 'AuthLayout'
    }
  },
};
</script>
