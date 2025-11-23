import axios from 'axios';

const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  withCredentials: true,
});

// Интерсептор для добавления токена в заголовки
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Интерсептор для обработки ответов (опционально)
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    // Обработка ошибок, например, при истечении срока действия токена
    if (error.response?.status === 401) {
      // Удаляем невалидный токен
      localStorage.removeItem('token');
      // Можно также сбросить состояние в Vuex
      window.location.href = '/login'; // Перенаправление на страницу входа
    }
    return Promise.reject(error);
  }
);

export const API = api;
