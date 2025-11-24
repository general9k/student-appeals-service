<template>
  <div class="login-container">
    <div class="login-form">
      <h2>Авторизация</h2>

      <form @submit.prevent="handleLogin">
        <div class="form-group">
          <label for="username">Логин:</label>
          <input
              id="username"
              v-model="username"
              type="text"
              :class="{ 'error': fieldErrors.username }"
              @input="clearFieldError('username')"
              required
          />
          <span v-if="fieldErrors.username" class="error-message">{{ fieldErrors.username }}</span>
        </div>

        <div class="form-group">
          <label for="password">Пароль:</label>
          <input
              id="password"
              v-model="password"
              type="password"
              :class="{ 'error': fieldErrors.password }"
              @input="clearFieldError('password')"
              required
          />
          <span v-if="fieldErrors.password" class="error-message">{{ fieldErrors.password }}</span>
        </div>

        <div v-if="error" class="error-message server-error">
          {{ 'Ошибка авторизации' }}
        </div>

        <button
            type="submit"
            :disabled="loading"
            class="login-button"
        >
          <span v-if="loading">Вход...</span>
          <span v-else>Войти</span>
        </button>
      </form>

      <div v-if="isAuthenticated" class="success-message">
        Вы успешно авторизованы!
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapGetters } from 'vuex'

export default {
  name: 'AuthLayout',
  data() {
    return {
      username: '',
      password: '',
      fieldErrors: {
        username: '',
        password: ''
      }
    }
  },
  computed: {
    ...mapState('auth', ['loading', 'error']),
    ...mapGetters('auth', ['isAuthenticated'])
  },
  methods: {
    ...mapActions('auth', ['login', 'clearError']),

    clearFieldError(field) {
      if (this.fieldErrors[field]) {
        this.fieldErrors[field] = ''
      }
      if (this.error) {
        this.clearError()
      }
    },

    async handleLogin() {
      const credentials = {
        username: this.username,
        password: this.password
      }

      const result = await this.login(credentials)

      if (result.success) {
        // Перенаправление или другие действия после успешного входа
        console.log('Токен сохранен:', localStorage.getItem('token'))
        this.$emit('login-success')
      }
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f5f5;
}

.login-form {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  text-align: center;
  margin-bottom: 1.5rem;
  color: #333;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: #555;
  font-weight: 500;
}

input {
  width: 100%;
  padding: 0.75rem;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-size: 1rem;
  box-sizing: border-box;
}

input:focus {
  outline: none;
  border-color: #007bff;
}

input.error {
  border-color: #dc3545;
}

.error-message {
  color: #dc3545;
  font-size: 0.875rem;
  margin-top: 0.25rem;
  display: block;
}

.server-error {
  text-align: center;
  margin: 1rem 0;
  padding: 0.5rem;
  background-color: #f8d7da;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
}

.success-message {
  color: #28a745;
  text-align: center;
  margin-top: 1rem;
  padding: 0.5rem;
  background-color: #d4edda;
  border: 1px solid #c3e6cb;
  border-radius: 4px;
}

.login-button {
  width: 100%;
  padding: 0.75rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 1rem;
  cursor: pointer;
  transition: background-color 0.3s;
}

.login-button:hover:not(:disabled) {
  background-color: #0056b3;
}

.login-button:disabled {
  background-color: #6c757d;
  cursor: not-allowed;
}
</style>
