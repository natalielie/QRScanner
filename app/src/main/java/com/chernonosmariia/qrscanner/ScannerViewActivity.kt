package com.chernonosmariia.qrscanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView
import android.media.MediaPlayer
import com.chernonosmariia.qrscanner.Data.QRConstants


/**
 * Created by
 * @name Anartz
 * @lastname Mugika
 * @data 2018/06/15
 */
class ScannerViewActivity : Activity(), ZXingScannerView.ResultHandler {
    private var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)

        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()         // Start camera


    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onResume() {
        super.onResume()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onPause() {
        super.onPause()
        this.cleanResult()
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    override fun onBackPressed() { this.cleanResult() }

    private fun cleanResult() {
        try {
            mScannerView!!.stopCamera() // Stop camera on pause
        } catch (e: Exception) {
            Log.e(QRConstants.ERROR, e.message)
        }

        val resultIntent = Intent()
        resultIntent.putExtra(QRConstants.BAR_CODE, "")
        setResult(2, resultIntent)
        finish()
    }

    override fun handleResult(rawResult: Result) {
        // Do something with the result here

        Log.e(QRConstants.HANDLER, rawResult.text) // Prints scan results
        Log.e(QRConstants.HANDLER, rawResult.barcodeFormat.toString()) // Prints the scan format (qrcode)

        try {
            mScannerView!!.stopCamera()

            // Add Sound to comfirm correct take sound
            MediaPlayer.create(this, QRConstants.SCAN_OK_SOUND).start()
            val resultIntent = Intent()

            resultIntent.putExtra(QRConstants.BAR_CODE, rawResult.text)
            setResult(2, resultIntent)
            finish()

            println(QRConstants.STOP_CAMERA)
            // Stop camera on pause
        } catch (e: Exception) {
            Log.e(QRConstants.ERROR, e.message)
            MediaPlayer.create(this, QRConstants.SCAN_ERROR_SOUND).start()
        }

    }
}