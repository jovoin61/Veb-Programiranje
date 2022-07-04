import { createApp } from 'vue'
import App from './App.vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import router from './router.js'
import './axios'

const app = createApp(App);
app.use(router);
app.mount('#app');

