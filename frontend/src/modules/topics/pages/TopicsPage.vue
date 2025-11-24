<template>
  <div class="d-flex justify-space-between">
    <h1>
      Темы обращений
    </h1>
    <v-btn
        color="#0082c5"
        variant="flat"
        @click="CHANGE_DATA_BY_KEY({modalView: true, modalType: 'create'})"
    >
      Создать тему
    </v-btn>
  </div>

  <v-data-table-server
      :headers="headers()"
      :items="topics"
      :items-length="topics.length"
      item-value="id"
      hide-default-footer
      :header-props="{ class: 'font-weight-bold' }"
  >
    <template #item.name="{item}">
      {{ item.name }}
    </template>
    <template #item.actions="{ item }">
      <v-icon
          class="me-2"
          size="small"
          color="#0082c5"
          @click="editHandler(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
          class="me-2"
          size="small"
          color="#0082c5"
          @click="deleteHandler(item)"
      >
        mdi-delete
      </v-icon>
    </template>
  </v-data-table-server>

  <TopicsModal/>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";
import TopicsModal from "@/modules/topics/components/TopicsModal.vue";
import {headers} from "@/modules/topics/entities/headers.js";

export default {
  name: 'TopicsPage',
  components: {TopicsModal},
  data() {
    return {
      sortBy: [],
    }
  },
  computed: {
    ...mapState('topics', [
      'modalView',
      'modalType',
      'topics',
    ]),
  },
  mounted() {
    this.getTopics()
  },
  methods: {
    headers() {
      return headers
    },
    ...mapActions('topics', ['getTopics']),
    ...mapMutations('topics', ['CHANGE_DATA_BY_KEY', 'SET_FORM']),

    editHandler(item) {
      this.CHANGE_DATA_BY_KEY({modalType: 'edit', modalView: true})
      this.SET_FORM(item)
    },
  }
}
</script>

<style scoped>

</style>
