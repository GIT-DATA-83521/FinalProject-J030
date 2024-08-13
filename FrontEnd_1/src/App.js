import { Route, Routes } from "react-router-dom";
import SignIn from './screens/SignIn'
import SignUp from './screens/SignUp'
import Home from "./screens/Home";
import Categories from "./screens/Categories";
import AddCategory from './screens/AddCategory';
import EditCategory from './screens/EditCategory';
import Posts from "./screens/Posts";
import AddPost from './screens/AddPost'
import EditPost from './screens/EditPost';;
import PostDetail from './screens/PostDetail';
import { ToastContainer } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';


function App() {
  return  (
    <div className="container"> 
    <Routes>
    <Route path="" element={<SignIn />} />
      <Route path="signin" element={<SignIn />} />
      <Route path="signup" element={<SignUp />} />
      <Route path="home" element={<Home />} />
      <Route path="categories" element={<Categories />} />
      <Route path="/add-category" element={<AddCategory />} />
      <Route path="/edit-category/:id" element={<EditCategory />} />
      <Route path="posts" element={<Posts />} />
      <Route path="/add-post" element={<AddPost />} />
      <Route path="/edit-post/:id" element={<EditPost />} />
      <Route path="/posts/:id" element={<PostDetail />} />

    </Routes>
    <ToastContainer />
  </div>
  )
  
}

export default App;
