import React, { useState, useEffect } from 'react';
import axios from 'axios';
import authHeader from '../service/auth-header';
import styles from '../css/ChangeAvatar.module.css';
import BlueButton from './BlueButton';

function ChangeAvatar() {
    const [message, setMessage] = useState(null);
    const [file, setFile] = useState(null);
    const [avatarPath, setAvatarPath] = useState('/assets/image/profile_empty.png');

    useEffect(() => {
        axios.get('http://localhost:8080/api/user/get', { headers: authHeader() })
            .then(response => {
                const imageUrl = `http://localhost:8080/api/user/images?path=${encodeURIComponent(response.data.photoPath.replace(/\\\\/g, '/'))}`;
                setAvatarPath(imageUrl);
            })
            .catch(error => {
                console.error('There was an error fetching the user data!', error);
            });
    }, []);

    const handleFileChange = (event) => {
        setFile(event.target.files[0]);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!file) {
            setMessage('No file selected');
            return;
        }

        const formData = new FormData();
        formData.append('file', file);

        try {
            const response = await axios.post('http://localhost:8080/api/user/update-image', formData, {
                headers: {
                    ...authHeader(),
                    'Content-Type': 'multipart/form-data',
                },
            });

            if (response.status === 200) {
                setMessage('Avatar updated successfully');
                setAvatarPath(URL.createObjectURL(file));
            } else {
                setMessage('Failed to update avatar: Server responded with status ' + response.status);
            }
        } catch (error) {
            setMessage('Failed to update avatar: ' + error.message);
        }
        }

    return (
        <form className={styles.formChangeAvatar} onSubmit={handleSubmit} encType="multipart/form-data">
            <p className={styles.changeAvatar}>Change avatar</p>

            <img className={styles.avatar_preview} src={avatarPath} alt="Avatar preview" />
            <input className={styles.inputFile} type="file" name="file" onChange={handleFileChange} /><br />
            <BlueButton text="Send" onClick={handleSubmit} />
            {message && <p className={styles.message}>{message}</p>}
        </form>
    );
}




export default ChangeAvatar;

