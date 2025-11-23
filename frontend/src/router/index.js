import { createRouter, createWebHistory } from 'vue-router'
import NotesPage from "@/modules/notes/pages/NotesPage.vue";
import TopicsPage from "@/modules/topics/pages/TopicsPage.vue";
import AuthLayout from "@/layout/AuthLayout.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', redirect: { path: '/auth' } },
    { path: '/auth', name: 'auth', component: AuthLayout },
    { path: '/notes', name: 'notes', component: NotesPage },
    { path: '/topics', name: 'topics', component: TopicsPage },
  ],
})

router.beforeEach((to, from, next) => {
  // проверяем, активен ли пользователь
  const isActive = !!localStorage.getItem('token');

  // если пользователь активен, отправляем на host
  if (!isActive && !['auth'].includes(to.name)) {
    next({ name: 'auth' });
    return;
  }

  // если пользователь активен, отправляем на host
  if (isActive && to.name === 'auth') {
    next({ name: 'notes' });
    return;
  }

  next();
});
export default router;
