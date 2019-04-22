import React, { Component } from "react";
import facade from "./apiFacade";

class ViewUser extends Component {
    constructor(props) {
      super(props);
      this.state = { dataFromServer: "Fetching!!" };
    }
    componentDidMount() {
      facade.fetchData().then(res => this.setState({ dataFromServer: res }));
    }
    render() {
      return (
        <div className="container" id="white">
          <h2>Update User</h2>
          <h3>{this.state.dataFromServer}</h3>
          <form>
            <div className="form-group">
              <label>Username</label>
              <input placeholder="User Name" id="username" className="form-control"/>
            </div>
            <div className="form-group">
              <label>Password</label>
              <input type="password" placeholder="Password" id="password" className="form-control"/>
            </div>
            <div className="form-group">
              <button className="btn btn-primary">Update</button>
            </div>
          </form>
        </div>
      )
    }
}

export default ViewUser;