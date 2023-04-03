import { createStore } from 'vuex';

export default createStore({
  state() {
    return {
      layout: 'app-layout'
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
    }
  }
});
