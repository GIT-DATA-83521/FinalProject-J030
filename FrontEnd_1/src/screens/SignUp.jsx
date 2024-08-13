import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import axiosInstance from '../axiosConfig';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function SignUp() {
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [dob, setDob] = useState('');
  const [city, setCity] = useState('');
  const [role, setRole] = useState('COMMENTER'); 
  const [showSuccess, setShowSuccess] = useState(false);

  const navigate = useNavigate();

  const handleSignUp = async (e) => {
    e.preventDefault();

    const userData = {
      firstName,
      lastName,
      email,
      password,
      dob,
      city,
      role,
    };

    try {
      const response = await axiosInstance.post('/user/signup', userData);

      if (response.status === 201) {
        toast.success('Registration Successful!', {
          position: "top-right",
          autoClose: 2000,
        });
        setShowSuccess(true);
        setTimeout(() => {
          navigate('/signin');
        }, 2000);
      } else {
        console.error('Unexpected response:', response);
      }
    } catch (error) {
      if (error.response && error.response.status === 400) {
        console.error('API error:', error.response.data.message);
        toast.error('Bad Request: ' + error.response.data.message, {
          position: "top-right",
          autoClose: 5000,
        });
      } else {
        console.error('API error:', error.message);
        toast.error('Server error. Please try again later.', {
          position: "top-right",
          autoClose: 5000,
        });
      }
    }
  };

  return (
    <div className="d-flex justify-content-center align-items-center vh-100 bg-light">
      <div className="bg-white p-4 rounded shadow-sm" style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="text-center mb-4">Sign Up</h2>
        <form onSubmit={handleSignUp}>
          <div className="mb-3">
            <label htmlFor="firstName" className="form-label">First Name</label>
            <input
              type="text"
              id="firstName"
              className="form-control"
              value={firstName}
              onChange={(e) => setFirstName(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="lastName" className="form-label">Last Name</label>
            <input
              type="text"
              id="lastName"
              className="form-control"
              value={lastName}
              onChange={(e) => setLastName(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">Email</label>
            <input
              type="email"
              id="email"
              className="form-control"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">Password</label>
            <input
              type="password"
              id="password"
              className="form-control"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="dob" className="form-label">Date of Birth</label>
            <input
              type="date"
              id="dob"
              className="form-control"
              value={dob}
              onChange={(e) => setDob(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="city" className="form-label">City</label>
            <input
              type="text"
              id="city"
              className="form-control"
              value={city}
              onChange={(e) => setCity(e.target.value)}
              required
            />
          </div>
          <div className="mb-3">
            <label htmlFor="role" className="form-label">Role</label>
            <select
              id="role"
              className="form-control"
              value={role}
              onChange={(e) => setRole(e.target.value)}
              required
            >
              <option value="ADMIN">Admin</option>
              <option value="BLOGGER">Blogger</option>
              <option value="COMMENTER">Commenter</option>
            </select>
          </div>
          <div className="mb-3">
            <button type="submit" className="btn btn-success w-100">Sign Up</button>
            <div className="text-center">
              <p>Already have an account? <Link to="/signin" className="text-decoration-none">Sign In</Link></p>
            </div>
          </div>
        </form>
      </div>
      <ToastContainer />
    </div>
  );
}

export default SignUp;
