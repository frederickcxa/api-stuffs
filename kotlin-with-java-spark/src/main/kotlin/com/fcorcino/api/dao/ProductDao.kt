package com.fcorcino.api.dao

import com.fcorcino.api.databaseTransaction
import java.sql.ResultSet

data class Product(
        val productId: Int = 0,
        val name: String = "",
        val description: String = "",
        val image: String = "",
        val thumbnail: String? = "",
        val status: String = "",
        val price: Float = 0f
)

class ProductDao {
    fun findAll(): ArrayList<Product> {
        val sql = """
            select productId, name, description, image, thumbnail, status, price
            from products
        """

        val products = databaseTransaction(sql) {
            val products = ArrayList<Product>()
            val resultSet = executeQuery()

            while (resultSet.next()) {
                getProductFromResultSet(resultSet).also { products.add(it) }
            }

            products
        }

        return products ?: ArrayList<Product>()
    }

    fun findById(productId: Int): Product? {
        val sql = """
            select productId, name, description, image, thumbnail, status, price
            from products
            where productId = ?
        """

        val product = databaseTransaction(sql) {
            setInt(1, productId)
            val resultSet = executeQuery()

            if (resultSet.next()) getProductFromResultSet(resultSet) else null
        }

        return product
    }

    private fun getProductFromResultSet(resultSet: ResultSet): Product {
        with(resultSet) {
            return Product(
                    productId = getInt(1),
                    name = getString(2),
                    description = getString(3),
                    image = getString(4),
                    thumbnail = getString(5),
                    status = getString(6),
                    price = getFloat(7)
            )
        }
    }
}
