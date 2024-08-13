import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import 'bootstrap/dist/css/bootstrap.min.css';

function AddCategory() {
  const [newCategory, setNewCategory] = useState({ categoryName: '', description: '' });
  const navigate = useNavigate();

  const handleSaveCategory = async () => {
    try {
      // Log the current state for debugging
      console.log('Saving category:', newCategory);

      // Send the data to the server
      await axiosInstance.post('/categories/addcategory', newCategory);

      // Show success message
      toast.success('Category added successfully!');
      
      // Navigate to categories page
      navigate('/categories');
    } catch (error) {
      // Log the error and show error message
      console.error('Error adding category:', error);
      toast.error('Failed to add category. Please try again.');
    }
  };

  const handleClear = () => {
    // Reset the state
    setNewCategory({categoryName: '', description: '' });
  };

  return (
    <div className="container my-4">
      <h2 className="mb-4">Add New Category</h2>

      <div className="mb-3">
        <label htmlFor="categoryName" className="form-label">Category Name</label>
        <input
          type="text"
          className="form-control"
          id="categoryName"
          value={newCategory.categoryName}
          onChange={(e) => {
            // Log input change for debugging
            console.log('Category Name changed:', e.target.value);
            setNewCategory({ ...newCategory, categoryName: e.target.value });
          }}
        />
      </div>

      <div className="mb-3">
        <label htmlFor="categoryDescription" className="form-label">Category Description</label>
        <textarea
          className="form-control"
          id="categoryDescription"
          rows="3"
          value={newCategory.description}
          onChange={(e) => {
            // Log input change for debugging
            console.log('Category Description changed:', e.target.value);
            setNewCategory({ ...newCategory, description: e.target.value });
          }}
        ></textarea>
      </div>

      <div className="d-flex gap-2">
        <button className="btn btn-success" onClick={handleSaveCategory}>Save Category</button>
        <button className="btn btn-secondary" onClick={handleClear}>Clear</button>
      </div>

      <ToastContainer />
    </div>
  );
}

export default AddCategory;
