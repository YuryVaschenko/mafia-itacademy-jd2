/**
 * Created by Yury V. on 17.07.17.
 */
function get_coords() {
    var address = document.getElementById('address').value;
    ymaps.geocode(address, {results: 1}).then(function (res) {
            var firstGeoObject = res.geoObjects.get(0);
            var cords = firstGeoObject.geometry.getCoordinates();

            document.getElementById("longitude").value = cords[0];
            document.getElementById("latitude").value = cords[1];
        },
        function (err) {
            alert(err.message);
        })
}