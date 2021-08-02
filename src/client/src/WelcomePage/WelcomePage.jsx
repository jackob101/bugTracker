import React, { Component } from 'react';

class WelcomePage extends Component {
    state={
        message: []
    }

    componentDidMount(){
        let axios = require('axios')
        axios.get("/user/all")
            .then(response => {
                this.setState({message: response.data})
            })

    }

    render() { 
        return ( 
            <div>
            <h1>Hello</h1>
            {this.state.message.map(object => <h1 key={object}>{object.name + " " + object.lastName}</h1>)}
            </div>
         );
    }
}
 
export default WelcomePage;