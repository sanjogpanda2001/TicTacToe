package com.example.tictactoeap

import android.annotation.SuppressLint
import android.graphics.Color.blue
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tictactoeap.R.color.white
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buclick(view: View) {

val buSelected=view as Button
    var cellId=0
        when(buSelected.id){
            R.id.b1-> cellId=1
            R.id.b2-> cellId=2
            R.id.b3-> cellId=3
            R.id.b4-> cellId=4
            R.id.b5-> cellId=5
            R.id.b6-> cellId=6
            R.id.b7-> cellId=7
            R.id.b8-> cellId=8
            R.id.b9-> cellId=9
        }
        //Log.d("buClick",buSelected.id.toString())
       // Log.d("buClick: cellId",cellId.toString())
        playGame(cellId,buSelected)
    }


    var activePlayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()

    fun playGame(cellId:Int, buSelected:Button){

            if (activePlayer==1){
                buSelected.text="X"
                buSelected.setBackgroundResource(R.color.blue)
                player1.add(cellId)
                activePlayer=2

                autoplay()
            }else{
                buSelected.text="0"
                buSelected.setBackgroundResource(R.color.green)
                player2.add(cellId)
                activePlayer=1

            }
        //buSelected.isEnabled=false
        checkWinner()

    }

    private fun autoplay() {
        var emptyCells=ArrayList<Int>()
        for(cellid in 1..9){
            if(!(player1.contains(cellid) ||player2.contains(cellid))){
                emptyCells.add(cellid)
            }
        }

        if (emptyCells.size==0){
            restart()
            Toast.makeText(this@MainActivity, "match tied" , Toast.LENGTH_LONG).show()
        }
        else {
            val r = (1..emptyCells.size).random()


            val cellId = emptyCells[r - 1]
            var buSelected: Button?
            buSelected = when (cellId) {
                1 -> b1
                2 -> b2
                3 -> b3
                4 -> b4
                5 -> b5
                6 -> b6
                7 -> b7
                8 -> b8
                9 -> b9
                else -> b1
            }
            playGame(cellId, buSelected)
        }
    }



    private fun checkWinner() {
        var winner=-1
        //row1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner=1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner=2
        }


        //row2
        if(player1.contains(5) && player1.contains(4) && player1.contains(6)){
            winner=1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner=2
        }


        //row3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner=1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner=2
        }


        //col1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner=1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner=2
        }


        //col2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner=1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner=2
        }

        //col3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner=1
        }
        if(player2.contains(9) && player2.contains(6) && player2.contains(3)){
            winner=2
        }


        //cross1
        if(player1.contains(1) && player1.contains(5) && player1.contains(9)){
            winner=1
        }
        if(player2.contains(1) && player2.contains(5) && player2.contains(9)){
            winner=2
        }

        //cross2
        if(player1.contains(7) && player1.contains(5) && player1.contains(3)){
            winner=1
        }
        if(player2.contains(7) && player2.contains(5) && player2.contains(3)){
            winner=2
        }

        if(winner==1){
            p1count++
            Toast.makeText(this@MainActivity, "winner is player 1" , Toast.LENGTH_LONG).show()
           // Thread.sleep(2000)
            restart()
        }
        if(winner==2){
            p2count++
            Toast.makeText(this@MainActivity, "winner is player 2" , Toast.LENGTH_LONG).show()
            //Thread.sleep(2000)
        restart()
        }
    }
    var p1count=0
    var p2count=0


    fun restart(){
        activePlayer=1
        player1.clear()
        player2.clear()
        for(i in 1..9){
            var buSelected:Button?
            buSelected=when(i){
                1->b1
                2->b2
                3->b3
                4->b4
                5->b5
                6->b6
                7->b7
                8->b8
                9->b9
                else ->b1
            }
            buSelected.text=""
            buSelected.setBackgroundResource(white)

            buSelected!!.isEnabled=true


        }
        Toast.makeText(this@MainActivity, "player1-> $p1count , player2-> $p2count" , Toast.LENGTH_LONG).show()
    }
}