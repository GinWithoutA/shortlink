import { createStore } from 'vuex'

// 创建一个新的 store 实例
const store = createStore({
  state() {
    return {
      // domain: 'nurl.ink'
      domain: 'tiny.intbeast.top'
    }
  }
})

export default store
