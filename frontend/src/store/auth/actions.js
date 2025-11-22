import {api} from "@/utils/Api.js";

export default {
  async auth() {
    const response = await api.post('/api/v1/login', {
      "username": "admin",
      "password": "admin"
    })

    console.log(response)
  }
}
