"use strict";

var app        = require('express')();
var bodyParser = require('body-parser');
var mysql	   = require('mysql');
var router     = require('./app/routes/routes.js');

var port = process.env.PORT || 8080;

app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use('', router);
app.listen(port);

console.log('Magic happens on port ' + port);
