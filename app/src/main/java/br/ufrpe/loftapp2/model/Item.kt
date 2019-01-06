package br.ufrpe.loftapp2.model

import java.io.Serializable

class Item(var id: String, var name: String, var units: Int, var price: Double, var ingredients: String,  var imageLink: String): Serializable {
}