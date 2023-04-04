import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { loadFonts } from './plugins/webfontloader';
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';
import store from './store';

const vuetify = createVuetify({
  components,
  directives
});

loadFonts();
const app = createApp(App);
app.config.globalProperties.$store = store;
app.use(router).provide('$store', store).use(store).use(vuetify).mount('#app');
