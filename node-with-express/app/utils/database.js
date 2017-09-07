"use strict";

var mysql = require('mysql');
var mysqlConfig = require('./config.js').mysqlConfig;

var connection = mysql.createPool(mysqlConfig);

module.exports = connection;
