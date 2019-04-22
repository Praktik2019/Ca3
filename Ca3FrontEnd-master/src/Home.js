import React from "react";

const homeStyle = {
    textAlign: 'center',
    background: 'white'
};
const Home = () => {
    return  <div className="container rounded" style={homeStyle}><h2>Velkommen til Slikgrotten!</h2>
        <p>Slikgrotten er en del af danmarks kommende store kæde af slik buttikker!</p>
        <p>Vi glæder os til at blive en del af alle danskeres hygge.</p>
        <img src={require('./slik.jpg')}/>
    </div>;
}


export default Home;