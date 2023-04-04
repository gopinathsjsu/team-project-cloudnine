import { useContext, useEffect } from 'react'; 

import AppContext from 'context';

import logo from 'assets/logo.svg';
import './Admin.css';

function Admin() {
  const { context, setContext } = useContext(AppContext);

  useEffect(() => {
    setContext({
      ...context,
      path: ['Admin']
    });
  }, []);

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          I'm Admin.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default Admin;
