<template>
  <v-responsive
    class="border rounded"
  >
    <v-app>
      <v-app-bar
        class="bg-blue-lighten-3"
        title="YSTU"
        @click="$router.push('/')"
      >
        <template #append>
          <div class="d-flex flex-column mr-5">
            <v-icon
                icon="mdi-exit-to-app"
                @click="logout"
            />
          </div>
        </template>
      </v-app-bar>

      <v-navigation-drawer
        permanent
      >
        <v-list
          nav
        >
          <v-list-item
            prepend-icon="mdi-package-variant"
            title="Обращения"
            value="notes"
            @click="$router.push('/notes')"
          />
          <!-- todo v-if admin-->
          <v-list-item
            prepend-icon="mdi-room-service"
            title="Темы обращений"
            value="topics"
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
import {mapActions, mapState} from 'vuex'

export default {
  name: 'MainLayout',
  computed: {
    ...mapState('auth', ['user'])
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
</style>
