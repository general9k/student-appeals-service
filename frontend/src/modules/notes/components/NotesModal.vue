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

      <!-- Форма обращения -->
      <v-form v-model="formValid" ref="noteForm">
        <v-text-field
            class="mt-4"
            label="Название обращения"
            density="comfortable"
            :readonly="modalType !== 'create'"
            :model-value="form.name"
            @input="form.name = $event.target.value"
            :rules="[requiredRule]"
            :error-messages="errors.name"
            @blur="validateField('name')"
        />

        <v-textarea
            class="mt-4"
            label="Описание"
            :readonly="modalType !== 'create'"
            density="comfortable"
            :model-value="form.description"
            @input="form.description = $event.target.value"
            :rules="[requiredRule]"
            :error-messages="errors.description"
            @blur="validateField('description')"
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
            :rules="[requiredRule]"
            :error-messages="errors.topicId"
            @blur="validateField('topicId')"
        />

        <v-select
            v-if="modalType !== 'create'"
            class="mt-4"
            label="Статус"
            density="comfortable"
            :readonly="modalType === 'view'"
            :items="statuses"
            :clearable="false"
            item-title="name"
            item-value="id"
            :model-value="form.statusId"
            @update:modelValue="form.statusId = $event"
        />
      </v-form>

      <!-- Блок комментариев -->
      <div v-if="modalType !== 'create'" class="mt-6">
        <h3 class="mb-4">Комментарии</h3>

        <!-- Список комментариев -->
        <div v-if="comments.length > 0" class="comments-list">
          <v-card
              v-for="comment in comments"
              :key="comment.id"
              class="mb-3 comment-card"
              variant="outlined"
          >
            <v-card-text class="pa-3">
              <div class="d-flex align-center justify-space-between mb-2">
                <div class="d-flex align-center">
                  <v-avatar size="32" color="primary" class="mr-2">
                    <span class="white--text text-caption">
                      {{ getInitials(comment.author) }}
                    </span>
                  </v-avatar>
                  <div>
                    <strong class="text-body-2">
                      {{ `${comment.author?.firstName} ${comment.author?.lastName}` }}
                    </strong>
                    <div class="text-caption text-medium-emphasis">
                      {{ getDate(comment.createdAt, 'DD.MM.YYYY HH:mm') }}
                    </div>
                  </div>
                </div>
              </div>
              <p class="text-body-2 mb-0 comment-text">
                {{ comment.text }}
              </p>
            </v-card-text>
          </v-card>
        </div>

        <!-- Пустой state -->
        <div v-else class="text-center py-8">
          <v-icon size="48" color="grey-lighten-1" class="mb-2">mdi-chat-outline</v-icon>
          <p class="text-grey">Комментариев пока нет</p>
        </div>

        <!-- Форма нового комментария -->
        <v-form v-model="commentValid" ref="commentForm" class="mt-4">
          <v-textarea
              label="Новый комментарий"
              density="comfortable"
              :model-value="newComment"
              @input="CHANGE_DATA_BY_KEY({newComment: $event.target.value})"
              :rules="[requiredRule]"
              :error-messages="errors.comment"
              @blur="validateField('comment')"
              placeholder="Введите ваш комментарий..."
              rows="3"
              auto-grow
          />
          <v-btn
              class="mt-2"
              color="#0082c5"
              variant="flat"
              @click="sendCommentHandler"
              :disabled="!commentValid"
              :loading="sendingComment"
          >
            Отправить
          </v-btn>
        </v-form>
      </div>
    </template>

    <template #append>
      <v-btn
          class="mr-2"
          color="#0082c5"
          variant="flat"
          @click="saveHandler"
          :disabled="modalType === 'create' && !formValid"
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
  name: 'NotesModal',
  data() {
    return {
      formValid: false,
      commentValid: false,
      saving: false,
      sendingComment: false,
      errors: {
        name: '',
        description: '',
        topicId: '',
        comment: ''
      }
    }
  },
  computed: {
    ...mapState(['topicsList', 'statuses']),
    ...mapState('notes', ['form', 'modalType', 'modalView', 'newComment', 'comments']),
    getTitle() {
      return this.modalType === 'create' ? 'Новое обращение' : 'Редактирование обращения'
    }
  },
  methods: {
    ...mapActions('notes', ['createNote', 'editNote', 'clearForm', 'sendComment']),
    ...mapMutations('notes', ['CHANGE_DATA_BY_KEY']),

    requiredRule(value) {
      return !!value || 'Обязательное поле';
    },

    validateField(field) {
      const value = field === 'comment' ? this.newComment : this.form[field];
      this.errors[field] = !value ? 'Обязательное поле' : '';
    },

    async validateForm() {
      if (this.modalType === 'create') {
        const fields = ['name', 'description', 'topicId'];
        let isValid = true;

        fields.forEach(field => {
          if (!this.form[field]) {
            this.errors[field] = 'Обязательное поле';
            isValid = false;
          } else {
            this.errors[field] = '';
          }
        });

        return isValid;
      }
      return true;
    },

    async saveHandler() {
      if (this.modalType === 'create' && !await this.validateForm()) {
        return;
      }

      this.saving = true;
      try {
        if (this.modalType === 'create') {
          await this.createNote();
        } else {
          await this.editNote();
        }
        this.closeModal();
      } catch (error) {
        console.error('Ошибка при сохранении:', error);
      } finally {
        this.saving = false;
      }
    },

    async sendCommentHandler() {
      if (!this.commentValid) {
        this.validateField('comment');
        return;
      }

      this.sendingComment = true;
      try {
        await this.sendComment();
        // Очищаем ошибку после успешной отправки
        this.errors.comment = '';
      } catch (error) {
        console.error('Ошибка при отправке комментария:', error);
      } finally {
        this.sendingComment = false;
      }
    },

    closeModal() {
      this.CHANGE_DATA_BY_KEY({modalView: false});
      this.clearForm();
      this.resetValidation();
    },

    resetValidation() {
      this.formValid = false;
      this.commentValid = false;
      Object.keys(this.errors).forEach(key => {
        this.errors[key] = '';
      });
    },

    getInitials(author) {
      if (!author) return '?';
      const first = author.firstName?.[0] || '';
      const last = author.lastName?.[0] || '';
      return (first + last).toUpperCase() || 'U';
    }
  }
}
</script>

<style scoped>
.comments-list {
  max-height: 400px;
  overflow-y: auto;
}

.comment-card {
  border-left: 3px solid #0082c5;
}

.comment-text {
  white-space: pre-wrap;
  line-height: 1.4;
}

:deep(.v-field__outline) {
  border-radius: 8px;
}

:deep(.v-card) {
  border-radius: 8px;
}
</style>
