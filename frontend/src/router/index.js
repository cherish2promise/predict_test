import { createRouter, createWebHashHistory } from 'vue-router';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      component: () => import('../components/pages/Index.vue'),
    },
    {
      path: '/deathPredictions',
      component: () => import('../components/ui/DeathPredictionGrid.vue'),
    },
  ],
})

export default router;
