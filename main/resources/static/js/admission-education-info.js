document.addEventListener("DOMContentLoaded", function() {
    function showFields() {
        var degree = document.getElementById("degree").value;
        var streamsField = document.getElementById("streams-field");
        var courseField = document.getElementById("course-field");

        // Clear stream and course options
        var streamsSelect = document.getElementById("streams");
        streamsSelect.innerHTML = "<option value=''>Select a Stream</option>";

        var courseSelect = document.getElementById("course");
        courseSelect.innerHTML = "<option value=''>Select a Course</option>";

        streamsField.style.display = "none";
        courseField.style.display = "none";

        if (degree === "UG") {
            streamsField.style.display = "block";
            addOptions("streams", ["B.Tech", "B.Ed"]);
        } else if (degree === "PG") {
            streamsField.style.display = "block";
            addOptions("streams", ["M.Tech", "M.Ed"]);
        }

        document.getElementById("streams").onchange = function() {
            var stream = document.getElementById("streams").value;
            courseField.style.display = "none";

            if (stream) {
                courseField.style.display = "block";
                if (stream === "B.Tech") {
                    addOptions("course", ["CS", "IT"]);
                } else if (stream === "B.Ed") {
                    addOptions("course", ["Education Management", "Curriculum Studies"]);
                } else if (stream === "M.Tech") {
                    addOptions("course", ["Mechanical Engineering", "Civil Engineering"]);
                } else if (stream === "M.Ed") {
                    addOptions("course", ["Educational Leadership", "Educational Psychology"]);
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
