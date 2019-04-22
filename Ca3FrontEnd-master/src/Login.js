import React, { Component } from 'react';
import { ShowError, ShowNeutral, ShowSuccess } from './ErrorMessage';

class LogIn extends Component {
    constructor(props) {
      super(props);
      this.state = { username: "", password: "", clicked: false }
    }
    login = (evt) => {
      evt.preventDefault();
      this.props.login(this.state.username, this.state.password);
    }
    onChange = (evt) => {
      this.setState({ [evt.target.id]: evt.target.value })
      this.setState({clicked: false})
    }
    onClick =()=>{
      this.setState({clicked: true})
    }
    feedBack(){
      if (this.state.username != "" && this.state.clicked === true) {
        return (<ShowSuccess message="Logged in" />)
    }
    if(this.state.username === "" && this.state.clicked === true) {
        return (<ShowError message="Failed to Login"></ShowError>)
    }
    }
    render() {
      return (
        <div className="ml-auto">
          <form onSubmit={this.login} onChange={this.onChange} className="form-inline">
            <div className="input-group ">
              <input placeholder="User Name" id="username" className="form-control" />
              <input type="password" placeholder="Password" id="password" className="form-control"/>
              <div className="input-group-append">
                <button onClick={this.onClick} className="btn btn-primary">Login</button>
              </div>
            </div>
          </form>
          {this.feedBack()}
        </div>
      )
    }
}
export default LogIn;