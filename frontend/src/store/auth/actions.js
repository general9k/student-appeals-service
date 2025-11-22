import {api} from "@/utils/Api.js";

export default {
  async auth() {
    const {data: {token}} = await api.post('/api/v1/login', {
      "username": "admin",
      "password": "admin"
    })

    localStorage.setItem('authKey', token)
  }
}
