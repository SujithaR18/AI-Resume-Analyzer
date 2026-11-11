const uploadBtn = document.getElementById("uploadBtn");

const inputFile = document.getElementById("inputFile")

const form = document.querySelector("#inputForm");

const jobDesc = document.getElementById("jobDescriptionText")

let debounceTimer;

let isSubmitting = false

console.log("Hiiii I'm working");


// Browse button opens the file picker:
uploadBtn.addEventListener("click", () => {
    inputFile.click()
})

// File Selected
inputFile.addEventListener("change", () => {

    trySubmit()
})

// User Typing:
jobDesc.addEventListener("input", () => {

    clearTimeout(debounceTimer)

    debounceTimer = setTimeout(() => {

        trySubmit()
    }, 1000)
})

// Function to submit only, when both the fields are ready:
function trySubmit() {

    const fileSelected = inputFile.files.length > 0;

    const descriptionEntered = jobDesc.value.trim() !== "";

    if (isSubmitting) {
        return;
    }

    if (fileSelected && descriptionEntered) {

        isSubmitting = true
        form.submit()
    }

}

//? ONLY FOR THE BUTTON TO ACCESS THE INPUT TAG TO UPLOAD.
// uploadBtn.addEventListener("click", () => {
//     inputFile.click()
// });

// inputFile.addEventListener("change", () => {
//     if (inputFile.files.length > 0) {
//         form.submit();
//     }
// });
