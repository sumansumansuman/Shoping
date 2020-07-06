import React,{useState} from 'react';
import style from '../css/nav.module.css'
import logo from '../static/images/logo.jpg'
import {Link, Redirect} from 'react-router-dom'
import { faUser,faShoppingBag,faDollyFlatbed,faSearch, faHeart } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import ProfileMenu  from './ProfileMenu'
import '../App.css'

function Nav() {

  const [isShown, setIsShown] = useState(false);
  const [isShown2, setIsShown2] = useState(true);
  
  const [item,setItem]=useState("")
  const submit=event=>{
    setItem(event.target[0].value)
//    console.log("event" +event.target[0].value)
    
  }
  


  return (
    <div className={style.main} >
      <nav className={style.nav}>
        <div className={style.navLogo}>
          <Link className={style.links} to="/">
           <img src={logo} height="40" widht="40"/>
          </Link> 
        </div>
        <div className={style.navItem}>
          <Link className={style.links} to="/Men">MEN</Link> 
        </div>
        <div className={style.navItem}>
          <Link className={style.links} to="/Women">WOMEN</Link> 
        </div>
        <div className={style.navItem}>
          <a className={style.links} href="/html/">KIDS</a> 
        </div>
        <div className={style.navItem}>
          <a className={style.links} href="/html/">HOME & LIVING</a> 
        </div>
        <div className={style.navItem}>
          <form className={style.navForm} onSubmit={submit} >
            <FontAwesomeIcon icon={faSearch}/>
            <input className={style.input} type="text" placeholder="   Search.."/>
          </form>
        </div>
       
        <div className={`${style.navItem} ${style.hov}`} 
        onMouseEnter={()=>{setIsShown(true)}}
        onMouseLeave={()=>{setIsShown(false)}}
        >
          <FontAwesomeIcon icon={faUser} /> 
         <h className={`${style.links} ${style.field1}`}style={{cursor:"pointer"}}     
          >  Profile</h> 
           {isShown&&<ProfileMenu />}
        </div>
        
        
        <div className={style.navItem}>
          <FontAwesomeIcon icon={faShoppingBag} />
          <Link className={style.links} to="/Cart">  Bag</Link> 
        </div>
        <div className={style.navItem}>
          <FontAwesomeIcon  icon={faHeart} />
          <Link  className={style.links} to="/Wishlist">  Wishlist</Link> 
        </div>
        
        {item&&<Redirect to={`/category/${item}`}/>}     

      </nav>
      
      
    </div>
       
  );
}

export default Nav;
