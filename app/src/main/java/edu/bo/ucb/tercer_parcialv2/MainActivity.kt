package edu.bo.ucb.tercer_parcialv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val context = this
        var db = DataBaseHandler(context)



        btn_insert.setOnClickListener({
            if (editTextTitulo.text.toString().length > 0 && editTextIsbn.text.toString().length > 0 &&
                    editTextTAutor.text.toString().length > 0 && editTextDate.text.toString().length > 0 &&
                    editTextNumber2.text.toString().length > 0 && editTextTextMultiLine.text.toString().length > 0 &&
                    editTextTextUrl.text.toString().length > 0 ){
                var user = User(editTextTitulo.text.toString(),editTextIsbn.text.toString(),editTextTAutor.text.toString(),editTextDate.text.toString(),editTextNumber2.text.toString().toInt(),editTextTextMultiLine.text.toString(),editTextTextUrl.text.toString())
                db.insertData(user)
            } else {
                Toast.makeText(context,"Please Fill All Data's", Toast.LENGTH_SHORT).show()
            }
        })


        btn_read.setOnClickListener({
            var data = db.readData()
            tvResult.text = ""
            for (i in 0..(data.size - 1)) {
                tvResult.append(data.get(i).id.toString() + " " + data.get(i).titulo + " " + data.get(i).isbn + "\n"+
                        data.get(i).autor.toString() + " " + data.get(i).fecha + " " + data.get(i).NroPagina + "\n"+
                        data.get(i).descripcion.toString() + " " + data.get(i).url)
                val URLL=data.get(i).url.toString()
                val picasso = Picasso.get()
                picasso.load(URLL).into(Imagen)

            }
        })



        btn_delete.setOnClickListener({
            db.deleteData()
            btn_read.performClick()
        })
    }
}
