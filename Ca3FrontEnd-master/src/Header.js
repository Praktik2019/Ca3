import React from 'react';
import { Link } from "react-router-dom";
import LogIn from "./Login";
import cart from './shoppingCart';

const imgstyle = {
  hight: '60px',
  width: '60px'
};

const Header = (props) => {
  return (
    !props.loggedIn ? (
      <nav className="navbar navbar-expand-sm bg-dark navbar-gray rounded-bottom">
        <ul className="navbar-nav">
          <li className="nav-item">
            <Link className="nav-link" to="/">Home</Link>
          </li>
        </ul><LogIn login={props.login} />
      </nav>
    ) : (
        <nav className="navbar navbar-expand-sm bg-dark navbar-gray rounded-bottom">
        
        <img src={require('./slik.jpg')} style={imgstyle}/>
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/">Home</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/user">User</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/candy">View Candy</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/cart">Cart <span className="badge badge-success">{props.weight}g</span></Link>
            </li>
          </ul>
          <button onClick={props.logout} className="btn btn-primary ml-auto">Logout</button>
        </nav>
      )
  );
}
export default Header;