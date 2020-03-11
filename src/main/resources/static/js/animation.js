var images = ["img/hotel.jpg", "img/reception.jpg",
                "img/bedroom.jpg", "img/bath.jpg",
                "img/room-service.jpg", "img/canteen.jpg",
                "img/swimming-pool.jpg", "img/spa.jpg",
                "img/fitness.jpg"];

var imgCount = 0;

function startTime()
{
    if (imgCount === images.length)
    {
        imgCount = 0;
    }

    document.getElementById('indexImg').src = images[imgCount];
    ++imgCount;

    setTimeout('startTime()', 10000);
}