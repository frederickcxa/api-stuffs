"use strict";

var router     = require('express').Router();
var productDao = require('../dao/productDao.js');
var response   = require('../utils/response.js');

router.get('/products', function(req, res) {
	productDao.findAll(function(error, result) {
		res.json(response.fromSelect(error, result));
	});
});

router.get('/products/:productId', function(req, res) {
	productDao.findById(req.params.productId, function(error, result) {
		res.json(response.fromSelect(error, result));
	});
});

module.exports = router;
