import { Router, RouterProvider } from "react-router-dom";

import router from 'router'

import Appbar from 'components/Appbar/Appbar'

import logo from 'assets/logo.svg';
import './App.css';

function App() {
  return (
    <div>
      <RouterProvider router={router}>
      </RouterProvider>
    </div>
  );
}

export default App;
