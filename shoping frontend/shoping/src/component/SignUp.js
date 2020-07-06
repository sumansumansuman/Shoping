import React,{useState} from 'react';
import {Redirect} from 'react-router-dom'
import axios from 'axios';
import {useAuth} from './auth'
import style from '../css/login.module.css'
import {Link} from 'react-router-dom'

function SignUp(props){
   
   let referer =  '/login';
   const[isError,SetError]=useState()
   const[success,setSuccess]=useState(false)
   const {authTokens}=useAuth()
   const [user,setUser]=useState({})
  
   const submit=event=>{
      event.preventDefault()
      console.log("on submit in signUp called")
      axios.post("http://localhost:8080/user/auth/signUp",user)
      .then(result=>{
         setSuccess(true)
         console.log("success")
      }).catch(error=>{
         console.log("error")
         console.log(error)
         console.log("response")
         console.log(error.response)
         if(error.response){
            SetError(error.response.data.message)
         }
         else{
            SetError("Something Went wrong!!!Please try again")
         }

      })
      
   }

   console.log("user")
   console.log(user)

  const change=event=>{
       event.persist()
        setUser(prevState=>({ ...prevState,[event.target.name]:event.target.value}))
   }

   if(authTokens){
      return <Redirect to="/" />
   }
   
   if(success){ 
    return <Redirect to={{
         pathname: '/order',
         state2: { success:"Account created Successfully ! Login to Continue" }
     }}/>
   }

   return(
      
      <div className={style.container}  >
         <form className={style.form} onSubmit={submit} >
         <p className={style.text}>Create Your Account</p>
         {isError&&<span className={style.error}>{isError}</span>}
         <input required placeholder='Name' className={style.input} type="text"   name="name"
          onChange={change} />
         <input required placeholder='Email or Username' className={style.input} type="email"   name="email"
          onChange={change} />
           <input required placeholder='Enter your Mobile No.' type="text" className={style.input}  name="mobile"
          onChange={change} />
          <input required placeholder='Enter a password' className={style.input} type="text"   name="password"
          onChange={change} />
          <input className={style.radio} type="radio" id="male" name="gender" value="Men"
           onChange={change}/>
            <label className={style.label} for="male">Male</label>
            <input className={style.radio} type="radio" id="female" name="gender" value="Women"
             onChange={change}/>
            <label className={style.label} for="female">Female</label><br></br>
          <button className={style.Login} type="submit">SignUp</button>
          <p className={style.signup}>Already have an account? <Link className={style.Link} to="/login">Login</Link></p>
          </form>
      </div>
   );

   
}

export default SignUp;