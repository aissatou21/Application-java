import axios from 'axios';

const registerUser = (username, password, role) => {
  return axios.post('/api/users/register', { username, password, role });
};

const loginUser = (username, password) => {
  return axios.post('/api/users/login', { username, password })
    .then(response => response.data.token);
};

const getUsers = (token) => {
  return axios.get('/api/users', { headers: { Authorization: token } })
    .then(response => response.data);
};

const deleteUser = (id, token) => {
  return axios
};