import axios from 'axios';
import util from 'util';

axios.defaults.baseURL = 'http://10.0.0.27:8080/api';
// axios.defaults.baseURL = 'http://3.14.65.89:8080/api';

export default class {

  constructor(context, setContext) {
    this.context = context;
    this.setContext = setContext;
  }

  async postRegister(
    gymLocationId,
    firstName,
    lastName,
    email,
    password,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post('/users/register', {
        gymLocationId,
        firstName,
        lastName,
        email,
        password
      });
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('Registration successful', 'success')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Account exists', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async postLogin(
    email,
    password,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post('/users/login', {
        email,
        password
      });
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('Login successful', 'success')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Bad credentials', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getProfile(
    email,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get('/users/' + email);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Cannot retrieve profile info', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getAllClasses(
    gymLocation,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get('/class-schedules/gym-location/' + gymLocation);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Cannot retrieve all classes', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getClassEnrollment(
    userId,
    classScheduleId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get('/enrollments/enroll', {
        params: {
          userId,
          classScheduleId
        }
      });
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('Enrollment(s) successful', 'success')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not enroll', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async postUserActivity(
    userId,
    gymLocationId,
    activityType,
    startTime,
    endTime,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post('/activities/addActivityToUser?userId=' + userId, {
        gymLocationId,
        activityType,
        startTime,
        endTime,
      });
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('Activity added', 'success')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not add activity', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getUserActivityByPeriod(
    userId,
    period,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get(`/activities/user/${userId}/activities/recent?period=${period}`);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not get activity for this period', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getEnrollments(
    period,
    locationId,
    classId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get('/enrollments/enrollmentStats', {
        params: {
          period,
          locationId,
          classId
        }
      });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not get all enrollments', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getAllUsers(
    gymLocationId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get('/gym-location/' + gymLocationId);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not get all users', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async postCheckin(
    userId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post(`/users/${userId}/checkin`);
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('User checked-in', 'success')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not check-in user', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async postCheckout(
    userId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post(`/users/${userId}/checkout`);
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('User checked-out', 'info')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not check-out user', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getTimeSpentStats(
    userId,
    period,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get(`/timeSpentStats?userId=${userId}&period=${period}`);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not get stats', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getMemberships(
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get(`/memberships`);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not get memberships', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async postEnrollMembership(
    userId,
    membershipId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.post(`/add-membership?userId=${userId}&membershipId=${membershipId}`);
      if (!hideToast)
        this.setContext({
          ...this.context,
          toast: util.getToast('Membership updated', 'info')
        });
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Could not udpated membership', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }

  async getVisitors(
    period,
    locationId,
    onSuccess,
    onError,
    hideToast
  ) {
    try {
      const res = await axios.get(`/checkInStats?period=${period}&locationId=${locationId}`);
      if (onSuccess) onSuccess(res.data);
      return res.data;
    } catch (err) {
      console.log('API Error', err);
      this.setContext({
        ...this.context,
        toast: util.getToast('Cannot retrieve visitors', 'error')
      });
      if (onError) onError(err);
      return null;
    }
  }
}