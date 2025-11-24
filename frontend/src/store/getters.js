export default {
  disableMenu: state => !!state.notes.modalView || !!state.topics.modalView,
}
