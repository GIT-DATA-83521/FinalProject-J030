import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import { toast } from 'react-toastify'; // Import Toastify

function EditPost() {
  const { id } = useParams(); // Get post ID from URL
  const [post, setPost] = useState({
    authorId: '',
    categoryId: '',
    title: '',
    description: '',
    content: ''
  });
  const navigate = useNavigate(); // Initialize navigate

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await axiosInstance.get(`/posts/${id}`);
        setPost(response.data);
      } catch (error) {
        console.error('Error fetching post:', error);
        toast.error('Failed to load post. Please try again.');
      }
    };

    fetchPost();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setPost({ ...post, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axiosInstance.put(`/posts/${id}`, post);
      toast.success('Post updated successfully!');
      navigate('/posts'); // Redirect to Posts page
    } catch (error) {
      console.error('Error updating post:', error);
      toast.error('Failed to update post. Please try again.');
    }
  };

  return (
    <div className="container my-4">
      <h2>Edit Post</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="id" className="form-label">Post ID</label>
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
          <label htmlFor="authorId" className="form-label">Author ID</label>
          <input
            type="text"
            id="authorId"
            name="authorId"
            className="form-control"
            value={post.authorId}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="categoryId" className="form-label">Category ID</label>
          <input
            type="text"
            id="categoryId"
            name="categoryId"
            className="form-control"
            value={post.categoryId}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="title" className="form-label">Title</label>
          <input
            type="text"
            id="title"
            name="title"
            className="form-control"
            value={post.title}
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
            value={post.description}
            onChange={handleChange}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="content" className="form-label">Content</label>
          <textarea
            id="content"
            name="content"
            className="form-control"
            value={post.content}
            onChange={handleChange}
            required
          />
        </div>
        <button type="submit" className="btn btn-primary">Update Post</button>
      </form>
    </div>
  );
}

export default EditPost;
