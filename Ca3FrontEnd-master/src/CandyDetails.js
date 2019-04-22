import React, { Component } from 'react';
import facade from './apiFacade';
import { Link } from "react-router-dom";
import cart from './shoppingCart';
import { ShowError, ShowNeutral, ShowSuccess } from './ErrorMessage';
import UserInput from './UserInput';

class CandyDetails extends Component {
    constructor(props) {
        super(props);
        this.state = { weight: 0, candy: [], clicked: false }
    }

    onChange = (evt) => {
        this.setState({ [evt.target.id]: evt.target.value })
        this.setState({clicked: false});
    }

    async componentDidMount() {
        const data = await facade.fetchCandyDetails(this.props.match.params.id);
        this.setState({ candy: data });
    }

    feedBack = () => {
        if (this.state.weight > 0 && this.state.clicked === true) {
            return (<ShowSuccess message="Item added to cart" />)
        }
        if(this.state.weight <= 0 && this.state.clicked == true) {
            return (<ShowError message="Failed to add to cart"></ShowError>)
        }
    }

    addToCart = () => {
        if(UserInput.validateInput(this.state.weight) === true){
            cart.addToCart2(this.state.candy.id, this.state.candy.name, this.state.weight);
            this.props.updateCart();
        }
        this.setState({clicked: true});
    }
    render() {
        return (
            <div className="container" id="white">
                <h2>Item Details</h2>
                <p>ID: {this.state.candy.id}</p>
                <p>Name: {this.state.candy.name}</p>
                <p>Description: {this.state.candy.description}</p>
                <p>Type: {this.state.candy.type}</p>
                <p>Flavour: {this.state.candy.flavour}</p>
                <p>IMG: {this.state.candy.img}</p>
                <form onChange={this.onChange} className="form-inline">
                    <div className="input-group">
                        <input placeholder="Weight" id="weight" type="number" className="form-control"/>
                        <div className="input-group-append">
                            <button id="btn" onClick={this.addToCart} className="btn btn-primary">Add To Cart</button>
                        </div>
                    </div>
                </form>
                {this.feedBack()}
                <Link to="/candy">Back</Link>
            </div>)
    }
}
export default CandyDetails;