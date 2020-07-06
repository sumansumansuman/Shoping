import React,{useState,useEffect} from 'react'
import logo from './logo.svg'
import './App.css'
import Nav from './component/Nav'
import Men from './component/Men'
import Women from './component/Women'
import Cart from './component/Cart'
import Home from './component/Home'
import Login from './component/Login'
import Order from './component/Order'
import Wishlist from './component/Wishlist'
import Profile from './component/Profile'
import SignUp from './component/SignUp'
import Adress from './component/Adress'
import category from './component/Category'
import product from './component/Product'
import PrivateRoute from './component/PrivateRoute'
import {BrowserRouter as Router,Switch,Route,Link} from 'react-router-dom';
import {AuthContext} from './component/auth';
function App() {
  
  const existingTokens= JSON.parse(sessionStorage.getItem("tokens"));
  const [logData, setlogData] = useState(existingTokens);
  const setTokens = (data) => {
    if(data)
     sessionStorage.setItem("tokens", JSON.stringify(data));
    setlogData(data);
  }
  

  let authTokens;
  if(logData)authTokens=logData.token


  return (
     <AuthContext.Provider value={{logData, authTokens, setAuthTokens: setTokens }}>
       <Router>
         <div className="App">
          <Nav/>
          <div className="div-space"/>          
          <Switch>
            <Route path="/" exact component={Home}/>
            <Route path="/men" exact component={Men}/>
            <Route path="/women" exact component={Women}/>
            <PrivateRoute path="/profile" exact component={Profile}/>
            <PrivateRoute path="/wishlist" exact component={Wishlist}/>
            <PrivateRoute path="/cart" exact component={Cart}/>
            <PrivateRoute path="/order" exact component={Order}/>
            <PrivateRoute path="/adress" exact component={Adress}/>
            <Route path="/login" exact component={Login}/>
            <Route path="/register" exact component={SignUp}/>
            <Route path="/category/:name" exact component={category}/>  
            <Route path="/product/:id" exact component={product}/>      
          </Switch>       
         </div>
        </Router>
        </AuthContext.Provider>
  );
}

export default App;
