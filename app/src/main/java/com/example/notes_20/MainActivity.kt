package com.example.notes_20

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), INotesRVAdapter {

    lateinit var viewModel1: NoteViewModel
    var changing:Boolean=false;
    var id:Int=0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        recyclerView.layoutManager=LinearLayoutManager(this);
        val adapter=NotesRVAdapter(this, this, this);
        recyclerView.adapter=adapter;
        viewModel1=ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(NoteViewModel::class.java);
        viewModel1.allNotesTime.observe(this,
            Observer { list ->
                list?.let {
                    adapter.updateList(newList = list);
                }
            })
        submit.setOnClickListener(View.OnClickListener {
            if (changing == false) {
                val noteText = input.text.toString().trim();
                if (!noteText.isEmpty()) {
                    viewModel1.addNote(Entry(noteText, noteText, 1));
                    input.setText("");
                    input.clearFocus();
                    val inputMethodManager: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        submit.getApplicationWindowToken(),
                        0
                    )
                    val toast = Toast.makeText(applicationContext, "Note added", Toast.LENGTH_SHORT)
                    toast.show()

                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Note cannot be empty",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }

            } else {
                val noteText = input.text.toString().trim();
                if (!noteText.isEmpty()) {
                    viewModel1.updateNote(noteText, id);
                    input.setText("");
                    input.clearFocus();
                    val inputMethodManager: InputMethodManager =
                        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        submit.getApplicationWindowToken(),
                        0
                    );
                    submit.setText("SUBMIT");
                    changing = false;
                    val toast = Toast.makeText(
                        applicationContext,
                        "Note modified",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Note cannot be empty",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }

            }
        })
    }
    override fun onItemClicked(note: Entry) {
        viewModel1.deleteNote(note);
        val toast = Toast.makeText(applicationContext, "Note deleted", Toast.LENGTH_LONG)
        toast.show()
    }

    override fun onEditItme(note: Entry) {
        id=note.id;
        var text:String=note.text;
        input.setText(text);
        input.append("");
        input.requestFocus();
        input.setSelection(input.getText().length);
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT)
        submit.setText("CHANGE");
        changing=true;
    }
}