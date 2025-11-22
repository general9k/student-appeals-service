import moment from 'moment';

export default () => ({
  getDate(date, format = 'LL') {
    if (!date) {
      return '\u2014';
    }
    return moment(date).format(format);
  },
  getTime(date, format = 'LT') {
    if (!date) {
      return '\u2014';
    }
    return moment(date).format(format);
  },
});
