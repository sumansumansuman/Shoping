import React,{useState,useEffect} from 'react'
import {useAuth} from './auth'
import axios from 'axios'
import style from '../css/login.module.css'


function Adress(){
    const [adress,setAdress]=useState()
    const {authTokens}=useAuth()
    useEffect(()=>{
       axios.get(`http://localhost:8080/user/logged/address?auth_token=${authTokens}`)
             .then((result)=>{
                 console.log(result)
                 if(result.data.lenght>0)
                 setAdress(result.data)
             })
             .catch((error)=>{
                 console.log("error in getting adress")
             })
    },[])


    const change=()=>{

    }
    const submit=()=>{

    }
    
    const isError=false;

    return(
       <div>
           {adress&&adress.map(ad=>{
             return <h1>ad.name</h1>
           })

           }

           {!adress&&
                    <div className={style.container}  >
                    <form className={style.form} onSubmit={submit} >
                    <p className={style.text}>Add Adress</p>
                    {isError&&<span className={style.error}>{isError}</span>}
                    <input required placeholder='Name' className={style.input} type="text"   name="name"
                     onChange={change} />
                    <input required placeholder='Mobile' className={style.input} type="email"   name="email"
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
                     </form>
                 </div>
           }
           
       </div>

    )
}

export default Adress;