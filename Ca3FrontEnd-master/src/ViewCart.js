import React from 'react';
import cart from './shoppingCart';
import facade from './apiFacade';

const DisplayCart = () => {
    return cart.isEmpty() ? (
        <div>
            <p>Your cart is empty.</p>
        </div>
    ) : (
    <div>
        <ul className="list-group">
            {cart.getCart().map((item) =>
                <li key={item.id} className="list-group-item">{item.weight}g {item.name}</li>
            )}
        </ul>
    </div>
        )
}
const checkOut = () => {
    facade.postOrder(cart.getCart());
}
const CartComponent = (props) => {
    return <div className="container" id="white">
        <h2>Shopping Cart</h2>
        <DisplayCart></DisplayCart>
            <div className="row">
                <div className="col-md-10">
                    <div></div>
                </div>
                <div className="col-md-2">
                    <p className="text-success">Total Weight: {cart.getWeight()}g</p>
                    <p className="text-success">Total Price: {cart.getPrice()}kr.</p>
                </div>
            </div>
            <div className="row">
                <div className="col-md-10">
                    <button onClick={props.emptyCart} className="btn btn-secondary">Empty Cart</button>
                </div>
                <div className="col-md-2">
                    <button onClick={checkOut} className="btn btn-primary">Checkout</button>
                </div>
            </div>
    </div>

}
export default CartComponent;