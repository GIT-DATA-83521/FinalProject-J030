import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; // Import useNavigate for redirection
import axiosInstance from '../axiosConfig';
import { toast } from 'react-toastify'; // Import Toastify
import 'react-toastify/dist/ReactToastify.css'; // Import Toastify CSS
import 'bootstrap/dist/css/bootstrap.min.css';

function Home() {
  const [blogPosts, setBlogPosts] = useState([]);
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const fetchBlogPosts = async () => {
      try {
        const response = await axiosInstance.get('/posts/getallposts');
        setBlogPosts(response.data);
      } catch (error) {
        console.error('Error fetching blog posts:', error);
      }
    };

    fetchBlogPosts();
  }, []);

  const handleLogout = () => {
    // Clear any authentication tokens or session data here
    // For example, if you are using localStorage to store the token:
    localStorage.removeItem('authToken');

    // Show Toastify message
    toast.success('Logged out successfully!');

    // Redirect to the sign-in page
    setTimeout(() => {
      navigate('/signin');
    }, 1000); // Adjust the delay if needed
  };

  const handlePostClick = (id) => {
    navigate(`/posts/${id}`); // Navigate to the individual post page
  };

  return (
    <div className="container my-4">
      <header className="d-flex justify-content-between align-items-center p-4 mb-4 bg-white rounded shadow">
        <div className="fw-bold fs-3 text-dark">MyBlog</div>
        <div className="text-primary fs-4">Thoughts, Ideas, and Stories</div>
        <nav className="d-flex gap-3">
          <a href="/home" className="text-dark text-decoration-none">Home</a>
          <a href="/categories" className="text-dark text-decoration-none">Categories</a>
          <a href="/posts" className="text-dark text-decoration-none">Posts</a>
          <a href="/search" className="text-dark text-decoration-none">Search</a>
          <a href="#logout" onClick={handleLogout} className="text-dark text-decoration-none">Logout</a>
        </nav>
      </header>
      <div className="d-flex flex-column gap-4">
        {blogPosts.map((post) => (
          <div
            key={post.id}
            className="d-flex align-items-start p-3 bg-white rounded shadow text-decoration-none text-dark"
            style={{ cursor: 'pointer' }}
            onClick={() => handlePostClick(post.id)} // Handle post click
          >
            <img src={post.image} alt={post.title} className="me-3" style={{ width: '200px', height: '130px', objectFit: 'cover', borderRadius: '10px' }} />
            <div>
              <h2 className="fs-4 mb-2">{post.title}</h2>
              <p className="text-muted">{post.excerpt}</p>
            </div>
          </div>
        ))}
      </div>
      <button
        className="btn btn-primary position-fixed bottom-0 end-0 m-3 rounded-circle"
        style={{ width: '60px', height: '60px', padding: '0', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
      >
        <span className="fs-3 text-white">+</span>
      </button>
      <footer className="text-center mt-4 p-3 bg-white rounded shadow">
        <p className="mb-0">&copy; 2024 My Blog. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Home;
