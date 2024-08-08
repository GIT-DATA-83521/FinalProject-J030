import React from 'react';
import { Container, Row, Col, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <Container>
            <Row className="justify-content-md-center">
                <Col md="8">
                    <h2 className="text-center my-4">Welcome to the Blog Management System</h2>
                    <p className="text-center">Manage your blogs efficiently with our system.</p>
                    <div className="text-center">
                        <Link to="/signup">
                            <Button variant="primary" className="mx-2">Signup</Button>
                        </Link>
                        <Link to="/login">
                            <Button variant="secondary" className="mx-2">Login</Button>
                        </Link>
                    </div>
                </Col>
            </Row>
        </Container>
    );
};

export default Home;
