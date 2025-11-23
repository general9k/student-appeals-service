export default {
  notes: [],

  form: {
    id: null,
    name: null,
    description: null,
    topicId: null,
    statusId: null,
  },

  modalType: 'create',
  modalView: false,

  filter: {
    noteId: null,
    statusId: null,
    topicId: null,
    searchString: null,
  },

  totalCount: 0,
  page: 0,
  size: 10,
  sort: 'createdAt',
  order: 'DESC',
}
