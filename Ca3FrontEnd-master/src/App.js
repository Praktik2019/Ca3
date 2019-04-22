import React, { Component } from "react";
import facade from "./apiFacade";
import { HashRouter as Router, Route, Redirect } from "react-router-dom";
import ViewItems from './ViewItems.js';
import CandyDetails from "./CandyDetails";
import Header from "./Header";
import ViewUser from "./User";
import ViewCart from './ViewCart';
import Home from './Home';
import cart from './shoppingCart';

const PrivateRoute = ({ component: Component, updateCart, emptyCart, ...rest }) => (
  <Route {...rest} render={(props) => (
    facade.loggedIn() === true
      ? <Component {...props} updateCart={updateCart} emptyCart={emptyCart}/>
      : <Redirect to='/' />
  )} />
)

const appstyle= {
  background: 'gray'
}

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { loggedIn: false, cart: [], cartWeight: 0 }
  }
  logout = () => {
    facade.logout();
    this.setState({ loggedIn: false });
  }
  login = (user, pass) => {
    facade.login(user, pass)
      .then(res => this.setState({ loggedIn: true }));
  }
  componentDidMount() {
    this.setState({
      loggedIn: facade.loggedIn(),
      cart: cart.getCart(),
      cartWeight: cart.getWeight()
    });
  }
  updateCart = () => {
    this.setState({
      cart: cart.getCart(),
      cartWeight: cart.getWeight()
    });
  }
  emptyCart = () => {
    cart.emptyCart();
    this.updateCart();
  }
  render() {
    return (
      <Router>
        <div style={appstyle}>
        <Header loggedIn={this.state.loggedIn} logout={this.logout} login={this.login} weight={this.state.cartWeight}/>
          <Route exact path="/" component={Home} />
          <PrivateRoute exact path="/user" component={ViewUser} />
          <PrivateRoute exact path="/candy" component={ViewItems} updateCart={this.updateCart}/>
          <PrivateRoute path="/candy/:id" component={CandyDetails} updateCart={this.updateCart}/>
          <PrivateRoute path="/cart" component={ViewCart} updateCart={this.updateCart} emptyCart={this.emptyCart}/>
        </div>
      </Router>
    )
  }
}
export default App;
