document.addEventListener("DOMContentLoaded", function () {
    const searchForm = document.getElementById("searchTrainsForm");
    
    if (searchForm) {
        searchForm.addEventListener("submit", function (event) {
            event.preventDefault(); // Prevent default form submission
            
            const source = document.getElementById("source").value;
            const destination = document.getElementById("destination").value;
            
            if (!source || !destination) {
                alert("Please enter both source and destination.");
                return;
            }

            fetch(`/trains/search?source=${source}&destination=${destination}`)
                .then(response => response.json())
                .then(data => {
                    const trainList = document.getElementById("trainList");
                    trainList.innerHTML = "";
                    
                    if (data.length === 0) {
                        trainList.innerHTML = "<tr><td colspan='5' class='text-center'>No Trains Available</td></tr>";
                        return;
                    }

                    data.forEach(train => {
                        trainList.innerHTML += `
                            <tr>
                                <td>${train.trainNumber}</td>
                                <td>${train.trainName}</td>
                                <td>${train.source}</td>
                                <td>${train.destination}</td>
                                <td>${train.seatsAvailable}</td>
                            </tr>
                        `;
                    });
                })
                .catch(error => console.error("Error fetching train data:", error));
        });
    }
});
