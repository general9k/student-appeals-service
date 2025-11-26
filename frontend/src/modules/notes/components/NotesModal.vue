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
        :readonly="modalType !== 'create'"
        :model-value="form.name"
        @input="form.name = $event.target.value"
      />
      <v-textarea
        class="mt-4"
        label="Описание"
        :readonly="modalType !== 'create'"
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
        :readonly="modalType !== 'create'"
        item-value="id"
        :model-value="form.topicId"
        @update:modelValue="form.topicId = $event"
      />
      <v-select
        v-if="modalType !== 'create'"
        class="mt-4"
        label="Статус"
        density="comfortable"
        :readonly="modalType === 'view'"
        :items="statuses"
        item-title="name"
        item-value="id"
        :model-value="form.statusId"
        @update:modelValue="form.statusId = $event"
      />
    </template>

    <h4>Комментарии</h4>
    <div v-for="comment in comments" :key="comment.id">
      <div>
        {{ `${comment.author} - ${getDate(comment.createdAt, 'DD.MM.YYYY HH:mm')}` }}
        <v-textarea
            class="mt-4"
            label="Описание"
            readonly
            density="comfortable"
            :model-value="comment.text"
        />
      </div>

      <v-textarea
          class="mt-4"
          label="Описание"
          density="comfortable"
          :model-value="newComment"
          @input="newComment = $event.target.value"
      />
      <v-btn
          class="mr-2"
          color="#0082c5"
          variant="flat"
          @click="sendComment"
      >
        Отправить
      </v-btn>
    </div>
    <template #append>
      <v-btn
        class="mr-2"
        color="#0082c5"
        variant="flat"
        @click="saveHandler"
      >
        Сохранить
      </v-btn>
      <v-btn
        color="#0082c5"
        variant="outlined"
        @click="CHANGE_DATA_BY_KEY({modalView: false}); clearForm()"
      >
        Отменить
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
  mounted() {
    this.getComments()
  },
  methods: {
    ...mapActions('notes', ['createNote', 'editNote', 'clearForm', 'sendComment', 'getComments']),
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
