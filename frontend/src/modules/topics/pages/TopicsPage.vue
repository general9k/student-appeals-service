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


  <v-dialog
      :model-value="deleteView"
      max-width="500px"
  >
    <v-card>
      <v-card-text>
        {{ `Удалить тему «${form.name}»?` }}
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn
            color="#0082c5"
            variant="flat"
            @click="deleteTopic(); CHANGE_DATA_BY_KEY( {deleteView: false})"
        >
          Удалить
        </v-btn>
        <v-btn
            color="#0082c5"
            variant="outlined"
            @click="CHANGE_DATA_BY_KEY( {deleteView: false})"
        >
          Отмена
        </v-btn>
        <v-spacer />
      </v-card-actions>
    </v-card>
  </v-dialog>

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
      'form',
      'deleteView',
    ]),
  },
  mounted() {
    this.getTopics()
  },
  methods: {
    headers() {
      return headers
    },
    ...mapActions('topics', ['getTopics', 'deleteTopic']),
    ...mapMutations('topics', ['CHANGE_DATA_BY_KEY', 'SET_FORM']),

    editHandler(item) {
      this.CHANGE_DATA_BY_KEY({modalType: 'edit', modalView: true})
      this.SET_FORM(item)
    },
    deleteHandler(item) {
      this.CHANGE_DATA_BY_KEY({deleteView: true})
      this.SET_FORM(item)
    },
  }
}
</script>

<style scoped>

</style>
