import { createBrowserRouter } from "react-router-dom";

import Appbar from '../components/Appbar/Appbar';
import Home from 'pages/Home/Home';
import Profile from 'pages/Profile/Profile';
import Admin from 'pages/Admin/Admin';
import User from 'pages/User/User';

const router = createBrowserRouter([
  {
    path: "/",
    element: <Appbar />,
    children: [
      {
        path: "/home",
        element: <Home />,
      },
      {
        path: "/profile",
        element: <Profile />,
      },
      {
        path: "/admin",
        element: <Admin />,
      },
      {
        path: "/user",
        element: <User />,
      },
    ]
  },
]);

export default router;