import { createRouter, createWebHistory } from "vue-router";

import Prijava from './components/Prijava.vue';
import Restoran from './components/Restoran.vue';
import Registracija from './components/Registracija.vue';
import KonkretanRestoran from './components/KonkretanRestoran.vue';
import Admin from './components/Admin.vue';
import AdminKorisnici from './components/AdminKorisnici.vue';
import DodavanjeMenadzera from './components/DodavanjeMenadzera.vue';
import DodavanjeDostavljaca from './components/DodavanjeDostavljaca.vue';
import DodavanjeRestorana from './components/DodavanjeRestorana.vue';
import PretragaRestorana from './components/PretragaRestorana.vue';

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
        {path: '/admin/dodavanje/menadzer', component: DodavanjeMenadzera},
        {path: '/admin/dodavanje/dostavljac', component: DodavanjeDostavljaca},
        {path: '/admin/dodavanje/restoran', component: DodavanjeRestorana},
        {path: '/restoran/pretraga', component: PretragaRestorana},
    ]
})

export default router;