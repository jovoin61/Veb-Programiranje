import { createRouter, createWebHistory } from "vue-router";

import Prijava from './components/Prijava.vue';
import Restoran from './components/Restoran.vue';
import Registracija from './components/Registracija.vue';

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes : [
        {path: '/', component: Restoran},
        {path: '/prijava', component: Prijava},
        {path: '/registracija', component: Registracija}
    ]
})

export default router;