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
        label="Название"
        density="comfortable"
        :model-value="form.name"
        @input="form.name = $event.target.value"
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
  name: 'TopicsModal',
  computed: {
    ...mapState('topics', ['form', 'modalType', 'modalView']),
    getTitle() {
      return this.modalType === 'create' ? 'Новая тема' : 'Редактирование темы'
    }
  },
  methods: {
    ...mapActions('topics', ['createTopic', 'editTopic', 'clearForm']),
    ...mapMutations('topics', ['CHANGE_DATA_BY_KEY']),
    async saveHandler() {
      if (this.modalType === 'create') {
        await this.createTopic();
      } else {
        await this.editTopic();
      }
    },
  }
}
</script>
