package edu.bo.ucb.tercer_parcialv2

class User{

    var id : Int = 0
    var titulo : String ="";
    var isbn : String = "";
    var autor : String ="";
    var fecha : String ="";
    var NroPagina : Int = 0;
    var descripcion : String ="";
    var url : String ="";

    constructor(titulo:String,isbn:String,autor:String,fecha:String,NroPagina:Int,descripcion:String,url:String)
    {
        this.titulo=titulo;
        this.isbn=isbn;
        this.autor=autor;
        this.fecha=fecha;
        this.NroPagina=NroPagina;
        this.descripcion=descripcion;
        this.url=url;
    }
    constructor(){
    }

}