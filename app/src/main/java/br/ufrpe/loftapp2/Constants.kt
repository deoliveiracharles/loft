package br.ufrpe.loftapp2

import br.ufrpe.loftapp2.model.Item

class Constants {
    companion object {
        val RequestCodeComidas=1010
        var card  = ArrayList<Item>()
        val confirmedCard = ArrayList<Item>()
        val RequestCodeDelete=1011
        val RequestCodeConfirm=1012
        var controlador = 0
    }
}