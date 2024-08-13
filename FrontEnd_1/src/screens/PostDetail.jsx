import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axiosInstance from '../axiosConfig';
import 'bootstrap/dist/css/bootstrap.min.css';

function PostDetail() {
  const { id } = useParams(); // Get the post ID from the URL
  const [post, setPost] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPostDetails = async () => {
      try {
        const response = await axiosInstance.get(`/posts/${id}`);
        setPost(response.data);
        setLoading(false);
      } catch (error) {
        console.error('Error fetching post details:', error);
        setLoading(false); // Even in case of error, stop the loading spinner
      }
    };

    fetchPostDetails();
  }, [id]);

  if (loading) {
    return <div>Loading...</div>; // Show a loading state while fetching data
  }

  if (!post) {
    return <div>Error loading post details</div>; // Handle the case where post is not found or an error occurs
  }

  return (
    <div className="container my-4">
      <header className="mb-4">
        <h1>{post.title}</h1>
        {post.category && (
          <>
            <p className="text-muted">Category: {post.category.name}</p>
            <p className="text-muted">{post.category.description}</p>
          </>
        )}
      </header>
      <article>
        <img src={post.image} alt={post.title} className="img-fluid mb-4" />
        <p>{post.content}</p>
      </article>
      <footer className="mt-4 p-3 bg-white rounded shadow">
        <p className="mb-0">&copy; 2024 My Blog. All rights reserved.</p>
      </footer>
    </div>
  );
}

export default PostDetail;
