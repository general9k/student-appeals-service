<template>
    <component :is="layout">
      <router-view />
    </component>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex';
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
  mounted() {
  },
  methods: {
    ...mapActions('auth', ['getUser'])
  }
};
</script>
