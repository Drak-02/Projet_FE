import { useState } from 'react'
import '../Styles/Authentification.css'


function Authentification(){
    //Variables
    const [password, setPassword] = useState('')
    const [nom, setNom] = useState('')
    const [dateNaissance, setDateNaissance] = useState('')
        
    //Traitement
        //C'est données seront envoyer au back-end
    const handlerSubmit = (event)=> {
        event.preventDefault();
        //Verifions ce les valeurs sont correctes 
        console.log('Données à soumises :', {nom, dateNaissance,password})
           
       
        //Cette partie permet de rénitialiser le champ de saisir après l'envoie
        setNom('')
        setDateNaissance('')
        setPassword('')
    }

    // Render
    return (<div className='lmj-contenaire'>
        <form onSubmit={handlerSubmit} className='lmj-formulaire' method='POST'>
        <fieldset>
                <legend>Authentification</legend>
                
                <div className='lmj-champ-item'>
                    <label htmlFor='nom'>Nom:</label><br/>
                    <input type='text' value={nom} 
                    onChange={(event)=> setNom(event.target.value)} 
                    required 
                    />
                </div>
                <div className='lmj-champ-item'>
                    <label htmlFor='dateNaissance'>Date Naissance:</label><br/>
                    <input type='date' 
                    value={dateNaissance}
                    onChange={ (event)=> setDateNaissance(event.target.value)}
                    required
                    />
                </div>
                <div className='lmj-champ-item'>
                    <label htmlFor='password'>Mot de passe:</label><br/>
                    <input type='password'
                    value={password} onChange={(event)=> setPassword(event.target.value)}
                    required
                    />
                </div><br/>
                <div className='lmj-champ-item'>
                    <button type='submit' className='lmj-button'>Se connecter</button>
                </div>
            </fieldset>
        </form>
    </div>)

}

export default Authentification