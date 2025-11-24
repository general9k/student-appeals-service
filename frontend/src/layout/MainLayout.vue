<template>
  <v-responsive
    class="border rounded"
  >
    <v-app>
      <v-app-bar
        @click="$router.push('/')"
      >
        <template #title>
          <v-img
              src="https://ystu.ru/local/templates/ystu/assets/img/logotype-big.svg"
              width="130px"
          />
        </template>
        <template #append>
          <div class="d-flex ga-3 mr-5">
            <span>
            {{ `${user?.firstName} ${user?.lastName}` }}
            </span>
            <v-icon
                icon="mdi-exit-to-app"
                @click="logout"
            />
          </div>
        </template>
      </v-app-bar>

      <v-navigation-drawer
        permanent
        :class="{ 'disable-nav': disableMenu }"
      >
        <v-list
          nav
          class="custom-active"
        >
          <v-list-item
            prepend-icon="mdi-package-variant"
            title="Обращения"
            value="notes"
            :active="currentRoute === '/notes'"
            @click="$router.push('/notes')"
          />
          <v-list-item
            v-if="user?.role === 'ADMIN'"
            prepend-icon="mdi-room-service"
            title="Темы обращений"
            value="topics"
            :active="currentRoute === '/topics'"
            @click="$router.push('/topics')"
          />
        </v-list>
      </v-navigation-drawer>

      <v-main class="mt-8">
        <v-container>
          <slot />
        </v-container>
      </v-main>
    </v-app>
  </v-responsive>
</template>

<script>
import {mapActions, mapGetters, mapState} from 'vuex'

export default {
  name: 'MainLayout',
  computed: {
    ...mapState('auth', ['user']),
    ...mapGetters(['disableMenu']),
    currentRoute() {
      return this.$route.path;
    },
  },
  methods: {
    ...mapActions('auth', ['logout']),
  }
}
</script>


<style lang="sass" scoped>
.avatar
  width: 40px
  height: 40px
  border-radius: 50%

.disable-nav
  z-index: 1000 !important
</style>

<style scoped>
.custom-active :deep(.v-list-item--active) {
  background-color: #fff3e0 !important;
  color: #e65100 !important;
  border-right: 3px solid #e65100;
}
</style>
