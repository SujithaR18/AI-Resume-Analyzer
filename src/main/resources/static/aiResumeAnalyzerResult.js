
/*
 * ==========================================
 * SPEEDOMETER FUNCTION
 * ==========================================
 */

function updateSpeedometer() {

//    let percentage =
//        parseInt(
            // document.getElementById("scoreInput").value
//
            //? this is Project input
//            jobMatch.matchPercentage

            // 89

//        );



	
	let percentage = parseInt(window.jobMatchPercentage);

	console.log("Percentage:", percentage);


    /*
     * Keep value between 0 and 100
     */

    if (percentage < 0) {
        percentage = 0;
    }

    if (percentage > 100) {
        percentage = 100;
    }


    /*
     * Update percentage text
     */

    document.getElementById("score").textContent =
        percentage;


    /*
     * Convert percentage to angle
     *
     * 0%   = 180 degrees
     * 50%  = 270 degrees
     * 100% = 360 degrees
     *
     * Since CSS rotation starts at 0 degrees
     * on the right side:
     *
     * 0%   = -180deg
     * 50%  = -90deg
     * 100% = 0deg
     */

    let angle =
        -180 + (percentage * 1.8);


    /*
     * Move needle
     */

    document.getElementById(
        "needleContainer"
    ).style.transform =
        `translateY(-50%) rotate(${angle}deg)`;


    /*
     * Change result text
     */

    const resultText =
        document.getElementById("resultText");


    if (percentage >= 81 && percentage <= 100) {

        resultText.textContent =
            "Excellent Match";
        resultText.style.backgroundColor = "green"

    }
    else if (percentage >= 61 && percentage <= 80) {

        resultText.textContent =
            "Good Match";
        resultText.style.backgroundColor = "green"

    }
    else if (percentage <= 60 && percentage >= 30) {

        resultText.textContent =
            "Average Match";
        resultText.style.backgroundColor = "orange"

    }
    else {

        resultText.textContent =
            "Poor Match";
        resultText.style.backgroundColor = "red"
    }

}


/*
 * ==========================================
 * INITIAL SCORE
 * ==========================================
 */

updateSpeedometer();
