import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

function Navbar() {
    return (
        <nav>
            <div className="Options">
                <a className="textHome" href="/dashboard">HOME</a>
                <a className="textCreate" href="/create">CREATE</a>
                <a className="textAccount" href="/account">ACCOUNT</a>
            </div>
        </nav>
    );
}

export default Navbar;