import React from 'react';
import '../styling/Login.css';
import Logo from '../loginIcon.png';

class Login extends React.Component{
    state={
        email:'',
        pwd:''
    }

    handleChange = (e) =>{
        const {name,value} = e.target
        this.setState({[name]:value})
    }

    handleSubmit = (e) =>{
        e.preventDefault()
        this.props.isLogin(true)
    }
    render(){
        return(
            <div className='div-login'>
{                <div className='div-login-logo'>
                    <img src={Logo} alt="login_image" />
                </div>}
                <div>
                    <form onSubmit = {this.handleSubmit}>
                        <input type='email' name='email' placeholder='email...' required onChange={this.handleChange}/>
                        <input type='password' name='pwd' placeholder='password...' required onChange={this.handleChange}/>
                        <button onSubmit={this.handleSubmit}>Login</button>
                    </form>
                </div>
            </div>
        )
    }
}

export default Login;