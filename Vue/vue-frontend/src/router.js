import { createRouter, createWebHistory } from "vue-router";

import Prijava from './components/Prijava.vue';
import Restoran from './components/Restoran.vue';
import Registracija from './components/Registracija.vue';
import KonkretanRestoran from './components/KonkretanRestoran.vue';
import Admin from './components/Admin.vue';
import AdminKorisnici from './components/AdminKorisnici.vue';

const router = createRouter({

    history: createWebHistory(process.env.BASE_URL),

    routes : [
        {path: '/', component: Restoran, 
            children:[
                {
                    path : '/restoran/:id',
                    name : 'restoran-id',
                    component : KonkretanRestoran,
                },
        ]},
        {path: '/prijava', component: Prijava},
        {path: '/registracija', component: Registracija},
        {path: '/admin', component: Admin},
        {path: '/admin/korisnici', component: AdminKorisnici},
    ]
})

export default router;