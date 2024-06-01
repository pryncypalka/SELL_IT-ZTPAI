import PropTypes from 'prop-types';
import styles from '../css/CategoryTile.module.css';

function CategoryTile({ category, onClick }) {
    return (
        <div className={styles.category_tile} onClick={onClick}>
            <div className={styles.category_name}>{category}</div>
        </div>
    );
}

CategoryTile.propTypes = {
    category: PropTypes.string,
    onClick: PropTypes.func,
};

CategoryTile.defaultProps = {
    category: 'Default Category',
    onClick: () => {},
};

export default CategoryTile;