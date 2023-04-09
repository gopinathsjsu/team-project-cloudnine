export default {
  getTwoDigits: (n) => Math.floor(n / 10) == 0 ? `0${n}` : `${n}`,

  userPresent() {
    return localStorage.getItem('name') && localStorage.getItem('email');
  },

  isAdmin() {
    const x = localStorage.getItem('is_admin') === 'true';
    return x;
  },

  removeUser() {
    localStorage.removeItem('name');
    localStorage.removeItem('email');
    localStorage.removeItem('is_admin');
  },

  getUserId() {
    return localStorage.getItem('id');
  },

  getName() {
    return localStorage.getItem('name');
  },

  getEmail() {
    return localStorage.getItem('email');
  },

  setGym(gym) {
    localStorage.setItem('gym', gym);
  },

  getGym(gym) {
    return localStorage.getItem('gym') ?? '';
  },

  completeLoginOrReg(id, name, email, isAdmin) {
    localStorage.setItem('id', id);
    localStorage.setItem('name', name);
    localStorage.setItem('email', email);
    localStorage.setItem('is_admin', isAdmin);
  },

  getToast(message, severity) {
    return {
      severity,
      message,
    };
  },

  timeify(time) {
    const date = new Date(time);
    return `${this.getTwoDigits(date.getHours())}:${this.getTwoDigits(date.getMinutes())}`;
  },

  dateify(time) {
    const date = new Date(time);
    return `${this.getTwoDigits(date.getFullYear())}-${this.getTwoDigits(date.getMonth())}-${this.getTwoDigits(date.getDate())}`;
  },

  getDuration(time1, time2) {
    const date1 = new Date(time1);
    const date2 = new Date(time2);

    return Math.ceil((date2 - date1) / 1000 / 60);
  }
}