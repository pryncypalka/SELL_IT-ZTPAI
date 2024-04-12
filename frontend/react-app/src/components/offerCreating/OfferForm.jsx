import PropTypes from 'prop-types';
import ComboBox from './ComboBox.jsx';
import PhotosPreview from './PhotosPreview.jsx';
import PhotoUpload from './PhotoUpload.jsx';

function OfferForm({
                          messages,
                          userRole,
                          items,
                          title,
                          description,
                          price,
                          photos,
                          visibilityOptions,
                          itemOptions
                      }) {
    return (
        <form action="addOffer" method="post" encType="multipart/form-data">
            {messages.map((message, index) => (
                <div key={index} className="messages">{message}</div>
            ))}
            {userRole === 1 && items && (
                <>
                    <ComboBox
                        selectLabel="Visibility"
                        selectName="isPublic"
                        options={visibilityOptions}
                    />
                    <ComboBox
                        selectLabel="Select Item"
                        selectName="item"
                        options={itemOptions}
                    />
                </>
            )}
            <label htmlFor="title">Title:</label>
            <input type="text" id="title" name="title" placeholder="Title" required value={title} />

            <label htmlFor="description">Description:</label>
            <textarea id="description" name="description" rows="15" placeholder="Description" required>{description}</textarea>

            <label htmlFor="price">Price:</label>
            <input className="offer_price" type="number" id="price" name="price" step="0.01" placeholder="Price"  value={price} />

            <PhotoUpload />

            <PhotosPreview photos={photos} />

            <div className="button_container">
                <button type="submit" name="action" value="saveOffer">Save As Offer</button>
                <button type="submit" name="action" value="saveAsTemplate" className="secondary">Save as Template (Title and Description only)</button>
            </div>
        </form>
    );
}

OfferForm.propTypes = {
    messages: PropTypes.arrayOf(PropTypes.string),
    userRole: PropTypes.number,
    items: PropTypes.array,
    title: PropTypes.string,
    description: PropTypes.string,
    price: PropTypes.number,
    photos: PropTypes.arrayOf(PropTypes.string),
    visibilityOptions: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        label: PropTypes.string
    })),
    itemOptions: PropTypes.arrayOf(PropTypes.shape({
        value: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
        label: PropTypes.string
    })),
};

OfferForm.defaultProps = {
    messages: [],
    userRole: 0,
    items: [],
    title: '',
    description: '',
    price: 0,
    photos: [],
    visibilityOptions: [
        { value: '0', label: 'Private' },
        { value: '1', label: 'Public' }
    ],
    itemOptions: [],
};

export default OfferForm;