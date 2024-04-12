import PropTypes from 'prop-types';

function CategoryTile({ category, imagePath }) {
    return (
        <div className="category_tile" >
            <img className="category_image" src={imagePath} alt="category_image"/>
            <div className="category_name">{category}</div>
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


