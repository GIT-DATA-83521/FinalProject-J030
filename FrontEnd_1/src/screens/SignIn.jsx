import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { toast } from 'react-toastify';
import axiosInstance from '../axiosConfig';


function SignIn() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleSignIn = async (e) => {
    e.preventDefault();
    
    try {
      // Make a POST request to the backend API
      const response = await axiosInstance.post('/user/signin', { email, password });
      
      // Assuming the response contains a success message and maybe a token
      if (response.status === 200) {
        toast.success('Login Successful!');

        // Save token to local storage or context if needed
        // localStorage.setItem('token', response.data.token);

        // Redirect to the home page after a short delay
        setTimeout(() => {
          navigate('/home');
        }, 2000);
      } else {
        // Handle unexpected response status
        toast.error('Unexpected error occurred. Please try again.');
      }
    } catch (error) {
      // Handle error (e.g., incorrect credentials, server error)
      if (error.response && error.response.status === 401) {
        toast.error('Invalid email or password. Please try again.');
      } else {
        toast.error('Server error. Please try again later.');
      }
    }
  };

  return (
    <div className="signin-container d-flex justify-content-center align-items-center min-vh-100" style={{ backgroundImage: 'url(/background.jpg)', backgroundSize: 'cover', backgroundRepeat: 'no-repeat', backgroundPosition: 'center center' }}>
      <div className="signin-form-container bg-light p-4 rounded shadow" style={{ maxWidth: '400px', width: '100%' }}>
        <h2 className="text-center mb-4">Sign In</h2>
        <form onSubmit={handleSignIn}>
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
            <button type="submit" className="btn btn-success w-100">Sign In</button>
          </div>
          <div className="text-center">
            <p>Don't have an account? <Link to="/signup" className="text-decoration-none">Sign Up</Link></p>
          </div>
        </form>
      </div>
    </div>
  );
}

export default SignIn;
