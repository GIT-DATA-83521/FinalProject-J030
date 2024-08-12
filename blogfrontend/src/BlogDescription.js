import React from 'react';
import { useParams } from 'react-router-dom';
import { Container, Row, Col, Card } from 'react-bootstrap';

const BlogDescription = () => {
    const { id } = useParams();

    const blogData = [
        {
            id: 1,
            title: "The Art of Blogging",
            author: "John Doe",
            excerpt: "Discover the secrets of successful blogging...",
            description: "Blogging is an art form that requires creativity, consistency, and a deep understanding of your audience. In this article, we'll explore the essential tips to make your blog stand out. It's a way to share your thoughts, opinions, and experiences with the world, and to connect with others who share your interests. Whether you're just starting out or you're a seasoned pro, the art of blogging is all about finding your voice, your style, and your message."
        },
        {
            id: 2,
            title: "SEO Tips for Bloggers",
            author: "Jane Smith",
            excerpt: "Learn the best practices for optimizing your blog posts for search engines...",
            description: "SEO is crucial for driving traffic to your blog. In this post, we'll delve into the top strategies to optimize your content and improve your blog's visibility on search engines.Blogging helps boost SEO quality by positioning your website as a relevant answer to your customers' questions. Blog posts that use a variety of on-page SEO tactics can give you more opportunities to rank in search engines and make your site more appealing to visitors."
        },
        {
            id: 3,
            title: "Content Creation Strategies",
            author: "Alice Johnson",
            excerpt: "Content is king. Find out how to create compelling content that resonates with your readers...",
            description: "Creating engaging content is key to maintaining a successful blog. This article covers various strategies to generate ideas, write captivating posts, and keep your readers coming back for more.A content strategy is the planning, creation, publication, management, and governance of content. A great content strategy will attract and engage a target audience, meeting their needs while driving business goals."
        }
    ];

    const blog = blogData.find((b) => b.id === parseInt(id));

    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col md="8">
                    <Card className="my-4">
                        <Card.Body>
                            <Card.Title>{blog.title}</Card.Title>
                            <Card.Subtitle className="mb-2 text-muted">By {blog.author}</Card.Subtitle>
                            <Card.Text>{blog.description}</Card.Text>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
};

export default BlogDescription;
