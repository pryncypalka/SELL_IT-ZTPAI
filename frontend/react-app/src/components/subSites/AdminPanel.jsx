import React, { useEffect, useState } from 'react';
import axios from 'axios';
import styles from '../../css/sub_sites_css/AdminPanel.module.css';

const AdminPanel = () => {
    const [users, setUsers] = useState([]);
    const [selectedUser, setSelectedUser] = useState(null);

    useEffect(() => {
        axios.get('http://localhost:8080/api/user/get-all')
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error('There was an error fetching the users!', error);
            });
    }, []);

    const handleUserClick = (user) => {
        setSelectedUser(user);
    };

    return (
        <div className={styles.adminPanel}>
            <h1>Admin Panel</h1>
            <ul className={styles.userList}>
                {users.map(user => (
                    <li key={user.userId} onClick={() => handleUserClick(user)} className={styles.userListItem}>
                        {user.username}
                    </li>
                ))}
            </ul>
            {selectedUser && (
                <div className={styles.userDetails}>
                    <h2>User Details</h2>
                    <p>Email: {selectedUser.email}</p>
                    <p>Username: {selectedUser.username}</p>
                </div>
            )}
        </div>
    );
};

export default AdminPanel;