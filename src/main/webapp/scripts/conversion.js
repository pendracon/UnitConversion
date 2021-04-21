/* 
 * Copyright (c) 2021 a.cook@veetechis.com.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    a.cook - initial API and implementation and/or initial documentation
 */
/*
 * Professor Frink's basic form logic.
 */
// Results visibility state flag
var results_visible = false;

/*
 * Check entered data for invalid characters.
 */
function isValidInput( data ) {
    var invalidChars = "~`!@#$%^&*?+=:;,()<>{}[]|/\"\'\\";
    var isValid = true;
    
    var dchar = "";
    for (var i = 0; i < data.length; i++ ) {
        dchar = data.charAt(i);
        if (invalidChars.indexOf(dchar) > -1 ) {
            isValid = false;
            break;
        }
    }
    
    return isValid;
}

/*
 * Check form data for minimum required entries.
 */
function isFormValid( data ) {
    var isValid = true;
    var errorMesg = "Missing input:\n\n";
    
    if (data['fromType'] === "") {
        errorMesg += "'From' conversion type not entered.\n";
        isValid = false;
    }
    if (data['toType'] === "") {
        errorMesg += "'To' conversion type not entered.\n";
        isValid = false;
    }
    if (data['units'] === "") {
        errorMesg += "'Amount' to convert not entered.\n";
        isValid = false;
    }

    if (!isValid) {
        alert( errorMesg );
    }
    
    return isValid;
}

/*
 * Validate the input and do the conversion.
 */
function getConversion() {
    var formElements = document.forms['conversionForm'].elements;
    var sendIt = true;

    var convData = {};
    for (var i = 0; i < formElements.length; i++) {
        if (!isValidInput(formElements[i].value)) {
            alert( "Bad input: " + formElements[i].value );
            formElements[i].value = "";
            sendIt = false;
            break;
        }
        else {
            var ival = "";
            var ivalues = formElements[i].value.trim().split(" ");
            if (ivalues.length > 1) {
                for (var j = 0; j < ivalues.length; j++) {
                    if (ivalues[j] !== "") {
                        if (j > 0) ival += "_";
                        ival += ivalues[j];
                    }
                }
            }
            else {
                ival = ivalues[0];
            }
            convData[formElements[i].name] = ival;
        }
    }

    if (sendIt) {
        if (isFormValid(convData)) {
            submitForm( convData );
        }
        else {
            sendIt = false;
        }
    }
    
    return sendIt;
}

/*
 * Send the form data to the conversion service.
 */
function submitForm( data ) {
    var uri = "/UnitConversion/service/validate?";
    if (data['fromType'] !== "") uri += "convertFrom=" + data['fromType'] + "&";
    if (data['toType'] !== "") uri += "convertTo=" + data['toType'] + "&";
    if (data['units'] !== "") uri += "unitValue=" + data['units'] + "&";
    if (data['validation'] !== "") uri += "validateValue=" + data['validation'];

    var http = new XMLHttpRequest();
    http.open( "GET", uri, true );
    http.send();
    http.onload = function() {
        if ((http.status >= 200) && (http.status < 300)) {
            parseResults( http.responseText );
        }
        else {
            alert( "Error in service! Site returned status " + http.status + "." );
        }
    };
}

/*
 * Reset the form.
 */
function clearForm() {
    document.forms['conversionForm'].reset();
}

/*
 * Parse the conversion service results.
 */
function parseResults( results ) {
    var robj = JSON.parse( results );
    
    var rmsg = "";
    var vmsg = "";
    if (robj['outputValue'] === "NONE") {
        rmsg = "Incorrect: Can not convert " + robj['inputValue'] + " between " + robj['inputType'] +
                " and " + robj['outputType'] + "!";
    }
    else {
        rmsg = "Result: " + robj['inputValue'] + "/" + robj['inputType'] +
            " is equal to " + robj['outputValue'] + "/" + robj['outputType'];
    }
    
    if (robj['validationValue'] !== "NONE") {
        vmsg = "Validation: " + robj['validationValue'] + " is " + robj['validation'] + "!";
    }
    
    document.getElementById("conversionVersion").textContent = robj['appVersion'];
    document.getElementById("conversionResults").textContent = rmsg;
    document.getElementById("validationResults").textContent = vmsg;
}

/*
 * Flip results visibility state.
 */
function hideOrShowResults() {
    if (results_visible) {
        clearForm();
        hideResults();
    }
    else {
        if (getConversion()) {
            results_visible = true;
            document.getElementById("userPrompt").textContent = "Clear form";
            document.getElementById("results").style.visibility = "visible";
        }
    }
}

/**
 * Hide results.
 */
function hideResults() {
    results_visible = false;
    document.getElementById("userPrompt").textContent = "Convert it";
    document.getElementById("results").style.visibility = "hidden";
}

/*
 * Just do it!
 */
function showResults() {
    hideOrShowResults();
}
