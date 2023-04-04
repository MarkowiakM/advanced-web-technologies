import { createStore } from 'vuex';

export type User = {
  avatar: string;
  name: string;
  mail: string;
};

const MOCK_USER: User = {
  avatar: 'https://randomuser.me/api/portraits/women/11.jpg',
  name: 'Maria Paulina',
  mail: 'maria.paulina@gmail.com'
};

export default createStore({
  state() {
    return {
      layout: 'app-layout',
      user: MOCK_USER
    };
  },
  mutations: {
    setLayout(state, payload) {
      state.layout = payload;
    }
  },
  getters: {
    layout(state) {
      return state.layout;
    },
    user(state) {
      return state.user;
    }
  }
});
