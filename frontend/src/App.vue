<template>
  <component :is="templates[getLayout]">
    <router-view />
  </component>
</template>

<script setup>
import { computed, onMounted, shallowRef } from 'vue';
import { useStore } from 'vuex';
import MainLayout from './layout/MainLayout.vue';

const store = useStore();

const templates = shallowRef({
  MainLayout,
});

const getLayout = computed(() => {
  return 'MainLayout';
});

onMounted(() => {
  // Вызов action auth из модуля auth
  store.dispatch('auth/auth');
});
</script>
