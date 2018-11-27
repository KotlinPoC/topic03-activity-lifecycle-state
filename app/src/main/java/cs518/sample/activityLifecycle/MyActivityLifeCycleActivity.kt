package cs518.sample.activityLifecycle

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView

/**
 * In Activity2.java we use the instanceStateBundle to maintain the counter information
 * In MyActivityLifeCycleActivity.java we do not save any state.
 * note the run time differences, when/is the counter reset ?
 * @author PMCampbell
 * @version 2016
 */

class MyActivityLifeCycleActivity : Activity() {
    protected var counter = 0

    /** Called when the activity is first created.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() ")
        setContentView(R.layout.main)


        // I am adding the listeners programmatically,
        // this is done via an anonymous inner class

        // This can also be done via the android:onclick attribute
        // A couple of buttons are coded  here to show you an example of programmatic
        // implemenatation
        val killButton = findViewById(R.id.killButton) as Button
        killButton.setOnClickListener {
            Log.d(TAG, "finish()")
            finish()
        }

        val countButton = findViewById(R.id.countButton) as Button
        countButton.setOnClickListener {
            Log.d(TAG, "counting")
            counter++
            val textView = findViewById(R.id.counterValue) as TextView
            textView.text = "Actual counter: $counter"
        }

        val activityButton = findViewById(R.id.activityButton) as Button
        activityButton.setOnClickListener {
            Log.d(TAG, "fire intent")
            val launchOtherScreen = Intent(applicationContext,
                    Activity2::class.java)
            startActivity(launchOtherScreen)
        }

        // Button dialogueButton = (Button) findViewById(R.id.dialogueButton);
        // The listener for this Button  is the set  via the android:onclick attribute
        // see launchDialogue
        // so we don't need a reference to it unless we are changing something.

    }   //onCreate()

    fun launchDialogue(view: View) {

        Log.d(TAG, "launch alert dialogue")
        val builder = AlertDialog.Builder(this)

        // setting Message and only 1 button
        // can set 3 buttons pos, neg, neutral
        // can set other attributes
        builder.setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_ok
                ) { dialog, id ->
                    // user clicked button
                }
        // Create the AlertDialog object and return it
        val dialog = builder.create()
        dialog.show()
    } // launchDialogue()

    fun rotateScreen(view: View) {
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        else
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }  //rotateScreen()

    // the below lifecycle methods are implemented in order to log their execution
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    companion object {

        val TAG = "LIFECYC"
    }

}  //MyActivityLifecycle