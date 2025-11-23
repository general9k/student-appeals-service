<template>
  <div class="d-flex justify-space-between">
    <h1>
      Обращения
    </h1>
    <v-btn
        color="blue-darken-4"
        variant="flat"
        @click="CHANGE_DATA_BY_KEY({modalView: true, modalType: 'create'})"
    >
      Создать обращение
    </v-btn>
  </div>


  <v-data-table-server
      :headers="headers()"
      :items="notes"
      :items-per-page-options="[
        {value: 10, title: '10'},
        {value: 25, title: '25'},
        {value: 50, title: '50'},
        {value: 100, title: '100'},
      ]"
      :items-length="notes.length"
      item-value="id"
  >
    <template #item.name="{item}">
      {{ item.name }}
      <v-chip
        size="S"
        :color="colors[item.status.id - 1]"
      >
        <div style="font-size: 11px; padding: 3px">
          {{ item.status.name }}
        </div>
      </v-chip>
    </template>
    <template #item.topic="{item}">
      {{ item.topic.name }}
    </template>
    <template #item.actions="{ item }">
      <v-icon
          class="me-2"
          size="small"
          @click="editHandler(item)"
      >
        mdi-pencil
      </v-icon>
<!--      <v-icon-->
<!--          size="small"-->
<!--          @click="storageStore.deleteModalView = true; storageStore.form.id = item.id"-->
<!--      >-->
<!--        mdi-delete-->
<!--      </v-icon>-->
    </template>
  </v-data-table-server>

  <NotesModal/>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";
import NotesModal from "@/modules/notes/components/NotesModal.vue";
import {headers} from "@/modules/notes/entities/headers.js";

export default {
  name: 'NotesPage',
  components: {NotesModal},
  computed: {
    ...mapState('notes', ['modalView', 'modalType', 'notes']),
    colors() {
      return ['blue', 'green', 'gray']
    }
  },
  mounted() {
    this.getStatuses()
    this.getNotes()
    this.getTopics()
  },
  methods: {
    headers() {
      return headers
    },
    ...mapActions(['getStatuses', 'getTopics']),
    ...mapActions('notes', ['getNotes']),
    ...mapMutations('notes', ['CHANGE_DATA_BY_KEY', 'SET_FORM']),
    editHandler(item) {
      this.CHANGE_DATA_BY_KEY({modalType: 'edit', modalView: true})
      this.SET_FORM({...item, topicId: item.topic.id, statusId: item.status.id})
    }
  }
}
</script>

<style scoped>

</style>
