import PropTypes from 'prop-types';
import styles from '../css/CategoryTile.module.css';
function CategoryTile({ category, imagePath }) {
    return (
        <div className={styles.category_tile} >
            <img className={styles.category_image} src={imagePath} alt="category_image"/>
            <div className={styles.category_name}>{category}</div>
        </div>
    );
}

CategoryTile.propTypes = {
    category: PropTypes.string,
    imagePath: PropTypes.string,
};

CategoryTile.defaultProps = {
    category: 'Default Category',
    imagePath: '/assets/image/profile_empty.png',
};

export default CategoryTile;


