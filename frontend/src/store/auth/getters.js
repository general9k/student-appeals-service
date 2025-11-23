export default {
  isAuthenticated: state => !!state.token,
  token: state => state.token,
  user: state => state.user,
  loading: state => state.loading,
  error: state => state.error
}
