document.addEventListener("DOMContentLoaded", function() {
    function showFields() {
        var degree = document.getElementById("degree").value;
        var courseField = document.getElementById("course-field");
        var streamsField = document.getElementById("streams-field");

        // Clear the course dropdown and add placeholder
        var courseSelect = document.getElementById("course");
        courseSelect.innerHTML = "<option value=''>Select a Course</option>";

        // Hide the streams field initially
        streamsField.style.display = "none";

        if (degree === "undergraduate") {
            courseField.style.display = "block";
            addOptions("course", ["B.Tech", "B.Ed"]);
        } else if (degree === "postgraduate") {
            courseField.style.display = "block";
            addOptions("course", ["M.Tech", "M.Ed"]);
        } else {
            courseField.style.display = "none";
        }

        document.getElementById("course").onchange = function () {
            var course = document.getElementById("course").value;
            streamsField.style.display = "none";

            if (course) {
                streamsField.style.display = "block";
                if (course === "B.Tech") {
                    addOptions("streams", ["Computer Science", "Information Technology"]);
                } else if (course === "B.Ed") {
                    addOptions("streams", ["Education Management", "Curriculum Studies"]);
                } else if (course === "M.Tech") {
                    addOptions("streams", ["Mechanical Engineering", "Civil Engineering"]);
                } else if (course === "M.Ed") {
                    addOptions("streams", ["Educational Leadership", "Educational Psychology"]);
                }
            }
        };
    }

    function addOptions(elementId, options) {
        var select = document.getElementById(elementId);
        select.innerHTML = "<option value=''>Select an Option</option>"; // Add placeholder
        options.forEach(function(option) {
            var opt = document.createElement('option');
            opt.value = option;
            opt.innerHTML = option;
            select.appendChild(opt);
        });
    }

    // Initialize with the degree dropdown change event
    document.getElementById("degree").addEventListener("change", showFields);
});
