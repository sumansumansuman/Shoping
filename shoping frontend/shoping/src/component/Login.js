import React,{useState} from 'react';
import {Redirect} from 'react-router-dom'
import axios from 'axios';
import {useAuth} from './auth'
import style from '../css/login.module.css'
import {Link} from 'react-router-dom'

function Login(props){
   let referer =  '/';
   if(props.location.state )referer=props.location.state.referer 
   const[isError,SetError]=useState()
   const {setAuthTokens,authTokens}=useAuth()
   const [isLogged,setIsLogged]=useState(false);
   const [username,setUsername]=useState("");
   const [password,setPassword]=useState("");
   
   const submit=event=>{
      event.preventDefault()
      console.log("login called")
      axios.post("http://localhost:8080/user/auth/login", {
         "email":username,
          "password":password
       }).then(result => {
         if (result.status === 200) {
            console.log(result.data)
           setAuthTokens(result.data)
           setIsLogged(true);
         }}).catch(error=>{
            if(error.response){
            console.log("error occured:"+error.response)
              SetError("Invalid username or password")
            }
            else if(error.request){
            console.log("request"+error.request)
                SetError("Server is Busy! please try Again")
            }

         }
      
         )
      
   }

   if(isLogged||authTokens){
      return <Redirect to={referer}/>
   }


   return(
      
      <div className={style.container}  >
         <form className={style.form} onSubmit={submit} >
         <p className={style.text}>Login or SignUp</p>
         {isError&&<span className={style.error}>{isError}</span>}
         {props.location.state2&&<span className={style.success}>{props.location.state2.success}</span>}
         <input required placeholder='Email or Username' className={style.input} type="text"   value={username}
          onChange={e => { setUsername(e.target.value)}}/>
           <input required placeholder='Enter your password' type="text" className={style.input}  value={password}
          onChange={e => { setPassword(e.target.value)}}/>
          <button className={style.Login} type="submit">LOGIN</button>
          <p className={style.signup}>new to the shoping? <Link className={style.Link} to="/register">SignUp</Link> now</p>
          </form>
      </div>
   );

   
}

export default Login;