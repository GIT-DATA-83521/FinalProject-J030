import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import { toast, ToastContainer } from 'react-toastify'; // Import Toastify
import 'react-toastify/dist/ReactToastify.css'; // Import Toastify CSS
import 'bootstrap/dist/css/bootstrap.min.css';

function Categories() {
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const fetchCategories = async () => {
      try {
        const response = await axiosInstance.get('/categories/getAllCat');
        setCategories(response.data);
      } catch (error) {
        console.error('Error fetching categories:', error);
      }
    };

    fetchCategories();
  }, []);

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this category and all its related posts?')) {
      try {
        // Send delete request to backend
        await axiosInstance.delete(`/categories/${id}`);
        
        // Update local state
        setCategories(categories.filter(category => category.id !== id));
        
        // Show success message
        toast.success('Category and related posts deleted successfully!');
      } catch (error) {
        console.error('Error deleting category:', error);
        toast.error('Failed to delete category. Please try again.');
      }
    }
  };

  const handleEdit = (id) => {
    // Navigate to Edit Category page
    navigate(`/edit-category/${id}`);
  };

  const handleLogout = () => {
    // Clear any authentication tokens or session data here
    localStorage.removeItem('authToken');

    // Show Toastify message
    toast.success('Logged out successfully!');

    // Redirect to the sign-in page
    setTimeout(() => {
      navigate('/signin');
    }, 1000); // Adjust the delay if needed
  };

  const handleAddCategory = () => {
    // Redirect to Add Category page
    navigate('/add-category');
  };

  return (
    <div className="container my-4">
      <header className="d-flex justify-content-between align-items-center p-4 mb-4 bg-white rounded shadow">
        <div className="fw-bold fs-3 text-dark">MyBlog</div>
        <div className="text-primary fs-4">Categories</div>
        <nav className="d-flex gap-3">
          <a href="/home" className="text-dark text-decoration-none">Home</a>
          <a href="/categories" className="text-dark text-decoration-none">Categories</a>
          <a href="/posts" className="text-dark text-decoration-none">Posts</a>
          <a href="/search" className="text-dark text-decoration-none">Search</a>
          <a href="#logout" onClick={handleLogout} className="text-dark text-decoration-none">Logout</a>
        </nav>
      </header>
      <div className="d-flex flex-column gap-4 mb-4">
        <button
          className="btn btn-primary"
          onClick={handleAddCategory}
        >
          Add Category
        </button>
      </div>
      <div className="row">
        {categories.map((category) => (
          <div key={category.id} className="col-md-4 mb-4">
            <div className="card h-100 shadow-sm">
              <div className="card-body">
                <h5 className="card-title">{category.categoryName}</h5> {/* Display category name */}
                <p className="card-text">{category.description}</p> {/* Display category description */}
                <button className="btn btn-warning me-2" onClick={() => handleEdit(category.id)}>Edit</button>
                <button className="btn btn-danger" onClick={() => handleDelete(category.id)}>Delete</button>
              </div>
            </div>
          </div>
        ))}
      </div>
      <ToastContainer />
      <button
        className="btn btn-primary position-fixed bottom-0 end-0 m-3 rounded-circle"
        style={{ width: '60px', height: '60px', padding: '0', display: 'flex', alignItems: 'center', justifyContent: 'center' }}
        onClick={handleAddCategory}
      >
        <span className="fs-3 text-white">+</span>
      </button>
      <footer className="text-center mt-4 p-3 bg-white rounded shadow">
        <p className="mb-0">&copy; 2024 My Blog. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default Categories;
