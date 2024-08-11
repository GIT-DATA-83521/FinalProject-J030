import React from 'react';
import { Container, Row, Col, Card } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home = () => {
    const blogData = [
        {
            id: 1,
            title: "The Art of Blogging",
            author: "John Doe",
            excerpt: "Discover the secrets of successful blogging...",
            description: "Blogging is an art form that requires creativity, consistency, and a deep understanding of your audience. In this article, we'll explore the essential tips to make your blog stand out..."
        },
        {
            id: 2,
            title: "SEO Tips for Bloggers",
            author: "Jane Smith",
            excerpt: "Learn the best practices for optimizing your blog posts for search engines...",
            description: "SEO is crucial for driving traffic to your blog. In this post, we'll delve into the top strategies to optimize your content and improve your blog's visibility on search engines..."
        },
        {
            id: 3,
            title: "Content Creation Strategies",
            author: "Alice Johnson",
            excerpt: "Content is king. Find out how to create compelling content that resonates with your readers...",
            description: "Creating engaging content is key to maintaining a successful blog. This article covers various strategies to generate ideas, write captivating posts, and keep your readers coming back for more..."
        }
    ];

    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col md="10">
                    <h2 className="text-center my-4">Latest Blogs</h2>
                    {blogData.map((blog) => (
                        <Card className="mb-4" key={blog.id}>
                            <Card.Body>
                                <Card.Title>{blog.title}</Card.Title>
                                <Card.Subtitle className="mb-2 text-muted">By {blog.author}</Card.Subtitle>
                                <Card.Text>{blog.excerpt}</Card.Text>
                                <Link to={`/blog/${blog.id}`} className="btn btn-primary">Read More</Link>
                            </Card.Body>
                        </Card>
                    ))}
                </Col>
            </Row>
        </Container>
    );
};

export default Home;
