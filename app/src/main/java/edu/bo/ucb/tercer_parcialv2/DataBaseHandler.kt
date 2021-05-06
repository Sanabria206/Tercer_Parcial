package edu.bo.ucb.tercer_parcialv2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME ="MyDB"
val TABLE_NAME="Users"
val COL_TITULO="titulo";
val COL_ISBN = "isbn";
val COL_AUTOR = "autor";
val COL_FECHA = "fecha";
val COL_NROPAGINA = "nropagina";
val COL_DESCRIPCION = "descripcion";
val COL_URL = "url";
val COL_ID = "id"


class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE " + TABLE_NAME +" (" +
                COL_ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TITULO + " VARCHAR(256)," +
                COL_ISBN + " VARCHAR(256)," +
                COL_AUTOR + " VARCHAR(256)," +
                COL_FECHA + " VARCHAR(256)," +
                COL_NROPAGINA + " INTEGER," +
                COL_DESCRIPCION + " VARCHAR(256)," +
                COL_URL +" VARCHAR(256))"

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertData(user : User){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TITULO,user.titulo)
        cv.put(COL_ISBN,user.isbn)
        cv.put(COL_AUTOR,user.autor)
        cv.put(COL_FECHA,user.fecha)
        cv.put(COL_NROPAGINA,user.NroPagina)
        cv.put(COL_DESCRIPCION,user.descripcion)
        cv.put(COL_URL,user.url)
        var result = db.insert(TABLE_NAME,null,cv)
        if(result == -1.toLong())
            Toast.makeText(context,"Failed", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(context,"Success", Toast.LENGTH_SHORT).show()
    }

    fun readData() : MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var user = User()
                user.id = result.getString(result.getColumnIndex(COL_ID)).toInt()
                user.titulo = result.getString(result.getColumnIndex(COL_TITULO))
                user.isbn = result.getString(result.getColumnIndex(COL_ISBN))
                user.autor = result.getString(result.getColumnIndex(COL_AUTOR))
                user.fecha = result.getString(result.getColumnIndex(COL_FECHA))
                user.NroPagina = result.getString(result.getColumnIndex(COL_NROPAGINA)).toInt()
                user.descripcion = result.getString(result.getColumnIndex(COL_DESCRIPCION))
                user.url = result.getString(result.getColumnIndex(COL_URL))
                list.add(user)
            }while (result.moveToNext())
        }

        result.close()
        db.close()
        return list
    }

    fun deleteData(){
        val db = this.writableDatabase
        db.delete(TABLE_NAME,null,null)
        db.close()
    }


    /*fun updateData() {
        val db = this.writableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do {
                var cv = ContentValues()
                cv.put(COL_AGE,(result.getInt(result.getColumnIndex(COL_AGE))+1))
                db.update(TABLE_NAME,cv,COL_ID + "=? AND " + COL_NAME + "=?",
                    arrayOf(result.getString(result.getColumnIndex(COL_ID)),
                        result.getString(result.getColumnIndex(COL_NAME))))
            }while (result.moveToNext())
        }

        result.close()
        db.close()
    }
  */

}