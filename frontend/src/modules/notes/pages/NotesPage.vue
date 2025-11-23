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
      v-model:sort-by="sortBy"
      :headers="headers()"
      :items="notes"
      :items-per-page-options="[
        {value: 10, title: '10'},
        {value: 25, title: '25'},
        {value: 50, title: '50'},
        {value: 100, title: '100'},
      ]"
      :items-length="totalCount"
      item-value="id"
      :items-per-page="size"
      @update:page="changePageHandler"
      @update:items-per-page="changePageItemsHandler"
      @update:sort-by="sortHandler"
  >
    <template #item.name="{item}">
      {{ item.name }}
    </template>
    <template #item.status="{item}">
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
    <template #item.author="{item}">
      {{ `${item.author.firstName} ${item.author.lastName}` }}
    </template>
    <template #item.createdAt="{item}">
      {{ getDate(item.createdAt, 'DD.MM.YYYY HH:mm') }}
    </template>
    <template #item.actions="{ item }">
      <v-icon
          class="me-2"
          size="small"
          @click="editHandler(item)"
      >
        mdi-pencil
      </v-icon>
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
  data() {
    return {
      sortBy: [],
    }
  },
  computed: {
    ...mapState('notes', [
      'modalView',
      'modalType',
      'notes',
      'size',
      'totalCount',
      'sort',
      'order'
    ]),
    colors() {
      return ['blue', 'green', 'gray']
    }
  },
  mounted() {
    this.getStatuses()
    this.getNotes()
    this.getTopics()
    // Инициализируем сортировку из store
    this.initializeSort()
  },
  methods: {
    headers() {
      return headers
    },
    ...mapActions(['getStatuses', 'getTopics']),
    ...mapActions('notes', ['getNotes']),
    ...mapMutations('notes', ['CHANGE_DATA_BY_KEY', 'SET_FORM']),

    // Инициализация сортировки
    initializeSort() {
      if (this.sort && this.order) {
        this.sortBy = [{key: this.sort, order: this.order}]
      }
    },

    // Обработчик сортировки
    sortHandler(sort) {
      if (sort.length > 0) {
        const sortItem = sort[0]
        this.CHANGE_DATA_BY_KEY({
          sort: sortItem.key,
          order: sortItem.order
        })
      } else {
        // Сброс сортировки
        this.CHANGE_DATA_BY_KEY({
          sort: null,
          order: null
        })
      }
      this.getNotes()
    },

    editHandler(item) {
      this.CHANGE_DATA_BY_KEY({modalType: 'edit', modalView: true})
      this.SET_FORM({...item, topicId: item.topic.id, statusId: item.status.id})
    },

    changePageHandler(page) {
      this.CHANGE_DATA_BY_KEY({page: page - 1})
      this.getNotes();
    },

    changePageItemsHandler(limit) {
      this.CHANGE_DATA_BY_KEY({size: limit})
      this.getNotes();
    },
  }
}
</script>

<style scoped>

</style>
