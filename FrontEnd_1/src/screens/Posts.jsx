import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function Posts() {
  const [posts, setPosts] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axiosInstance.get('/posts/getallposts');
        setPosts(response.data);
      } catch (error) {
        console.error('Error fetching posts:', error);
      }
    };

    fetchPosts();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    toast.success('Logged out successfully!');
    setTimeout(() => {
      navigate('/signin');
    }, 1000);
  };

  const handleCreatePost = () => {
    navigate('/add-post');
  };

  const handleEdit = (postId) => {
    navigate(`/edit-post/${postId}`);
  };

  const handleDelete = async (postId) => {
    if (!postId) {
      toast.error('Post ID is missing.');
      return;
    }
    
    try {
      console.log('Deleting post with ID:', postId);
      await axiosInstance.delete(`/posts/${postId}`);
      setPosts(posts.filter(post => post.id !== postId));
      toast.success('Post deleted successfully!');
    } catch (error) {
      console.error('Error deleting post:', error);
      toast.error('Failed to delete post. Please try again.');
    }
  };

  return (
    <div className="container my-4">
      <header className="d-flex justify-content-between align-items-center p-4 mb-4 bg-white rounded shadow">
        <div className="fw-bold fs-3 text-dark">MyBlog</div>
        <div className="text-primary fs-4">Posts</div>
        <nav className="d-flex gap-3">
          <a href="/home" className="text-dark text-decoration-none">Home</a>
          <a href="/categories" className="text-dark text-decoration-none">Categories</a>
          <a href="/posts" className="text-dark text-decoration-none">Posts</a>
          <a href="/search" className="text-dark text-decoration-none">Search</a>
          <a href="#logout" onClick={handleLogout} className="text-dark text-decoration-none">Logout</a>
        </nav>
      </header>
      <div className="row">
        {posts.map((post) => (
          <div key={post.id} className="col-md-6 mb-4">
            <div className="card h-100 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">{post.title}</h5>
                <h6 className="card-subtitle mb-2 text-muted">Author ID: {post.authorId} | Category ID: {post.categoryId}</h6>
                <p className="card-text">{post.description}</p>
                <div className="card-content">
                  <p>{post.content}</p>
                </div>
                <button
                  className="btn btn-warning me-2"
                  onClick={() => handleEdit(post.id)}
                >
                  Edit
                </button>
                <button
                  className="btn btn-danger"
                  onClick={() => handleDelete(post.id)}
                >
                  Delete
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
      <ToastContainer />
      <button
        className="btn btn-primary position-fixed bottom-0 end-0 m-3 rounded-circle"
        style={{ width: '60px', height: '60px', padding: '0', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
        onClick={handleCreatePost}
      >
        <span className="fs-3 text-white">+</span>
      </button>
      <footer className="text-center mt-4 p-3 bg-white rounded shadow">
        <p className="mb-0">&copy; 2024 My Blog. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Posts;
