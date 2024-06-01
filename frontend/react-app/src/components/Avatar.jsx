import React, {useEffect, useState} from 'react';
import styles from '../css/Avatar.module.css';
import axios from "axios";
import authHeader from "../service/auth-header";




function Avatar() {
    const [avatarPath, setAvatarPath] = useState('/assets/image/profile_empty.png');
    const [username, setUsername] = useState('username');
    useEffect(() => {
        axios.get('http://localhost:8080/api/user/get', { headers: authHeader() })
            .then(response => {
                const imageUrl = `http://localhost:8080/api/user/images?path=${encodeURIComponent(response.data.photoPath.replace(/\\\\/g, '/'))}`;
                setAvatarPath(imageUrl);
                setUsername(response.data.username);
            })
            .catch(error => {
                console.error('There was an error fetching the user data!', error);
            });
    }, []);

    return (
        <div className={styles.avatar}>
            <img className={styles.avatarImage}  src={avatarPath} alt="avatar"/>
            <p id="username">{username}</p>
        </div>
    );
}

export default Avatar;
