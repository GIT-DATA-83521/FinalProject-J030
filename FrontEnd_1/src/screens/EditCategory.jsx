import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import { toast } from 'react-toastify'; // Import Toastify

function EditCategory() {
  const { id } = useParams(); // Get category ID from URL
  const [category, setCategory] = useState({ name: '', description: '' });
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const fetchCategory = async () => {
      try {
        const response = await axiosInstance.get(`/categories/${id}`);
        setCategory(response.data);
      } catch (error) {
        console.error('Error fetching category:', error);
      }
    };

    fetchCategory();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setCategory({ ...category, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axiosInstance.put(`/categories/${id}`, category);
      toast.success('Category updated successfully!');
      navigate('/categories');
    } catch (error) {
      console.error('Error updating category:', error);
      toast.error('Failed to update category. Please try again.');
    }
  };

  return (
    <div className="container my-4">
      <h2>Edit Category</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="id" className="form-label">Category ID</label>
          <input
            type="text"
            id="id"
            name="id"
            className="form-control"
            value={id}
            readOnly
          />
        </div>
        <div className="mb-3">
          <label htmlFor="nacategoryNameme" className="form-label">Category Name</label>
          <input
            type="text"
            id="categoryName"
            name="categoryName"
            className="form-control"
            value={category.categoryName}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="description" className="form-label">Description</label>
          <textarea
            id="description"
            name="description"
            className="form-control"
            value={category.description}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Update Category</button>
      </form>
    </div>
  );
}

export default EditCategory;
