"use strict";

var db = require('../utils/database.js');

var findAll = function(callback) {
	var sql = "select * from products";

	db.query(sql, callback);
};

var findById = function(productId, callback) {
	var sql = "select * from products where productId = " + productId;

	db.query(sql, callback);
};

exports.findAll = findAll;
exports.findById = findById;
