<template>
  <v-navigation-drawer
    temporary
    class="pa-4"
    location="right"
    width="500"
    :title="getTitle"
    :model-value="modalView"
    @update:model-value="CHANGE_DATA_BY_KEY({modalView: false}); clearForm()"
  >
    <template #default>
      <h1>
        {{ getTitle }}
      </h1>
      <v-text-field
        class="mt-4"
        label="Название обращения"
        density="comfortable"
        :disabled="modalType === 'edit'"
        :model-value="form.name"
        @input="form.name = $event.target.value"
      />
      <v-textarea
        class="mt-4"
        label="Описание"
        :disabled="modalType === 'edit'"
        density="comfortable"
        :model-value="form.description"
        @input="form.description = $event.target.value"
      />
      <v-select
        class="mt-4"
        label="Тема обращения"
        density="comfortable"
        :items="topicsList"
        item-title="name"
        :disabled="modalType === 'edit'"
        item-value="id"
        :model-value="form.topicId"
        @update:modelValue="form.topicId = $event"
      />
      <!-- todo and isAdmin-->
      <v-select
        v-if="modalType === 'edit'"
        class="mt-4"
        label="Статус"
        density="comfortable"
        :items="statuses"
        item-title="name"
        item-value="id"
        :model-value="form.statusId"
        @update:modelValue="form.statusId = $event"
      />
    </template>
    <template #append>
      <v-btn
        class="mr-2"
        color="blue-darken-4"
        variant="outlined"
        @click="CHANGE_DATA_BY_KEY({modalView: false}); clearForm()"
      >
        Отменить
      </v-btn>
      <v-btn
        color="blue-darken-4"
        variant="flat"
        @click="saveHandler"
      >
        Сохранить
      </v-btn>
    </template>
  </v-navigation-drawer>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: 'NotesModal',
  computed: {
    ...mapState(['topicsList', 'statuses']),
    ...mapState('notes', ['form', 'modalType', 'modalView']),
    getTitle() {
      return this.modalType === 'create' ? 'Новое обращение' : 'Редактирование обращения'
    }
  },
  methods: {
    ...mapActions('notes', ['createNote', 'editNote', 'clearForm']),
    ...mapMutations('notes', ['CHANGE_DATA_BY_KEY']),
    async saveHandler() {
      if (this.modalType === 'create') {
        await this.createNote();
      } else {
        await this.editNote();
      }
    },
  }
}
</script>
