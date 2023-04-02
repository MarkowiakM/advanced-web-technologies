import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { loadFonts } from './plugins/webfontloader';
import '@mdi/font/css/materialdesignicons.css';
import 'vuetify/styles';
import { createVuetify } from 'vuetify';
import * as components from 'vuetify/components';
import * as directives from 'vuetify/directives';

const vuetify = createVuetify({
  components,
  directives
});

loadFonts();

createApp(App).use(router).use(vuetify).mount('#app');
