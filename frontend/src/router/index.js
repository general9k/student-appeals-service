import { createRouter, createWebHistory } from 'vue-router'
import NotesPage from "@/modules/notes/pages/NotesPage.vue";
import TopicsPage from "@/modules/topics/pages/TopicsPage.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'notes',
      component: NotesPage,
    },
    {
      path: '/topics',
      name: 'topics',
      component: TopicsPage,
    },
  ],
})

export default router
