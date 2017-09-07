package com.fcorcino.api

import java.sql.DriverManager
import java.sql.PreparedStatement

fun <T> databaseTransaction(sql: String, body: PreparedStatement.() -> T): T? {
    try {
        Class.forName("com.mysql.jdbc.Driver")
        DriverManager.getConnection("jdbc:mysql://localhost:3306/shopping_cart", "root", "root").use {
            val statement = it.prepareStatement(sql)
            return statement.body()
        }
    } catch(ex: Exception) {
        ex.printStackTrace()
    }

    return null
}

fun response(message: String, data: Any, code: Int = 200): Map<String, Any> {
    return mapOf(
            "code" to code,
            "message" to message,
            "data" to data
    )
}
