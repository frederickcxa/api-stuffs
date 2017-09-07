"use strict";

var fromSelect = function (error, result) {
	var response = {};

	if (error) {
		response = {
			code: 500,
			message: "Could not fetch the data",
			error: error
		};
	} else {
		response = {
			code: 200,
			message: 'Retrieved ' + result.length + ' row(s)',
			data: result
		};
	}

	return response;
};

exports.fromSelect = fromSelect;
