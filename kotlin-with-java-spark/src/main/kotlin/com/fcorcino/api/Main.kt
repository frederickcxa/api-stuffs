package com.fcorcino.api

import com.fcorcino.api.dao.ProductDao
import com.google.gson.Gson
import spark.Filter
import spark.Spark.*

fun main(args: Array<String>) {
    val gson by lazy { Gson() }
    val productDao by lazy { ProductDao() }

    path("/products") {

        get("", { _, _ ->
            val products = productDao.findAll()
            val message = "Retrieved ${products.size} row(s)."

            response(message, products)
        }, gson::toJson)

        get("/:productId", { request, _ ->
            val productId = request.params("productId").toInt()
            val product = productDao.findById(productId)
            val message = if (product != null) "Retrieved 1 row." else "No product with id $productId."
            val data = product ?: ""

            response(message, data)
        }, gson::toJson)
    }

    after(Filter { _, response -> response.type("application/json") })
}
