
function PhotoUpload() {
    return (
        <div className="photo_upload_container">
            <label className="photos_label" >Photos:</label>
            <input type="file" id="photo" name="photo[]" accept="image/*" multiple/>
        </div>
    );
}

export default PhotoUpload;