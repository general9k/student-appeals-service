<template>
  <v-navigation-drawer
      temporary
      class="pa-4"
      location="right"
      width="500"
      :title="getTitle"
      :model-value="modalView"
      @update:model-value="closeModal"
  >
    <template #default>
      <h1>
        {{ getTitle }}
      </h1>

      <v-form v-model="formValid" ref="topicForm">
        <v-text-field
            class="mt-4"
            label="Название"
            density="comfortable"
            :model-value="form.name"
            @input="onNameInput"
            :rules="nameRules"
            :error-messages="errors.name"
            @blur="validateName"
            placeholder="Введите название темы"
            :disabled="saving"
        />
      </v-form>
    </template>

    <template #append>
      <v-btn
          class="mr-2"
          color="#0082c5"
          variant="flat"
          @click="saveHandler"
          :disabled="!formValid || saving"
          :loading="saving"
      >
        Сохранить
      </v-btn>
      <v-btn
          color="#0082c5"
          variant="outlined"
          @click="closeModal"
          :disabled="saving"
      >
        Отменить
      </v-btn>
    </template>
  </v-navigation-drawer>
</template>

<script>
import {mapActions, mapMutations, mapState} from "vuex";

export default {
  name: 'TopicsModal',
  data() {
    return {
      formValid: false,
      saving: false,
      errors: {
        name: ''
      },
      nameRules: [
        value => !!value || 'Название темы обязательно для заполнения',
        value => (value && value.length >= 2) || 'Название должно содержать минимум 2 символа',
        value => (value && value.length <= 100) || 'Название не должно превышать 100 символов'
      ]
    }
  },
  computed: {
    ...mapState('topics', ['form', 'modalType', 'modalView']),
    getTitle() {
      return this.modalType === 'create' ? 'Новая тема' : 'Редактирование темы'
    }
  },
  methods: {
    ...mapActions('topics', ['createTopic', 'editTopic', 'clearForm']),
    ...mapMutations('topics', ['CHANGE_DATA_BY_KEY']),

    onNameInput(event) {
      this.form.name = event.target.value;
      // Сбрасываем ошибку при вводе
      if (this.errors.name && event.target.value) {
        this.validateName();
      }
    },

    validateName() {
      const value = this.form.name;

      if (!value) {
        this.errors.name = 'Название темы обязательно для заполнения';
        return false;
      }

      if (value.length < 2) {
        this.errors.name = 'Название должно содержать минимум 2 символа';
        return false;
      }

      if (value.length > 100) {
        this.errors.name = 'Название не должно превышать 100 символов';
        return false;
      }

      this.errors.name = '';
      return true;
    },

    async validateForm() {
      return this.validateName();
    },

    async saveHandler() {
      if (!await this.validateForm()) {
        return;
      }

      this.saving = true;
      try {
        if (this.modalType === 'create') {
          await this.createTopic();
        } else {
          await this.editTopic();
        }
        this.closeModal();
      } catch (error) {
        console.error('Ошибка при сохранении темы:', error);
        // Можно добавить отображение ошибки пользователю
      } finally {
        this.saving = false;
      }
    },

    closeModal() {
      this.CHANGE_DATA_BY_KEY({modalView: false});
      this.clearForm();
      this.resetValidation();
    },

    resetValidation() {
      this.formValid = false;
      this.errors.name = '';
    }
  }
}
</script>

<style scoped>
:deep(.v-field__outline) {
  border-radius: 8px;
}
</style>
